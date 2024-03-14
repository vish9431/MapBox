package com.mapbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.Window
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Spinner
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.get
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.mapbox.maps.MapView
import com.mapbox.maps.Style
import com.mapbox.maps.plugin.Plugin

class MainActivity : AppCompatActivity() {

    private lateinit var mapView: MapView
    private lateinit var mapTypeSpinner: Spinner
    private lateinit var drawerLayout: DrawerLayout
    lateinit var  actionBarDrawerToggle: ActionBarDrawerToggle

    private lateinit var navList: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Hide the status bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main)
        mapView=findViewById(R.id.mapView)
        drawerLayout = findViewById(R.id.drawer_layout)
        val navigationView: NavigationView = findViewById(R.id.navigation_view)

        //mapView.getMapboxMap().loadStyleUri(Style.MAPBOX_STREETS)

        // init action bar drawer toggle
        actionBarDrawerToggle = ActionBarDrawerToggle(this,drawerLayout,R.string.nav_open,R.string.nav_close)
        // add a drawer listener into  drawer layout
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        // show menu icon and back icon while drawer open
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        navigationView.setNavigationItemSelectedListener { menuItem ->
            // Handle menu item clicks here
            when (menuItem.itemId) {
                R.id.menu_streets -> {
                    mapView.getMapboxMap().loadStyleUri(Style.SATELLITE_STREETS)
                }
                R.id.menu_satellite -> {
                    mapView.getMapboxMap().loadStyleUri(Style.SATELLITE)
                }
                R.id.menu_hybrid -> {
                    mapView.getMapboxMap().loadStyleUri(Style.OUTDOORS)
                }
            }
            // Close the drawer after handling menu item click
            drawerLayout.closeDrawers()
            true
        }


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            true
        } else super.onOptionsItemSelected(item)
    }
}