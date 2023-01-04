package id.ac.stiki.doleno.absenin.view.participant

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import id.ac.stiki.doleno.absenin.databinding.ActivityParticipantBinding

class ParticipantActivity : AppCompatActivity() {
    private lateinit var binding: ActivityParticipantBinding

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        binding = ActivityParticipantBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)
    }
}