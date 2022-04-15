package ru.amk.tesaurus

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.amk.tesaurus.databinding.ActivityMainBinding
import ru.amk.tesaurus.entity.AppState
import ru.amk.tesaurus.model.network.data.DataModel
import ru.amk.tesaurus.presentation.MainActivityViewModel
import ru.amk.tesaurus.ui.adapter.MainAdapter
import ru.amk.tesaurus.ui.fragments.SearchDialogFragment
import ru.amk.tesaurus.ui.view.BaseActivity

class MainActivity : BaseActivity<AppState>() {

    override val model: MainActivityViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding
    private var adapter: MainAdapter? = null

    private val onListItemClickListener: MainAdapter.OnListItemClickListener =
        object : MainAdapter.OnListItemClickListener {
            override fun onItemClick(data: DataModel) {
                Toast.makeText(this@MainActivity, data.text, Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        title = resources.getString(R.string.app_name)
        binding.searchFab.setOnClickListener {
            val searchDialogFragment = SearchDialogFragment.newInstance()
            searchDialogFragment.setOnSearchClickListener(object :
                SearchDialogFragment.OnSearchClickListener {

                override fun onClick(searchWord: String) {
                    model.getData(word = searchWord, isOnline = true)
                    model.liveData.observe(this@MainActivity) {
                        renderData(it)
                    }
                }
            })

            searchDialogFragment.show(
                supportFragmentManager,
                BOTTOM_SHEET_FRAGMENT_DIALOG_TAG
            )
        }
    }

    override fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                val dataModel = appState.data
                if (dataModel == null || dataModel.isEmpty()) {
                    showErrorScreen(getString(R.string.empty_server_response_on_success))
                } else {
                    showViewSuccess()
                    adapter?.setData(dataModel) ?: run {
                        binding.mainActivityRecyclerview.layoutManager =
                            LinearLayoutManager(applicationContext)
                        binding.mainActivityRecyclerview.adapter =
                            MainAdapter(onListItemClickListener, dataModel)
                    }
                }
            }
            is AppState.Loading -> {
                showViewLoading()
                if (appState.progress != null) {
                    binding.progressBarHorizontal.visibility = VISIBLE
                    binding.progressBarRound.visibility = GONE
                    binding.progressBarHorizontal.progress = appState.progress
                } else {
                    binding.progressBarHorizontal.visibility = GONE
                    binding.progressBarRound.visibility = VISIBLE
                }
            }
            is AppState.Error -> {
                showErrorScreen(appState.error.message)
            }
        }
    }

    private fun initViews(){
        binding.mainActivityRecyclerview.layoutManager =
            LinearLayoutManager(applicationContext)
        binding.mainActivityRecyclerview.adapter = MainAdapter(onListItemClickListener, listOf())
    }
    private fun showErrorScreen(error: String?) {
        showViewError()
        binding.errorTextview.text = error ?: getString(R.string.undefined_error)
        binding.reloadButton.setOnClickListener {
            model.getData(word = "error", isOnline = true)
            model.liveData.observe(this@MainActivity) {
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
