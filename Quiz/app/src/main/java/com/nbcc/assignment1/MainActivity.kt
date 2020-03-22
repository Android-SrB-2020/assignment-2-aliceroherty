package com.nbcc.assignment1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.nbcc.assignment1.databinding.ActivityMainBinding
import com.nbcc.quiz.Question

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val navController = findNavController(R.id.navHostFragment)

        //Adding Hamburger Menu
        NavigationUI.setupWithNavController(binding.navView, navController)
        drawerLayout = binding.drawerLayout

        //Adding Up Button and Hamburger Menu Icons to Header
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
    }

    override fun onSupportNavigateUp(): Boolean = NavigationUI.navigateUp(findNavController(R.id.navHostFragment), drawerLayout)
}
