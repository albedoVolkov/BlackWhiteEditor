package com.albedo.blackwhiteeditor.presentation.main

import android.app.Activity
import android.graphics.BitmapFactory
import android.graphics.Insets
import android.os.Build
import android.os.Bundle
import android.util.Base64
import android.util.DisplayMetrics
import android.util.Log
import android.view.WindowInsets
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.albedo.blackwhiteeditor.databinding.ActivityMainBinding
import com.albedo.blackwhiteeditor.domain.services.drawer.PaintView
import com.albedo.blackwhiteeditor.presentation.utils.ConstantsUI
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    companion object {
        // MVP / CHICHIRONNE / DB adapter / work with storage / work with image
        const val TAG = "MainActivity"
    }

    private lateinit var binding: ActivityMainBinding
    //private val viewModel: MainActivityViewModel by viewModels()
    private var paintView: PaintView? = null


    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getDataFromOldActivity(savedInstanceState)
        init()
    }


    private fun <T> views(block: ActivityMainBinding.() -> T): T? = binding.block()


    @RequiresApi(Build.VERSION_CODES.R)
    private fun init() {
        hideSystemItems()
        requireData()
        setViews()
        setListeners()
    }

    private fun getDataFromOldActivity(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate : bundle - $savedInstanceState")
        if (savedInstanceState != null) {

            val id: String? = savedInstanceState.getString(ConstantsUI.KeyLayoutIdFromSIAToMA, "")
            Log.d(TAG, "onCreate : id - $id")
            if (id == "" || id == null || id == "null") {
                //if id == null then image != null
                val stringImage: String? = savedInstanceState.getString(ConstantsUI.KeyBitmapFromSIAToMA, "")
                val imageBytes = Base64.decode(stringImage, 0)
                val image = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)

            } else {
                //if id != null then image == null
                //TODO("action with id")

            }
        }
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




    private fun requireData() {
       // TODO()
//        viewModel.errorData.onEach {
//            Log.d(TAG, "error : $it")
//            if(it != ""){ Toast.makeText(this@MainActivity,"Ошибка при загрузке данных - $it",Toast.LENGTH_LONG).show() }
//        }.launchIn(viewModel.viewModelScope)
//
//        viewModel.data.onEach {
//            Log.d(TAG, "mainItems : $it")
//            viewModel.setListInViewModel(it)
//            setItemListInRecyclerView(it)
//        }.launchIn(viewModel.viewModelScope)
    }


    @RequiresApi(Build.VERSION_CODES.R)
    private fun setViews() {
        views {

            paintView = containerEditImage


            paintView!!.init(getScreenWidth(this@MainActivity).second,getScreenWidth(this@MainActivity).first)

        }
    }

    private fun getScreenWidth(activity: Activity): Pair<Int,Int> {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val windowMetrics = activity.windowManager.currentWindowMetrics
            val insets: Insets = windowMetrics.windowInsets.getInsetsIgnoringVisibility(WindowInsets.Type.systemBars())
            Pair(
                windowMetrics.bounds.width() - insets.left - insets.right,
                windowMetrics.bounds.height() - insets.bottom - insets.top
            )
        } else {
            val displayMetrics = DisplayMetrics()
            activity.windowManager.defaultDisplay.getMetrics(displayMetrics)
            Pair(
                displayMetrics.widthPixels,
                displayMetrics.heightPixels
            )
        }
    }

    private fun setListeners() {
        views {
            //TOOLBAR TOP

            btnBack.setOnClickListener {
                TODO()
            }
            btnDownload.setOnClickListener {
                TODO()
            }
            btnReturnActionBack.setOnClickListener {
                TODO()
            }
            btnReturnActionForward.setOnClickListener {
                TODO()
            }


            //TOOLBAR BOTTOM
            //Tool
            toolBarBottomMain.containerEraser.setOnClickListener {
                TODO()
            }
            toolBarBottomMain.containerBrush.setOnClickListener {
                TODO()
            }


            //Color
            toolBarBottomMain.containerColorBlack.setOnClickListener {
                TODO()
            }
            toolBarBottomMain.containerColorWhite.setOnClickListener{
                TODO()
            }

            //Size
            toolBarBottomMain.containerSize20.setOnClickListener {
                TODO()
            }
            toolBarBottomMain.containerSize40.setOnClickListener {
                TODO()
            }
            toolBarBottomMain.containerSize60.setOnClickListener {
                TODO()
            }
            toolBarBottomMain.containerSize80.setOnClickListener {
                TODO()
            }
            toolBarBottomMain.containerSize100.setOnClickListener {
                TODO()
            }
            toolBarBottomMain.containerSize120.setOnClickListener {
                TODO()
            }
        }
    }

}