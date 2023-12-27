package com.ummhay.appacr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ummhay.appacr.databinding.ActivityLoginBinding

class LoginAct : AppCompatActivity() {
    private lateinit var find : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        find = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(find.root)

        val inptusername  = find.username
        val inptpassword = find.password
        val pasword= "1101"
        val user = listOf("ummhay")

        find.login.setOnClickListener {
            if (inptusername.text.isNotEmpty() && inptpassword.text.isNotEmpty()) {
                if (inptpassword.text.length >= 4){
                    if (inptusername.text.toString() in user && inptpassword.text.toString()== pasword) {
                        startActivity(
                            Intent(this, ActDash::class.java)
                                .putExtra("username", inptusername.text.toString())
                        )
                        alert("Selamat datang di U.H.A ${inptusername.text}")
                        finish()
                    } else {
                        alert("pasword atau username salah")
                    }
                }else {
                    alert("Pasword minimal 4 huruf")
                }
            } else {
                alert("Username dan Password tidak boleh kosong!")
            }
        }
    }

    private fun alert(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}