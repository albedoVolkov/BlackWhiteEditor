package com.albedo.blackwhiteeditor.presentation.selectImage


import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.albedo.blackwhiteeditor.R
import com.albedo.blackwhiteeditor.databinding.ActivitySelectImageBinding
import com.albedo.blackwhiteeditor.domain.models.LayoutImageUIState
import com.albedo.blackwhiteeditor.presentation.main.MainActivity
import com.albedo.blackwhiteeditor.presentation.selectImage.utils.SelectImageAdapter
import com.albedo.blackwhiteeditor.presentation.utils.ConstantsUI
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


    @RequiresApi(Build.VERSION_CODES.R)
    private fun setListeners() {
        views {
            containerBtnCreateNewLayout.setOnClickListener {
                openBlankFragment()
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
                    openMainActivity(itemData.id)
                }
            }

            itemAdapter.onClickListenerDelete = object : SelectImageAdapter.OnClickListener {
                override fun onClick(itemData: LayoutImageUIState) {
                    viewModel.deleteLayout(itemData.id)
                }
            }
        }
    }


    private fun setItemListInRecyclerView(list: List<LayoutImageUIState>) {
        Log.d(TAG, "list : $list")
        itemAdapter.setData(list)
    }


    private var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val returnString : Uri? = result.data?.data
            Log.d(TAG, "resultLauncher : returnString - $returnString")
            if(returnString != null){
                val returnBitmap : Bitmap? = loadFromUri(returnString)
                if(returnBitmap != null){
                    openMainActivity(returnBitmap)
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




    private fun openBlankFragment() {

        val bitmap = Bitmap.createBitmap(400,800,Bitmap.Config.ARGB_8888)

        val canvas = Canvas(bitmap)
        canvas.drawColor(ContextCompat.getColor(this, R.color.white))

        openMainActivity(bitmap = bitmap)
    }


    @RequiresApi(Build.VERSION_CODES.R)
    private fun openStorageWithImages() {
        try {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            Intent.createChooser(intent, "Select Picture")
            //startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE)
            resultLauncher.launch(intent)
        }catch (e : Exception){
            Log.d(TAG, "openStorageWithImages : error : $e")
        }
    }


   private fun openMainActivity(id: String) {
        this.finish()
        val intent = Intent(this, MainActivity::class.java)
        val bundle = Bundle()
        bundle.putString(ConstantsUI.KeyLayoutIdFromSIAToMA, id)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    private fun openMainActivity(bitmap: Bitmap) {
        this.finish()
        val intent = Intent(this, MainActivity::class.java)
        val bundle = Bundle()

        bundle.putString(ConstantsUI.KeyBitmapFromSIAToMA, bitmap.toString())
        intent.putExtras(bundle)
        startActivity(intent)
    }



    private fun loadFromUri(uri : Uri) : Bitmap?{
        try{
            val source : ImageDecoder.Source = ImageDecoder.createSource(contentResolver, uri)
            return ImageDecoder.decodeBitmap(source)
        }catch (e : Exception){
            Log.d(TAG, "loadFromUri : error : $e")
            return null
        }
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