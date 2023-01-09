package id.ac.stiki.doleno.absenin.view.admin.ui.my_event_detail

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import id.ac.stiki.doleno.absenin.R
import id.ac.stiki.doleno.absenin.data.entity.Event
import id.ac.stiki.doleno.absenin.databinding.ActivityMyEventDetailBinding
import id.ac.stiki.doleno.absenin.util.date.DateUtil
import java.util.*
import android.R as RParent

@AndroidEntryPoint
class MyEventDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyEventDetailBinding
    private lateinit var viewModel: MyEventDetailViewModel
    private var event: Event? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyEventDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        event = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra("selected_event", Event::class.java)
        } else {
            intent.getParcelableExtra("selected_event")
        }

        viewModel = ViewModelProvider(this)[MyEventDetailViewModel::class.java]
        event?.let {
            viewModel.getEventParticipant(it)
            binding.imgQrCode.setImageBitmap(viewModel.generateQrCode(it))
            setData()
        }

        viewModel.myEventDetailViewState.observe(this) {
            when (it) {
                MyEventDetailViewState.LOADING_PARTICIPANT -> binding.layoutLoadingParticipant.layoutLoading.visibility =
                    View.VISIBLE
                MyEventDetailViewState.SUCCESS -> {
                    binding.layoutLoadingParticipant.layoutLoading.visibility = View.GONE
                    binding.layoutEmptyParticipant.layoutEmpty.visibility = View.GONE
                    binding.listViewParticipants.visibility = View.GONE
                    if (viewModel.participants.isEmpty()) {
                        binding.layoutEmptyParticipant.layoutEmpty.visibility = View.VISIBLE
                        binding.txtTotalParticipant.text = ""
                    } else {
                        val arrayAdapter = ArrayAdapter(
                            this,
                            RParent.layout.simple_list_item_1,
                            viewModel.participants.map { participant -> participant.name })
                        binding.listViewParticipants.adapter = arrayAdapter
                        binding.txtTotalParticipant.text =
                            getString(R.string.txt_total_participants, viewModel.participants.size)
                        binding.listViewParticipants.visibility = View.VISIBLE
                    }
                }
                else -> print("No state found")
            }
        }

        binding.btnDownloadQrCode.setOnClickListener {
            event?.let { event ->
                viewModel.downloadQrCode(event)
                Toast.makeText(this, getString(R.string.txt_qr_code_downloaded), Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun setData() {
        binding.txtTitle.text = event?.eventTitle
        binding.etTitle.setText(event?.eventTitle)
        binding.etDescription.setText(event?.eventDescription)
        binding.etLocationName.setText(event?.locationName)
        binding.etDate.setText(DateUtil.dateToString(event?.eventDate ?: Date()))
    }
}