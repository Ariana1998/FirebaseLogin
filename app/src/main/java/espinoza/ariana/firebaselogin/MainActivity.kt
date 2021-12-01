package espinoza.ariana.firebaselogin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.ktx.Firebase
import espinoza.ariana.firebaselogin.databinding.ActivityMainBinding
import android.content.Intent
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.*

class MainActivity : AppCompatActivity() {
    private lateinit var auth:FirebaseAuth
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth=Firebase.auth

        signOut()
    }

    private fun signOut(){
        Firebase.auth.signOut()
        val intent= Intent (this,SignInActivity::class.java)
        startActivity(intent)
    }
}