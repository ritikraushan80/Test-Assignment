package com.pw.pwrickapp

import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.pw.pwrickapp.databinding.ActivityMainBinding
import com.pw.pwrickapp.helpers.StringConstants
import com.pw.pwrickapp.utils.SharedPref
import com.pw.pwrickapp.utils.ThemeChangeListener
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Ritik on: 31/08/24
 */

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), ThemeChangeListener {
    private lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController
    private var backPressedOnce = false
    private var themeChangePending = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val settings: SharedPreferences = applicationContext.getSharedPreferences(
            applicationContext.packageName, MODE_PRIVATE
        )
        settings.edit().apply()
        SharedPref.init(this)

        binding.root.post {
            navController = findNavController(R.id.nav_host_fragment_activity_main)
        }

        /**
         * Check the current theme and set the app's theme accordingly
         */
        val themeMode = SharedPref.read(StringConstants.THEME_MODE, null)
        when (themeMode) {
            "light" -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            "dark" -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            else -> {
                // If the theme mode is not set or invalid, use the system default
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

            }
        }
    }
    /**
     * Confirmation dialog for change the theme
     */
    private fun showConfirmationDialog(themeMode: Int) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Change Theme")
        builder.setMessage("Are you sure you want to change the theme?")

        builder.setPositiveButton("Yes") { _, _ ->
            themeChangePending = true
            AppCompatDelegate.setDefaultNightMode(themeMode)
        }

        builder.setNegativeButton("No") { dialog, _ ->
            dialog.dismiss()
            themeChangePending = false
        }

        val dialog = builder.create()
        dialog.setCancelable(false)
        dialog.show()

        // Set flag to true indicating theme change is pending
        themeChangePending = true
    }

    override fun onThemeChange(themeMode: Int) {
        showConfirmationDialog(themeMode)
    }

    override fun onBackPressed() {
        if (navController.currentDestination?.id == R.id.characterListFragment) {
            /**
             *  If the current destination is the home fragment, exit the app
             */
            if (backPressedOnce) {
                /**
                 * If back button is pressed twice in quick succession, exit the app
                 */
                finish()
            } else {
                /**
                 * Prompt user to press back again to exit
                 */
                backPressedOnce = true
                Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show()
                /**
                 * Reset backPressedOnce after a delay
                 */
                Handler(Looper.getMainLooper()).postDelayed({ backPressedOnce = false }, 2000)
            }
        } else {
            if (!navController.navigateUp()) {
                super.onBackPressed()
            }
        }

    }
}