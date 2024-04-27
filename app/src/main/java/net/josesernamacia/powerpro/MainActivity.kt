package net.josesernamacia.powerpro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import net.josesernamacia.powerpro.fragments.ModulesFragment
import net.josesernamacia.powerpro.fragments.NewsFragment
import net.josesernamacia.powerpro.fragments.YouFragment

class MainActivity : AppCompatActivity() {
    private lateinit var  bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView = findViewById(R.id.bottom_navigation)

        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when(menuItem.itemId){
                R.id.bottom_you -> {
                    replaceFragment(YouFragment())
                    true
                }
                R.id.bottom_news -> {
                    replaceFragment(NewsFragment())
                    true
                }
                R.id.bottom_modules -> {
                    replaceFragment(ModulesFragment())
                    true
                }
                else -> false
            }
        }
        replaceFragment(YouFragment())
    }

    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit()
    }
}