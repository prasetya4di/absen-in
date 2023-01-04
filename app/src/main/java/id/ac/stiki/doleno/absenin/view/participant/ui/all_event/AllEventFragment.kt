package id.ac.stiki.doleno.absenin.view.participant.ui.all_event

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.ac.stiki.doleno.absenin.data.entity.Event
import id.ac.stiki.doleno.absenin.databinding.FragmentAllEventBinding
import id.ac.stiki.doleno.absenin.view.participant.ui.all_event.AllEventAdapter.AllEventAdapterCallback

@AndroidEntryPoint
class AllEventFragment : Fragment() {
    private lateinit var binding: FragmentAllEventBinding
    private lateinit var viewModel: AllEventViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllEventBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this)[AllEventViewModel::class.java]
        viewModel.fetchMyEvent()

        binding.rvAllevent.layoutManager = LinearLayoutManager(this.requireContext())

        viewModel.myEventState.observeForever {
            run {
                binding.loading.layoutLoading.visibility = View.GONE
                binding.empty.layoutEmpty.visibility = View.GONE
                binding.error.layoutError.visibility = View.GONE
                binding.rvAllevent.visibility = View.GONE
                when (it) {
                    AllEventState.LOADING -> binding.loading.layoutLoading.visibility = View.VISIBLE
                    AllEventState.SUCCESS -> {
                        if (viewModel.isListEventEmpty()) {
                            binding.empty.layoutEmpty.visibility = View.VISIBLE
                        } else {
                            binding.rvAllevent.visibility = View.VISIBLE
                        }
                    }
                    AllEventState.FAILED -> binding.error.layoutError.visibility = View.VISIBLE
                }
            }
        }

        viewModel.events.observeForever {
            run {
                val adapter = AllEventAdapter(it, object : AllEventAdapterCallback {
                    override fun onClick(event: Event) {
                        TODO("Not yet implemented")
                    }
                })
                binding.rvAllevent.adapter = adapter
            }
        }

        return binding.root
    }
}