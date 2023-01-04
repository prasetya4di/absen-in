package id.ac.stiki.doleno.absenin.view.participant.ui.my_profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import id.ac.stiki.doleno.absenin.R
import id.ac.stiki.doleno.absenin.databinding.FragmentParticipantProfileBinding
import id.ac.stiki.doleno.absenin.view.dialog.ConfirmDialog
import id.ac.stiki.doleno.absenin.view.login.LoginActivity

@AndroidEntryPoint
class MyProfileFragment : Fragment() {
    private lateinit var binding: FragmentParticipantProfileBinding
    private lateinit var viewModel: MyProfileViewModel
    private lateinit var confirmDialog: ConfirmDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentParticipantProfileBinding.inflate(inflater, container, false)

        confirmDialog = ConfirmDialog(this.activity)

        viewModel = ViewModelProvider(this)[MyProfileViewModel::class.java]
        viewModel.getUser()
        viewModel.myProfileState.observeForever { state ->
            run {
                binding.etEmail.setText(viewModel.user?.email ?: "")
                binding.etName.setText(viewModel.user?.name ?: "")
                binding.etRole.setText(
                    viewModel.user?.role?.text ?: resources.getStringArray(R.array.list_role).last()
                    ?: "Peserta Acara"
                )
            }
        }

        binding.btnLogout.setOnClickListener { v ->
            confirmDialog.show(
                getString(R.string.logout_message)
            ) {
                val intent = Intent(this.activity, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                viewModel.logout()
            }
        }

        return binding.root
    }
}