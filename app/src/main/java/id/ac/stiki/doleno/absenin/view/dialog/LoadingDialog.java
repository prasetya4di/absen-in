package id.ac.stiki.doleno.absenin.view.dialog;

import android.app.Activity;
import android.graphics.Color;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class LoadingDialog {
    private final Activity activity;
    private SweetAlertDialog alertDialog;

    public LoadingDialog(Activity myactivity) {
        activity = myactivity;
    }

    public void show() {
        SweetAlertDialog loadingDialog = new SweetAlertDialog(activity, SweetAlertDialog.PROGRESS_TYPE);
        loadingDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        loadingDialog.setTitleText("Loading");
        loadingDialog.setCancelable(false);
        alertDialog = loadingDialog;
        alertDialog.show();
    }


    public void dismiss() {
        alertDialog.dismiss();
    }
}
