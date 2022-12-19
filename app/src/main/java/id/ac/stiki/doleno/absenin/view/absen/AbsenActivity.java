package id.ac.stiki.doleno.absenin.view.absen;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import com.google.common.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutionException;

import dagger.hilt.android.AndroidEntryPoint;
import id.ac.stiki.doleno.absenin.R;
import id.ac.stiki.doleno.absenin.databinding.ActivityAbsenBinding;
import id.ac.stiki.doleno.absenin.util.qrcode.QRCodeFoundListener;
import id.ac.stiki.doleno.absenin.util.qrcode.QRCodeImageAnalyzer;
import id.ac.stiki.doleno.absenin.view.dialog.ErrorDialog;
import id.ac.stiki.doleno.absenin.view.dialog.LoadingDialog;
import id.ac.stiki.doleno.absenin.view.dialog.SuccessDialog;

@AndroidEntryPoint
public class AbsenActivity extends AppCompatActivity {
    private ActivityAbsenBinding binding;
    private AbsenViewModel absenViewModel;
    private LoadingDialog loadingDialog;
    private SuccessDialog successDialog;
    private ErrorDialog errorDialog;

    private PreviewView cameraView;
    private ProcessCameraProvider processCameraProvider;
    private ListenableFuture<ProcessCameraProvider> cameraProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAbsenBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        absenViewModel = new ViewModelProvider(this).get(AbsenViewModel.class);

        cameraView = binding.mainCameraView;
        loadingDialog = new LoadingDialog(this);
        successDialog = new SuccessDialog(this);
        errorDialog = new ErrorDialog(this, sweetAlertDialog -> {
            sweetAlertDialog.dismiss();
            onBackPressed();
        }, sweetAlertDialog -> {
            sweetAlertDialog.dismiss();
            startCamera();
        });

        cameraProvider = ProcessCameraProvider.getInstance(this);

        startCamera();

        absenViewModel.resultStatus.observeForever(status -> {
            switch (status) {
                case VALID:
                    loadingDialog.dismiss();
                    successDialog.show();
                    break;
                case INVALID:
                    loadingDialog.dismiss();
                    errorDialog.show(getString(R.string.invalid_code_title_text));
                    break;
                case INVALID_LOCATION:
                    loadingDialog.dismiss();
                    errorDialog.show(getString(R.string.invalid_location_text));
                    break;
            }
        });
    }

    private void startCamera() {
        cameraProvider.addListener(() -> {
            try {
                processCameraProvider = this.cameraProvider.get();
                bindCameraPreview(processCameraProvider);
            } catch (ExecutionException | InterruptedException e) {
                Toast.makeText(this, "Error starting camera " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }, ContextCompat.getMainExecutor(this));
    }

    private void bindCameraPreview(@NonNull ProcessCameraProvider cameraProvider) {
        cameraView.setImplementationMode(PreviewView.ImplementationMode.PERFORMANCE);

        Preview preview = new Preview.Builder().build();

        CameraSelector cameraSelector = new CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                .build();

        preview.setSurfaceProvider(cameraView.getSurfaceProvider());

        ImageAnalysis imageAnalysis = new ImageAnalysis.Builder()
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                .build();

        imageAnalysis.setAnalyzer(ContextCompat.getMainExecutor(this), new QRCodeImageAnalyzer(new QRCodeFoundListener() {
            @Override
            public void onQRCodeFound(String result) {
                cameraProvider.unbindAll();
                absenViewModel.doAbsent(result);
                loadingDialog.show();
            }

            @Override
            public void qrCodeNotFound() {
                Log.d("Scan result", "Qr code not found");
            }
        }));

        cameraProvider.bindToLifecycle(this, cameraSelector, imageAnalysis, preview);
    }
}
