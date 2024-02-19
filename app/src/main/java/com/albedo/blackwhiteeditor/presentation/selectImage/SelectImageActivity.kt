package com.albedo.blackwhiteeditor.presentation.selectImage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.albedo.blackwhiteeditor.R
import com.albedo.blackwhiteeditor.databinding.ActivitySelectImageBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectImageActivity : AppCompatActivity() {


        companion object {
            const val TAG = "SelectImageActivity"
        }

        private lateinit var binding: ActivitySelectImageBinding


        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
            init()
        }


        private fun <T> views(block: ActivitySelectImageBinding.() -> T): T? = binding.block()


        private fun init() {
            setListeners()
            requireData()
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
                containerBtnSelect.setOnClickListener {
                    openMainActivity()
                }
            }
        }


        private fun openMainActivity() {
            TODO()
//        val intent = Intent(this, UserInformationActivity::class.java)
//        val bundle = Bundle()
//        bundle.putString(ConstantsSource.KeyFromMAToUIA, itemData.id)
//        intent.putExtras(bundle)
//        startActivity(intent)
        }
    }