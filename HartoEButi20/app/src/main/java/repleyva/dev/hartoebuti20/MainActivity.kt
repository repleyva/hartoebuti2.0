package repleyva.dev.hartoebuti20

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import repleyva.dev.hartoebuti20.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // preparamos los datos de la actividad
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
    }
}