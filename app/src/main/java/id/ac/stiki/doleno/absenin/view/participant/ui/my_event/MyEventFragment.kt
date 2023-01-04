package id.ac.stiki.doleno.absenin.view.participant.ui.my_event

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.ac.stiki.doleno.absenin.data.entity.Absent
import id.ac.stiki.doleno.absenin.databinding.FragmentParticipantEventBinding

@AndroidEntryPoint
class MyEventFragment : Fragment() {
    private lateinit var binding: FragmentParticipantEventBinding
    private lateinit var viewModel: MyEventViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentParticipantEventBinding.inflate(inflater, container, false)
        binding.rvMyEvent.layoutManager = LinearLayoutManager(this.requireContext())

        viewModel = ViewModelProvider(this)[MyEventViewModel::class.java]
        viewModel.fetchAllAbsent()

        viewModel.myEventState.observeForever {
            run {
                binding.loading.layoutLoading.visibility = View.GONE
                binding.error.layoutError.visibility = View.GONE
                binding.empty.layoutEmpty.visibility = View.GONE
                binding.rvMyEvent.visibility = View.GONE
                when (it) {
                    MyEventState.LOADING -> binding.loading.layoutLoading.visibility = View.VISIBLE
                    MyEventState.FAILED -> binding.error.layoutError.visibility = View.VISIBLE
                    MyEventState.SUCCESS -> {
                        if (viewModel.isAbsentsEmpty()) {
                            binding.empty.layoutEmpty.visibility = View.VISIBLE
                        } else {
                            binding.rvMyEvent.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }

        viewModel.absents.observeForever {
            run {
                val adapter = MyEventAdapter(it, object : MyEventAdapter.MyEventAdapterCallback {
                    override fun onClick(absent: Absent) {
                        TODO("Not yet implemented")
                    }
                })
            }
        }

        return binding.root
    }
}