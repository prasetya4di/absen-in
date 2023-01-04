package id.ac.stiki.doleno.absenin.view.participant

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import id.ac.stiki.doleno.absenin.R
import id.ac.stiki.doleno.absenin.databinding.ActivityParticipantBinding
import id.ac.stiki.doleno.absenin.view.participant.ui.absent.AbsentFragment
import id.ac.stiki.doleno.absenin.view.participant.ui.all_event.AllEventFragment
import id.ac.stiki.doleno.absenin.view.participant.ui.history.HistoryFragment
import id.ac.stiki.doleno.absenin.view.participant.ui.my_event.MyEventFragment
import id.ac.stiki.doleno.absenin.view.participant.ui.my_profile.MyProfileFragment

@AndroidEntryPoint
class ParticipantActivity : AppCompatActivity() {
    private lateinit var binding: ActivityParticipantBinding

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        binding = ActivityParticipantBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)

        val allEventFragment = AllEventFragment()
        val myEventFragment = MyEventFragment()
        val absentFragment = AbsentFragment()
        val historyFragment = HistoryFragment()
        val myProfileFragment = MyProfileFragment()

        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.event_page -> setCurrentPage(allEventFragment)
                R.id.my_event_page -> setCurrentPage(myEventFragment)
                R.id.absent_page -> setCurrentPage(absentFragment)
                R.id.history_page -> setCurrentPage(historyFragment)
                R.id.profile_page -> setCurrentPage(myProfileFragment)
            }
            true
        }

        setCurrentPage(myEventFragment)
    }

    private fun setCurrentPage(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.flAdmin, fragment).commit()
    }
}