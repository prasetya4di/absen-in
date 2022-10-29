package com.pras.absenin.view.absen;

import static com.pras.absenin.util.qrcode.QRCodeResultStatus.INVALID;
import static com.pras.absenin.util.qrcode.QRCodeResultStatus.INVALID_LOCATION;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import com.google.common.util.concurrent.ListenableFuture;
import com.pras.absenin.R;
import com.pras.absenin.databinding.ActivityAbsenBinding;
import com.pras.absenin.util.qrcode.QRCodeFoundListener;
import com.pras.absenin.util.qrcode.QRCodeImageAnalyzer;
import com.pras.absenin.util.qrcode.QRCodeResultStatus;
import com.pras.absenin.view.absent_result.AbsentSuccessActivity;
import com.pras.absenin.view.dialog.LoadingDialog;

import java.util.concurrent.ExecutionException;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AbsenActivity extends AppCompatActivity {
    private ActivityAbsenBinding binding;
    private AbsenViewModel absenViewModel;
    private LoadingDialog loadingDialog;

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

        cameraProvider = ProcessCameraProvider.getInstance(this);

        startCamera();

        absenViewModel.resultStatus.observeForever(status -> {
            switch (status) {
                case VALID:
                    loadingDialog.dismisDialog();
                    startActivity(new Intent(this, AbsentSuccessActivity.class));
                    break;
                case INVALID:
                case INVALID_LOCATION:
                    loadingDialog.dismisDialog();
                    showAlertDialog(status);
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
                loadingDialog.startLoadingDialog();
            }

            @Override
            public void qrCodeNotFound() {
                Log.d("Scan result", "Qr code not found");
            }
        }));

        cameraProvider.bindToLifecycle(this, cameraSelector, imageAnalysis, preview);
    }

    private void showAlertDialog(QRCodeResultStatus status) {
        int messageId = 0;
        if (status == INVALID) {
            messageId = R.string.invalid_qr_code_text;
        } else if (status == INVALID_LOCATION) {
            messageId = R.string.invalid_location_text;
        }
        new AlertDialog
                .Builder(this)
                .setMessage(messageId)
                .setTitle(R.string.invalid_code_title_text)
                .setPositiveButton(R.string.invalid_code_rescan, (dialog, which) -> {
                    dialog.dismiss();
                    startCamera();
                })
                .setNegativeButton(R.string.invalid_code_exit, (dialog, which) -> {
                    dialog.dismiss();
                    onBackPressed();
                })
                .create()
                .show();
    }
}
