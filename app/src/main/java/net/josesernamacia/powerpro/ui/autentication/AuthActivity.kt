package net.josesernamacia.powerpro.ui.autentication

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import net.josesernamacia.powerpro.MainActivity
import net.josesernamacia.powerpro.databinding.ActivityAuthBinding

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //VERIFICAMO QUE EL USUARIO ESTÁ AUTENTICADO
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser != null){
            //SI EL USUARIO YA ESTÁ AUTENTICADO VA AL MAIN ACTIVITY
            showHome(currentUser.email ?:"")
            finish()
        }else{
            //SI NO ESTÁ AUTENTICADO CONTINUAMOS CON LA VISTA DE AUTH ACTIVITY
            setup()
        }
    }

    private fun setup(){
        title = "Autenticación"

        binding.tvSignUp.setOnClickListener {
            val email = binding.edtEmail.text.toString()
            val password = binding.edtPassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                // Verificar si la contraseña cumple con los criterios
                val passwordPattern = Regex("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,}\$")
                if (!passwordPattern.matches(password)) {
                    // Si la contraseña no cumple con los criterios, mostrar un error
                    showAlert("Password ERROR","Password must be at least 6 characters long, including at least one uppercase letter, one lowercase letter, and one number.")
                    return@setOnClickListener
                }

                FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            //FirebaseAuth.getInstance().currentUser?.sendEmailVerification()
                            //showAlert("Email verification sent","Please check your email, and verify your account")
                            showAlert("Email creation account","Cuenta creada correctamente")
                        } else {
                            showAlert("Error creating account","An error occurred while creating the account. Please try again.")
                        }
                    }
            } else {
                showAlert("Empty fields","Please complete all fields")
            }
        }

        binding.btSignIn.setOnClickListener {
            val email = binding.edtEmail.text.toString()
            val password = binding.edtPassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val user = FirebaseAuth.getInstance().currentUser
                            showHome(user?.email ?: "")
                        } else {
                            showAlert("Error logging in","Incorrect email or password. Please try again.")
                        }
                    }

            } else {
                showAlert("Empty fields","Please complete all fields")
            }
        }
    }

    private fun showAlert(titulo: String, msg:String){
        val builder = AlertDialog.Builder(this)
        builder.setTitle(titulo)
        builder.setMessage(msg)
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showHome(email: String){
        val homeIntent = Intent(this,MainActivity::class.java).apply {
            putExtra("email",email)
        }
        startActivity(homeIntent)

    }

}