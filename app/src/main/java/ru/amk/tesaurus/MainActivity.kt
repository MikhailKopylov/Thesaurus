package ru.amk.tesaurus

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.koin.java.KoinJavaComponent.getKoin
import ru.amk.core.entity.AppHistoryState
import ru.amk.core.entity.AppResponseState
import ru.amk.core.model.network.data.DataModel
import ru.amk.core.utils.viewById
import ru.amk.tesaurus.databinding.ActivityMainBinding
import ru.amk.translate.presentation.MainActivityViewModel
import ru.amk.translate.ui.adapter.HistoryAdapter
import ru.amk.translate.ui.adapter.TranslateAdapter
import ru.amk.translate.ui.fragments.SearchDialogFragment
import ru.amk.translate.ui.view.BaseActivity
import java.util.concurrent.Executors

class MainActivity : BaseActivity<AppResponseState>() {

    private val scope: Scope by lazy { getKoin().createScope(this.toString(), named("MainActivity")) }
    override val model: MainActivityViewModel by scope.inject()
    private lateinit var binding: ActivityMainBinding
    private var translateAdapter: TranslateAdapter? = null
    private var historyAdapter: HistoryAdapter? = null

    private val searchFab by viewById<FloatingActionButton>(R.id.search_fab)
    private val historyRecyclerview by viewById<RecyclerView>(R.id.history_recyclerview)
    private val translateRecyclerview by viewById<RecyclerView>(R.id.translate_recyclerview)

    private val onListItemClickListener: TranslateAdapter.OnListItemClickListener =
        object : TranslateAdapter.OnListItemClickListener {
            override fun onItemClick(data: DataModel) {
                Toast.makeText(this@MainActivity, data.text, Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val splashScreen = installSplashScreen()
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        model.getData()
        initViews()
        renderHistoryData()
        title = resources.getString(R.string.app_name)
        searchFab.setOnClickListener {
            translateRecyclerview.visibility = GONE
            historyRecyclerview.visibility = VISIBLE
            val searchDialogFragment = SearchDialogFragment.newInstance()
            searchDialogFragment.setOnSearchClickListener(object :
                SearchDialogFragment.OnSearchClickListener {

                override fun onClick(searchWord: String) {
                    translateRecyclerview.visibility = VISIBLE
                    historyRecyclerview.visibility = GONE
                    model.requestData(word = searchWord, isOnline = true)
                    model.saveWordHistory(word = searchWord)
                    model.translateLiveData.observe(this@MainActivity) {
                        renderData(it)
                    }
                }
            })

            searchDialogFragment.show(
                supportFragmentManager,
                BOTTOM_SHEET_FRAGMENT_DIALOG_TAG
            )
        }

        splashScreen.setKeepOnScreenCondition { true }
        Executors.newSingleThreadExecutor().execute {
            Thread.sleep(3000)
            splashScreen.setKeepOnScreenCondition { false }
        }
    }

    override fun renderData(appResponseState: AppResponseState) {
        renderResponseData(appResponseState)
    }


    override fun renderHistoryData() {
        model.historyLiveData.observe(this@MainActivity) {
            renderHistoryData(it)
        }
    }

    override fun onDestroy() {
        scope.close()
        super.onDestroy()
    }

    fun renderHistoryData(appHistoryState: AppHistoryState) {
        when (appHistoryState) {
            is AppHistoryState.Success -> {
                val data = appHistoryState.data
                if (data == null || data.isEmpty()) {
                    showErrorScreen(getString(R.string.empty_server_response_on_success))
                } else {
                    historyAdapter?.setData(data) ?: run {
                        historyRecyclerview.layoutManager =
                            LinearLayoutManager(applicationContext)
                        historyRecyclerview.adapter =
                            HistoryAdapter(data)
                    }
                }
            }
            is AppHistoryState.Error -> {
                //TODO
            }
        }
    }

    private fun renderResponseData(appResponseState: AppResponseState) {
        when (appResponseState) {
            is AppResponseState.Success -> {
                val dataModel = appResponseState.data
                if (dataModel == null || dataModel.isEmpty()) {
                    showErrorScreen(getString(R.string.empty_server_response_on_success))
                } else {
                    showViewSuccess()
                    translateAdapter?.setData(dataModel) ?: run {
                        translateRecyclerview.layoutManager =
                            LinearLayoutManager(applicationContext)
                        translateRecyclerview.adapter =
                            TranslateAdapter(onListItemClickListener, dataModel)
                    }
                }
            }
            is AppResponseState.Loading -> {
                showViewLoading()
                if (appResponseState.progress != null) {
                    binding.progressBarHorizontal.visibility = VISIBLE
                    binding.progressBarRound.visibility = GONE
                    binding.progressBarHorizontal.progress = appResponseState.progress!!
                } else {
                    binding.progressBarHorizontal.visibility = GONE
                    binding.progressBarRound.visibility = VISIBLE
                }
            }
            is AppResponseState.Error -> {
                showErrorScreen(appResponseState.error.message)
            }
        }
    }

    private fun initViews() {
        translateRecyclerview.layoutManager =
            LinearLayoutManager(applicationContext)
        translateRecyclerview.adapter = TranslateAdapter(onListItemClickListener, listOf())
    }

    private fun showErrorScreen(error: String?) {
        showViewError()
        binding.errorTextview.text = error ?: getString(R.string.undefined_error)
        binding.reloadButton.setOnClickListener {
            model.requestData(word = "error", isOnline = true)
            model.translateLiveData.observe(this@MainActivity) {
                renderData(it)
            }
        }
    }

    private fun showViewSuccess() {
        binding.successLinearLayout.visibility = VISIBLE
        binding.loadingFrameLayout.visibility = GONE
        binding.errorLinearLayout.visibility = GONE
    }

    private fun showViewLoading() {
        binding.successLinearLayout.visibility = GONE
        binding.loadingFrameLayout.visibility = VISIBLE
        binding.errorLinearLayout.visibility = GONE
    }

    private fun showViewError() {
        binding.successLinearLayout.visibility = GONE
        binding.loadingFrameLayout.visibility = GONE
        binding.errorLinearLayout.visibility = VISIBLE
    }

    companion object {
        private const val BOTTOM_SHEET_FRAGMENT_DIALOG_TAG =
            "74a54328-5d62-46bf-ab6b-cbf5fgt0-092395"
    }
}
