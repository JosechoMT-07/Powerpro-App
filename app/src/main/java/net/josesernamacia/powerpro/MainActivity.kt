package net.josesernamacia.powerpro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import net.josesernamacia.powerpro.autentication.AuthActivity
import net.josesernamacia.powerpro.databinding.ActivityMainBinding
import net.josesernamacia.powerpro.fragments.ModulesFragment
import net.josesernamacia.powerpro.fragments.NewsFragment
import net.josesernamacia.powerpro.fragments.YouFragment

class MainActivity : AppCompatActivity() {
    private lateinit var youFragment: YouFragment
    private lateinit var binding: ActivityMainBinding

    lateinit var tvEmailUser: TextView
    lateinit var tvNameUser: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigation.setOnItemSelectedListener { menuItem ->
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
        youFragment = YouFragment()
        replaceFragment(YouFragment())

    }

    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit()
    }



}