package com.pras.absenin.view.loading;

import android.app.Activity;
import android.view.LayoutInflater;

import androidx.appcompat.app.AlertDialog;

import com.pras.absenin.R;

public class LoadingDialog {
    private final Activity activity;
    private AlertDialog alertDialog;

    public LoadingDialog(Activity myactivity) {
        activity = myactivity;
    }

    public void startLoadingDialog() {
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.loading_dialog, null));
        builder.setCancelable(false);


        alertDialog = builder.create();
        alertDialog.show();
    }


    public void dismisDialog() {
        alertDialog.dismiss();
    }
}
