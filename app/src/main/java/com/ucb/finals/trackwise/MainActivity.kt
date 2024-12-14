package com.ucb.finals.trackwise

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.ucb.finals.trackwise.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import com.google.firebase.FirebaseApp

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var fragmentManager: FragmentManager
    private lateinit var binding: ActivityMainBinding
    private val userActivities = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        FirebaseApp.initializeApp(this)

        setSupportActionBar(binding.toolbar)

        val toggle = ActionBarDrawerToggle(this, binding.drawerLayout, binding.toolbar, R.string.open_nav, R.string.close_nav)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        binding.navigationDrawer.setNavigationItemSelectedListener(this)

        binding.bottomNavigation.background = null
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when(item.itemId){
                R.id.bottom_home -> {
                    openFragment(HomeFragment())
                    logUserActivity("Opened Home")
                    true
                }
                R.id.bottom_dashboard -> {
                    openFragment(DashboardFragment())
                    logUserActivity("Opened Dashboard")
                    true
                }
                R.id.bottom_history -> {
                    openFragment(HistoryFragment.newInstance(userActivities))
                    logUserActivity("Opened History")
                    true
                }
                R.id.bottom_settings -> {
                    openFragment(SettingsFragment())
                    logUserActivity("Opened Settings")
                    true
                }
                else -> false
            }
        }

        fragmentManager = supportFragmentManager

        // Retrieve data from the intent
        val idNumber = intent.getStringExtra("idNumber")
        val fullName = intent.getStringExtra("fullName")
        val email = intent.getStringExtra("email")

        // When navigating to ProfileFragment, pass the data
        val profileFragment = ProfileFragment()
        val bundle = Bundle()
        bundle.putString("idNumber", idNumber)
        bundle.putString("fullName", fullName)
        bundle.putString("email", email)
        profileFragment.arguments = bundle

        // Open ProfileFragment
        openFragment(profileFragment)

        binding.fab.setOnClickListener {
            Toast.makeText(this, "Categories", Toast.LENGTH_SHORT).show()
            logUserActivity("Clicked Categories FAB")
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_home -> {
                openFragment(HomeFragment())
                logUserActivity("Opened Home")
            }
            R.id.nav_profile -> {
                // Open ProfileFragment with user data
                val profileFragment = ProfileFragment()
                val bundle = Bundle()
                bundle.putString("idNumber", intent.getStringExtra("idNumber"))
                bundle.putString("fullName", intent.getStringExtra("fullName"))
                bundle.putString("email", intent.getStringExtra("email"))
                profileFragment.arguments = bundle
                openFragment(profileFragment)
                logUserActivity("Opened Profile")
            }
            R.id.nav_analytics -> {
                openFragment(AnalyticsFragment())
                logUserActivity("Opened Analytics")
            }
            R.id.nav_aboutUs -> {
                openFragment(AboutUsFragment())
                logUserActivity("Opened About Us")
            }
            R.id.nav_logout -> {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)){
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
            logUserActivity("Pressed Back")
        }
    }

    private fun openFragment(fragment: Fragment) {
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }

    private fun logUserActivity(activity: String) {
        userActivities.add(activity)
    }
}