package com.albedo.blackwhiteeditor.presentation.selectImage


import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.albedo.blackwhiteeditor.R
import com.albedo.blackwhiteeditor.databinding.ActivitySelectImageBinding
import com.albedo.blackwhiteeditor.domain.models.LayoutImageUIState
import com.albedo.blackwhiteeditor.presentation.selectImage.utils.SelectImageAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach



@AndroidEntryPoint
class SelectImageActivity : AppCompatActivity() {


    companion object {
        const val TAG = "SelectImageActivity"
    }


    private lateinit var binding: ActivitySelectImageBinding
    private val viewModel: SelectImageViewModel by viewModels()
    private lateinit var itemAdapter: SelectImageAdapter


    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectImageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }


    private fun <T> views(block: ActivitySelectImageBinding.() -> T): T? = binding.block()


    @RequiresApi(Build.VERSION_CODES.R)
    private fun init() {
        hideSystemItems()
        setListeners()
        setAdapter()
        requireData()
    }

    @RequiresApi(Build.VERSION_CODES.R)
    private fun hideSystemItems() {
        val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)
        windowInsetsController.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

        window.decorView.setOnApplyWindowInsetsListener { view, windowInsets ->
            windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())
            view.onApplyWindowInsets(windowInsets)
        }
    }


    private fun setListeners() {
        views {
            containerBtnCreateNewLayout.setOnClickListener {
                openEditFragment()
            }
            containerBtnOpenNewImage.setOnClickListener {
                openStorageWithImages()
            }
        }
    }


    private fun setAdapter() {
        views {
            itemAdapter = SelectImageAdapter()
            rslImage.layoutManager = GridLayoutManager(this@SelectImageActivity, resources.getInteger(R.integer.columns_SelectImageActivity), LinearLayoutManager.VERTICAL, false)
            rslImage.adapter = itemAdapter

            itemAdapter.onClickListenerMain = object : SelectImageAdapter.OnClickListener {
                override fun onClick(itemData: LayoutImageUIState) {
                    openMainActivity()
                }
            }

            itemAdapter.onClickListenerDelete = object : SelectImageAdapter.OnClickListener {
                override fun onClick(itemData: LayoutImageUIState) {
                    viewModel.deleteLayout(itemData.id)
                }
            }
        }
    }



    private fun  requireData() {

        viewModel.data.onEach {
            Log.d(TAG, "mainItems : $it")
            viewModel.setListInViewModel(it?: listOf())
            viewModel.filterItems("All")
            setItemListInRecyclerView(viewModel.showList)
            enableBackgroundNoElements(viewModel.showList.isEmpty())
        }.launchIn(viewModel.viewModelScope)

    }






    private fun openEditFragment() {
        TODO()
    }

   private fun openMainActivity() {
       //item: LayoutImageUIState
        TODO()
//        val item : LayoutImageUIState? = null
//
//        this.finish()
//        val intent = Intent(this, MainActivity::class.java)
//        val bundle = Bundle()
//        bundle.putString(ConstantsUI.KeyLayoutIdFromSIAToMA, item.toString())
//        intent.putExtras(bundle)
//        startActivity(intent)
    }

    private fun openStorageWithImages() {
        TODO()
    }


    private fun setItemListInRecyclerView(list: List<LayoutImageUIState>) {
        Log.d(TAG, "list : $list")
        itemAdapter.setData(list)
    }





    private fun enableBackgroundNoElements(boolean: Boolean) {
        views {
            if(boolean) {
                noElementsToShowBackground.visibility = View.VISIBLE
            }else{
                noElementsToShowBackground.visibility = View.GONE
            }
        }
    }
}