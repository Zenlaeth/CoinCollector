package com.zenlaeth.tpsup.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.zenlaeth.tpsup.R
import com.zenlaeth.tpsup.fragments.AddCoinFragment
import com.zenlaeth.tpsup.fragments.GraphFragment
import com.zenlaeth.tpsup.fragments.HomeFragment

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        loadFragment(HomeFragment(this), R.string.home_page_title)

        // importer la bottomNavigationView
        val navigationView = findViewById<BottomNavigationView>(R.id.navigation_view)
        navigationView.setOnNavigationItemSelectedListener {
            when(it.itemId)
            {
                R.id.home_page -> {
                    loadFragment(HomeFragment(this), R.string.home_page_title)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.add_coin_page -> {
                    loadFragment(AddCoinFragment(this), R.string.home_page_title)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.graph_page -> {
                    loadFragment(GraphFragment(this), R.string.home_page_title)
                    return@setOnNavigationItemSelectedListener true
                }
                else -> false
            }
        }
    }

    private fun loadFragment(fragment: Fragment, string: Int) {
        findViewById<TextView>(R.id.page_title).text = resources.getString(string)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}