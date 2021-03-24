package com.example.calculatebmi

import android.app.Application
import android.content.Context
import com.example.calculatebmi.Helper.LocalHelper


class MainApplication : Application() {
    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(LocalHelper.onAttach(base, "en"))
    }
}