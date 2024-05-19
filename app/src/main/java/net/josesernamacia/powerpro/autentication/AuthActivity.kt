package net.josesernamacia.powerpro.autentication

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.ErrorCodes
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth
import net.josesernamacia.powerpro.MainActivity
import net.josesernamacia.powerpro.R
import org.w3c.dom.Text

class AuthActivity : AppCompatActivity() {
    private lateinit var btSignIn: Button
    private lateinit var signUpTextView: TextView

    private lateinit var edtEmail: EditText
    private lateinit var edtPasswordUser: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        signUpTextView = findViewById(R.id.tvSignUp)
        edtEmail = findViewById(R.id.edtEmail)
        edtPasswordUser = findViewById(R.id.edtPassword)
        btSignIn = findViewById(R.id.btSignIn)

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

        signUpTextView.setOnClickListener {
            val email = edtEmail.text.toString()
            val password = edtPasswordUser.text.toString()

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

        btSignIn.setOnClickListener {
            val email = edtEmail.text.toString()
            val password = edtPasswordUser.text.toString()

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
            //putExtra("email",email)
        }
        startActivity(homeIntent)
    }

    // Mostrar un mensaje emergente
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }





}