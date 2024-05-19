package net.josesernamacia.powerpro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import net.josesernamacia.powerpro.autentication.AuthActivity
import net.josesernamacia.powerpro.fragments.ModulesFragment
import net.josesernamacia.powerpro.fragments.NewsFragment
import net.josesernamacia.powerpro.fragments.YouFragment

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var youFragment: YouFragment
    lateinit var tvEmailUser: TextView
    lateinit var tvNameUser: TextView

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
        youFragment = YouFragment()
        replaceFragment(YouFragment())

        //Setup
        //val bundle = intent.extras
        //tvEmailUser.text = bundle?.getString("email")
        //setup(tvEmailUser.text.toString())

        //logOutSesion()
    }

    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit()
    }

    private fun setup(email: String){
        youFragment.setEmail(email)

        youFragment.ivLogOut.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            onBackPressed()
        }
    }

    fun logOutSesion (){
        FirebaseAuth.getInstance().signOut()
        startActivity(Intent(this,AuthActivity::class.java))
        finish()
    }


}