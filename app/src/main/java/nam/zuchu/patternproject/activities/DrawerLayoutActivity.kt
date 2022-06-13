package nam.zuchu.patternproject.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import nam.zuchu.patternproject.R
import nam.zuchu.patternproject.databinding.ActivityDrawerLayoutBinding
import nam.zuchu.patternproject.framents.login.SignInFM

class DrawerLayoutActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {
    lateinit var mainBinding:ActivityDrawerLayoutBinding
    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityDrawerLayoutBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        initNavController()
        setUpNavController()
    }

    //step2 setUpNavController
    private fun setUpNavController() {
        NavigationUI.setupWithNavController(mainBinding.navView, navController)
        NavigationUI.setupActionBarWithNavController(this,navController)
        NavigationUI.setupActionBarWithNavController(this, navController, mainBinding.drawerLayout)
    }

    //step1 createNavController
    private fun initNavController() {
        navController = findNavController(R.id.fmNavHost)
    }

    //step3 call support to click item in NavView
    override fun onSupportNavigateUp(): Boolean {
        initNavController()
        return NavigationUI.navigateUp(navController, mainBinding.drawerLayout)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.signOut->{
                Toast.makeText(this,"Sign Out",Toast.LENGTH_SHORT  ).show()
                val intent = Intent(this@DrawerLayoutActivity,MainActivity::class.java)
                startActivity(intent)
                this.finishAffinity()
            }
        }
        mainBinding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }


}