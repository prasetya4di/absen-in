package id.ac.stiki.doleno.absenin.view.participant.ui.absent

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.common.util.concurrent.ListenableFuture
import dagger.hilt.android.AndroidEntryPoint
import id.ac.stiki.doleno.absenin.R
import id.ac.stiki.doleno.absenin.databinding.FragmentAbsentBinding
import id.ac.stiki.doleno.absenin.util.qrcode.QRCodeFoundListener
import id.ac.stiki.doleno.absenin.util.qrcode.QRCodeImageAnalyzer
import id.ac.stiki.doleno.absenin.util.qrcode.QRCodeResultStatus
import id.ac.stiki.doleno.absenin.view.dialog.ErrorDialog
import id.ac.stiki.doleno.absenin.view.dialog.LoadingDialog
import id.ac.stiki.doleno.absenin.view.dialog.SuccessDialog
import java.util.concurrent.ExecutionException

@AndroidEntryPoint
class AbsentFragment : Fragment() {
    private lateinit var binding: FragmentAbsentBinding
    private lateinit var viewModel: AbsentViewModel
    private lateinit var loadingDialog: LoadingDialog
    private lateinit var successDialog: SuccessDialog
    private lateinit var errorDialog: ErrorDialog

    private lateinit var cameraView: PreviewView
    private lateinit var processCameraProvider: ProcessCameraProvider
    private lateinit var cameraProvider: ListenableFuture<ProcessCameraProvider>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAbsentBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this)[AbsentViewModel::class.java]

        cameraView = binding.mainCameraView
        loadingDialog = LoadingDialog(this.activity)
        successDialog = SuccessDialog(this.activity)
        errorDialog = ErrorDialog(
            this.activity
        ) {
            it.dismiss()
            startCamera()
        }

        cameraProvider = ProcessCameraProvider.getInstance(this.requireContext())

        startCamera()

        viewModel.resultStatus.observeForever {
            kotlin.run {
                when (it) {
                    QRCodeResultStatus.VALID -> {
                        loadingDialog.dismiss()
                        successDialog.show(getString(R.string.success_text))
                    }
                    QRCodeResultStatus.INVALID -> {
                        loadingDialog.dismiss()
                        errorDialog.show(getString(R.string.invalid_code_title_text))
                    }
                    QRCodeResultStatus.INVALID_LOCATION -> {
                        loadingDialog.dismiss()
                        errorDialog.show(getString(R.string.invalid_location_text))
                    }
                }
            }
        }

        return binding.root
    }

    private fun startCamera() {
        cameraProvider.addListener({
            try {
                processCameraProvider = cameraProvider.get()
                bindCameraPreview(processCameraProvider)
            } catch (e: ExecutionException) {
                Toast.makeText(
                    this.context,
                    "Error starting camera " + e.message,
                    Toast.LENGTH_SHORT
                )
                    .show()
            } catch (e: InterruptedException) {
                Toast.makeText(
                    this.context,
                    "Error starting camera " + e.message,
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        }, ContextCompat.getMainExecutor(this.requireContext()))
    }

    private fun bindCameraPreview(cameraProvider: ProcessCameraProvider) {
        cameraView.implementationMode = PreviewView.ImplementationMode.PERFORMANCE
        val preview = Preview.Builder().build()
        val cameraSelector = CameraSelector.Builder()
            .requireLensFacing(CameraSelector.LENS_FACING_BACK)
            .build()
        preview.setSurfaceProvider(cameraView.surfaceProvider)
        val imageAnalysis = ImageAnalysis.Builder()
            .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
            .build()
        imageAnalysis.setAnalyzer(
            ContextCompat.getMainExecutor(this.requireContext()),
            QRCodeImageAnalyzer(object : QRCodeFoundListener {
                override fun onQRCodeFound(result: String) {
                    cameraProvider.unbindAll()
                    viewModel.doAbsent(result)
                    loadingDialog.show()
                }

                override fun qrCodeNotFound() {
                    Log.d("Scan result", "Qr code not found")
                }
            })
        )
        cameraProvider.bindToLifecycle(this, cameraSelector, imageAnalysis, preview)
    }
}