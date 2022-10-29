package com.pras.absenin.view.loading;

import android.app.Activity;
import android.graphics.Color;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class LoadingDialog {
    private final Activity activity;
    private SweetAlertDialog alertDialog;

    public LoadingDialog(Activity myactivity) {
        activity = myactivity;
    }

    public void startLoadingDialog() {
        SweetAlertDialog loadingDialog = new SweetAlertDialog(activity, SweetAlertDialog.PROGRESS_TYPE);
        loadingDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        loadingDialog.setTitleText("Loading");
        loadingDialog.setCancelable(false);
        alertDialog = loadingDialog;
        alertDialog.show();
    }


    public void dismisDialog() {
        alertDialog.dismiss();
    }
}
