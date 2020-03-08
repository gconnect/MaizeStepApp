package com.chuks.maizestemapp.common.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.chuks.maizestemapp.common.util.showToast
import com.google.android.material.bottomnavigation.BottomNavigationView


/**
 * This HomeActivity is the host activity for the other fragments
 * */
class HomeActivity : AppCompatActivity() {

    lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var navController: NavController
    lateinit var navView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.chuks.maizestemapp.R.layout.activity_home)
         navView= findViewById(com.chuks.maizestemapp.R.id.nav_view)
        navController = findNavController(com.chuks.maizestemapp.R.id.nav_host_fragment)

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
               com.chuks.maizestemapp.R.id.navigation_home,
                com.chuks.maizestemapp.R.id.navigation_about,
                com.chuks.maizestemapp.R.id.navigation_support,
               com.chuks.maizestemapp.R.id.navigation_share
            )
        )

        supportActionBar?.hide()

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        navView.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                com.chuks.maizestemapp.R.id.navigation_share -> shareInsect()
                com.chuks.maizestemapp.R.id.navigation_support -> sendMail()
                com.chuks.maizestemapp.R.id.navigation_home -> navController.navigate(com.chuks.maizestemapp.R.id.navigation_home)
                com.chuks.maizestemapp.R.id.navigation_about -> navController.navigate(com.chuks.maizestemapp.R.id.navigation_about)
            }
            true
        }


    }

    /**
     * The [onSupportNavigateUp] displays the back arrow and ensures backward navigation
     * */
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


    /**
     * The [shareInsect] shares the app to other social apps
     * */
    private fun shareInsect() {
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(
            Intent.EXTRA_TEXT,
            "Hello dear, Check out Maize Insect App "
        )
        sendIntent.type = "text/plain"
        this.startActivity(sendIntent)
    }

    /**
     * The [sendMail] sends mail to the owner of the app
     * */
    private fun sendMail() {
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.putExtra(Intent.EXTRA_SUBJECT, "Subject of email")
        intent.putExtra(Intent.EXTRA_TEXT, "Body of email")
        intent.data = Uri.parse("mailto:jocian_vef2004@yahoo.com")
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) 
       try {
           startActivity(intent)

       }catch (ex: android.content.ActivityNotFoundException){
           this.showToast("There are no email clients installed.")
       }
    }

}
