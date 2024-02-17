package com.albedo.blackwhiteeditor.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.albedo.blackwhiteeditor.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // data base нужна для хранения данных
        // InnerStorageRepository для доставания картинки
        // ImageRepository для выполения действий с картинками
        // ImageUIState/ ImageDB/
    }
}