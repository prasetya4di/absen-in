package id.ac.stiki.doleno.absenin.view.dialog;

import android.app.Activity;

import cn.pedant.SweetAlert.SweetAlertDialog;
import id.ac.stiki.doleno.absenin.R;

public class SuccessDialog {
    private final Activity activity;

    public SuccessDialog(Activity myactivity) {
        activity = myactivity;
    }

    public void show(String text) {
        SweetAlertDialog loadingDialog = new SweetAlertDialog(activity, SweetAlertDialog.SUCCESS_TYPE);
        loadingDialog.setTitleText(text);
        loadingDialog.setCancelable(false);
        loadingDialog.setConfirmButton(activity.getString(R.string.exit_text), sweetAlertDialog -> {
            sweetAlertDialog.dismiss();
            activity.onBackPressed();
        });
        loadingDialog.show();
    }
}
