package com.example.ainterdent.view

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.ainterdent.R
import com.example.ainterdent.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_menu)

        val navController= findNavController(R.id.menu_container)

        val bottomNavigationView= findViewById<BottomNavigationView>(R.id.nav_view)

        bottomNavigationView.setupWithNavController(navController)


    }

}