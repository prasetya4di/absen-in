package com.pras.absenin.view.dialog;

import android.app.Activity;
import android.app.Dialog;

import com.pras.absenin.R;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class SuccessDialog {
    private final Activity activity;

    public SuccessDialog(Activity myactivity) {
        activity = myactivity;
    }

    public void show() {
        SweetAlertDialog loadingDialog = new SweetAlertDialog(activity, SweetAlertDialog.SUCCESS_TYPE);
        loadingDialog.setTitleText(activity.getString(R.string.success_text));
        loadingDialog.setCancelable(false);
        loadingDialog.setConfirmButton(activity.getString(R.string.exit_text), Dialog::onBackPressed);
        loadingDialog.show();
    }
}
