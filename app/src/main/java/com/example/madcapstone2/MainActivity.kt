package com.example.madcapstone2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.madcapstone2.ui.home.HomeFragment
import com.example.madcapstone2.ui.packing.PackingFragment
import com.example.madcapstone2.ui.map.MapFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    private val  fragments = arrayListOf<Fragment>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

//        val navController = findNavController(R.id.nav_host_fragment)

        navView.setOnNavigationItemSelectedListener(navListener)
        //I added this if statement to keep the selected fragment when rotating the device
        initFragments()
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(
                R.id.nav_host_fragment,
                HomeFragment()
            ).commit()
        }


    }
    private val navListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            var selectedFragment: Fragment? = null
            when (item.itemId) {
                R.id.navigation_home -> selectedFragment = fragments[0]
                R.id.navigation_list -> selectedFragment = fragments[1]
                R.id.navigation_map -> selectedFragment = fragments[2]
            }
            if (selectedFragment != null) {
//            fragments.removeAt(item.itemId)
//            fragments.add(item.itemId, supportFragmentManager.fragments.last())
                supportFragmentManager.beginTransaction().replace(
                    R.id.nav_host_fragment,
                    selectedFragment
                ).commit()
            }
            true
        }
    private fun  initFragments(){
        fragments.add(HomeFragment())
        fragments.add(PackingFragment())
        fragments.add(MapFragment())
    }
}
