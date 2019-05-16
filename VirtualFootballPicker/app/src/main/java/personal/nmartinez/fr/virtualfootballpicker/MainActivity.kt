package personal.nmartinez.fr.virtualfootballpicker

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.WindowManager
import personal.nmartinez.fr.virtualfootballpicker.navigation.HideShowIconInterface
import personal.nmartinez.fr.virtualfootballpicker.navigation.NavigationManager

open class MainActivity : AppCompatActivity(), HideShowIconInterface {

    private lateinit var toggle: ActionBarDrawerToggle
    private var drawer: DrawerLayout? = null
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        window.setFlags(
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED)

        drawer = findViewById(R.id.drawer_layout)
        toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer?.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        bottomNavigationView = findViewById<View>(R.id.navigation) as BottomNavigationView
        NavigationManager.getInstance().init(supportFragmentManager, this, drawer, navigationView, bottomNavigationView)
        NavigationManager.getInstance().homePage()
    }

    override fun onBackPressed() {
        if (drawer != null && drawer!!.isDrawerOpen(GravityCompat.START)) {
            drawer!!.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun showHamburgerIcon() {
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        toggle.isDrawerIndicatorEnabled = true
    }

    override fun showBackIcon() {
        toggle.isDrawerIndicatorEnabled = false
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toggle.toolbarNavigationClickListener = View.OnClickListener { onBackPressed() }
    }

}


