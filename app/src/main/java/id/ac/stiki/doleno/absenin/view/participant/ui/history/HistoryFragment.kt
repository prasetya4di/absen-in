package id.ac.stiki.doleno.absenin.view.participant.ui.history

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
import id.ac.stiki.doleno.absenin.data.entity.Absent
import id.ac.stiki.doleno.absenin.databinding.FragmentHistoryBinding
import id.ac.stiki.doleno.absenin.view.participant.ui.history_detail.HistoryDetailActivity

@AndroidEntryPoint
class HistoryFragment : Fragment() {
    private lateinit var binding: FragmentHistoryBinding
    private lateinit var viewModel: HistoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistoryBinding.inflate(inflater, container, false)

        binding.rvHistory.layoutManager = LinearLayoutManager(this.activity)

        viewModel = ViewModelProvider(this)[HistoryViewModel::class.java]
        viewModel.getAbsent()

        viewModel.historyViewState.observe(viewLifecycleOwner) {
            binding.loading.layoutLoading.visibility = View.GONE
            binding.empty.layoutEmpty.visibility = View.GONE
            binding.error.layoutError.visibility = View.GONE
            binding.rvHistory.visibility = View.GONE
            when (it) {
                HistoryViewState.LOADING -> binding.loading.layoutLoading.visibility = View.VISIBLE
                HistoryViewState.SUCCESS -> {
                    if (viewModel.listAbsent.isEmpty()) {
                        binding.empty.layoutEmpty.visibility = View.VISIBLE
                        binding.rvHistory.visibility = View.GONE
                    } else {
                        binding.empty.layoutEmpty.visibility = View.GONE
                        binding.rvHistory.visibility = View.VISIBLE
                        val historyAdapter =
                            HistoryAdapter(
                                viewModel.listAbsent,
                                object : HistoryAdapter.HistoryAdapterCallback {
                                    override fun onClick(absent: Absent) {
                                        val intent = Intent(
                                            this@HistoryFragment.activity,
                                            HistoryDetailActivity::class.java
                                        )
                                        intent.putExtra("absent_data", absent as Parcelable)
                                        startActivity(intent)
                                    }
                                })
                        binding.rvHistory.adapter = historyAdapter
                    }
                }
                HistoryViewState.FAILED -> binding.error.layoutError.visibility = View.VISIBLE
            }
        }

        return binding.root
    }
}