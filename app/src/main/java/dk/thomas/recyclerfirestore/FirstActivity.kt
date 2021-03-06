package dk.thomas.recyclerfirestore

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)

        var hej = findViewById<EditText>(R.id.hejEdit)
        var farvel = findViewById<EditText>(R.id.farvelEdit)
        var age = findViewById<EditText>(R.id.ageEdit)

        val upload = findViewById<Button>(R.id.btnUploadData)
        upload.setOnClickListener {
            var newUser = User(age.text.toString() ,hej.text.toString(), farvel.text.toString())
            uploadData("User", newUser)
            val i = Intent(this, SecondActivity::class.java)
            startActivity(i)
        }
    }
}