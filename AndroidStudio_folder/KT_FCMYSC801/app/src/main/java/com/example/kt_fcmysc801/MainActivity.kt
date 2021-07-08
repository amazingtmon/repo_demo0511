package com.example.kt_fcmysc801

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {
    //게으른 인스턴스화
    private val resultTxtView: TextView by lazy {
        findViewById(R.id.resultTextView)
    }

    private val firebaseTokenText: TextView by lazy {
        findViewById(R.id.firebaseTokenText)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initFirebase();
    }

    private fun initFirebase(){
        FirebaseMessaging.getInstance().token
            .addOnCompleteListener { task ->
                if(task.isSuccessful){
                    firebaseTokenText.text = task.result
                }
            }
    }
}