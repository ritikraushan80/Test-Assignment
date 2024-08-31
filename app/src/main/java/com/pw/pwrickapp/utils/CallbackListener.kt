package com.pw.pwrickapp.utils


/**
 * Created by Ritik on: 31/08/24
 */

interface CallbackListener {
    fun clickOnCharacterItem(id:Int)
}


interface ThemeChangeListener {
    fun onThemeChange(themeMode: Int)
}
