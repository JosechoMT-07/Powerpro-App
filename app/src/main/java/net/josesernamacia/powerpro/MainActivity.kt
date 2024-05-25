package net.josesernamacia.powerpro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.FirebaseFirestore
import net.josesernamacia.powerpro.databinding.ActivityMainBinding
import net.josesernamacia.powerpro.fragments.ModulesFragment
import net.josesernamacia.powerpro.fragments.NewsFragment
import net.josesernamacia.powerpro.fragments.YouFragment

class MainActivity : AppCompatActivity() {
    private lateinit var youFragment: YouFragment
    private lateinit var binding: ActivityMainBinding

    private val db = FirebaseFirestore.getInstance()



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