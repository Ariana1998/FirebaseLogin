package espinoza.ariana.firebaselogin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import espinoza.ariana.firebaselogin.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import espinoza.ariana.firebaselogin.databinding.ActivitySignUpBinding
import java.util.regex.Pattern
import android.content.ContentValues.TAG

class SignUpActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        binding= ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Inicializamos
        // Initialize Firebase Auth
        auth = Firebase.auth

        binding.signUpButton.setOnClickListener{
            val mEmail=binding.emailEditText.text.toString()
            val mPassword=binding.passwordEditText.text.toString()
            val mRepeatPassword=binding.repeatPasswordEditText.text.toString()
            val passwordRegex= Pattern.compile("^" +
                    "(?=.*[-@#$%^&+=])" +     // Al menos 1 carácter especial
                    ".{6,}" +                // Al menos 4 caracteres
                    "$")

            if(mEmail.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(mEmail).matches()){
                Toast.makeText(this,"Ingrese un email valido.",Toast.LENGTH_SHORT).show()
            } else if(mPassword.isEmpty() || !passwordRegex.matcher(mPassword).matches()){
                Toast.makeText(this,"La contrasena es debil",Toast.LENGTH_SHORT).show()
            } else if(mPassword != mRepeatPassword){
                Toast.makeText(this,"Confirma la contrasena",Toast.LENGTH_SHORT).show()
            } else {
                createAccount(mEmail,mPassword)
            }
        }


    }

    private fun createAccount(email:String, password:String){
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                } else {
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }
}