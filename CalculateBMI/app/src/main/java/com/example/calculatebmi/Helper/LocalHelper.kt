package com.example.calculatebmi.Helper;


import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.preference.PreferenceManager
import java.util.*


object LocalHelper {
    private const val SELECTED_LANGUAGE = "Locale.Helper.Selected.Language"
    fun onAttach(context: Context, en: String?): Context {
        val lang = getPersistedData(context, Locale.getDefault().language)
        return setLocale(context, lang)
    }

    fun setLocale(context: Context, lang: String?): Context {
        persist(context, lang)
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) updateResources(context, lang) else updateResourcesLegacy(context, lang)
    }

    @TargetApi(Build.VERSION_CODES.N)
    private fun updateResources(context: Context, lang: String?): Context {
        val locale = Locale(lang)
        Locale.setDefault(locale)
        val config = context.resources.configuration
        config.setLocale(locale)
        config.setLayoutDirection(locale)
        return context.createConfigurationContext(config)
    }

    private fun updateResourcesLegacy(context: Context, lang: String?): Context {
        val locale = Locale(lang)
        Locale.setDefault(locale)
        val resources = context.resources
        val config = resources.configuration
        config.locale = locale
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) config.setLayoutDirection(locale)
        resources.updateConfiguration(config, resources.displayMetrics)
        return context
    }

    private fun persist(context: Context, lang: String?) {
        val pref = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = pref.edit()
        editor.putString(SELECTED_LANGUAGE, lang)
        editor.apply()
    }

    private fun getPersistedData(context: Context, language: String): String? {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        return preferences.getString(SELECTED_LANGUAGE, language)
    }
}
