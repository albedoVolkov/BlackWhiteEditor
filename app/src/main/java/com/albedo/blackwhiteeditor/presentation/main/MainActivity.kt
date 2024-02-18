package com.albedo.blackwhiteeditor.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.albedo.blackwhiteeditor.R
import com.albedo.blackwhiteeditor.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    companion object {
        const val TAG = "MainActivity"
    }

    private lateinit var binding: ActivityMainBinding
   // private val viewModel: MainActivityViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // MVP / CHICHIRONNE / DB adapter / work with storage / work with image

        // data base нужна для хранения данных
        // InnerStorageRepository для доставания картинки
        // ImageRepository для выполения действий с картинками
        // ImageUIState/ ImageDB/
        init()
    }


    private fun <T> views(block: ActivityMainBinding.() -> T): T? = binding.block()


    private fun init() {
        requireData()
        setListeners()
    }


    private fun requireData() {
        TODO()
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


    private fun setListeners() {
        views {
            //TOOLBAR TOP
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


    private fun openSelectedImageFragment() {
        TODO()
//        val intent = Intent(this, UserInformationActivity::class.java)
//        val bundle = Bundle()
//        bundle.putString(ConstantsSource.KeyFromMAToUIA, itemData.id)
//        intent.putExtras(bundle)
//        startActivity(intent)
    }
}