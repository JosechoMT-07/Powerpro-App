package net.josesernamacia.powerpro.ui.autentication

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import net.josesernamacia.powerpro.MainActivity
import net.josesernamacia.powerpro.R
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
                    showAlert(getString(R.string.error_pass), getString(R.string.err_pass_msg))
                    return@setOnClickListener
                }

                FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            //FirebaseAuth.getInstance().currentUser?.sendEmailVerification()
                            //showAlert("Email verification sent","Please check your email, and verify your account")
                            showAlert(getString(R.string.creacionCuenta),
                                getString(R.string.conf_cuenta))
                        } else {
                            showAlert(getString(R.string.error_account),
                                getString(R.string.create_account))
                        }
                    }
            } else {
                showAlert(getString(R.string.empty_fields), getString(R.string.empty_fields_msg))
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
                            showAlert(getString(R.string.error_al_iniciar_sesi_n),
                                getString(R.string.correo_electr_nico_o_contrase_a_incorrectos_por_favor_int_ntelo_de_nuevo))
                        }
                    }

            } else {
                showAlert(getString(R.string.campos_vac_os),
                    getString(R.string.rellene_todos_los_campos))
            }
        }
    }

    private fun showAlert(titulo: String, msg:String){
        val builder = AlertDialog.Builder(this)
        builder.setTitle(titulo)
        builder.setMessage(msg)
        builder.setPositiveButton(getString(R.string.aceptar),null)
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