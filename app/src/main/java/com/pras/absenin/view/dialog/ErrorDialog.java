package com.pras.absenin.view.dialog;

import android.app.Activity;

import com.pras.absenin.R;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class ErrorDialog {
    private final Activity activity;
    private final SweetAlertDialog.OnSweetClickListener cancelListener;
    private final SweetAlertDialog.OnSweetClickListener confirmListener;

    public ErrorDialog(Activity myactivity, SweetAlertDialog.OnSweetClickListener cancelListener, SweetAlertDialog.OnSweetClickListener confirmListener) {
        activity = myactivity;
        this.cancelListener = cancelListener;
        this.confirmListener = confirmListener;
    }

    public void showErrorDialog(String errorText) {
        SweetAlertDialog loadingDialog = new SweetAlertDialog(activity, SweetAlertDialog.SUCCESS_TYPE);
        loadingDialog.setTitleText(errorText);
        loadingDialog.setCancelable(false);
        loadingDialog.setCancelButton(activity.getString(R.string.invalid_code_exit), cancelListener);
        loadingDialog.setConfirmButton(activity.getString(R.string.invalid_code_rescan), confirmListener);
        loadingDialog.show();
    }
}
