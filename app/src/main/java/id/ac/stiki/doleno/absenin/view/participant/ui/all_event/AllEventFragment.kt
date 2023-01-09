package id.ac.stiki.doleno.absenin.view.participant.ui.all_event

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
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
import id.ac.stiki.doleno.absenin.view.participant.ui.event_join.EventJoinActivity

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

        viewModel.myEventState.observe(viewLifecycleOwner) {
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
                        val adapter =
                            AllEventAdapter(viewModel.events, object : AllEventAdapterCallback {
                                override fun onClick(event: Event) {
                                    val intent =
                                        Intent(
                                            this@AllEventFragment.activity,
                                            EventJoinActivity::class.java
                                        )
                                    intent.putExtra("selected_event", event as Parcelable)
                                    startActivity(intent)
                                }
                            })
                        binding.rvAllevent.adapter = adapter
                        binding.rvAllevent.visibility = View.VISIBLE
                    }
                }
                AllEventState.FAILED -> binding.error.layoutError.visibility = View.VISIBLE
            }
        }
        return binding.root
    }
}