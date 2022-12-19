package id.ac.stiki.doleno.absenin.view.dialog;

import android.app.Activity;

import cn.pedant.SweetAlert.SweetAlertDialog;
import id.ac.stiki.doleno.absenin.R;

public class ErrorDialog {
    private final Activity activity;
    private final SweetAlertDialog.OnSweetClickListener cancelListener;
    private final SweetAlertDialog.OnSweetClickListener confirmListener;

    public ErrorDialog(Activity myactivity, SweetAlertDialog.OnSweetClickListener cancelListener, SweetAlertDialog.OnSweetClickListener confirmListener) {
        activity = myactivity;
        this.cancelListener = cancelListener;
        this.confirmListener = confirmListener;
    }

    public void show(String errorText) {
        SweetAlertDialog loadingDialog = new SweetAlertDialog(activity, SweetAlertDialog.ERROR_TYPE);
        loadingDialog.setTitleText(errorText);
        loadingDialog.setCancelable(false);
        loadingDialog.setCancelButton(activity.getString(R.string.invalid_code_exit), cancelListener);
        loadingDialog.setConfirmButton(activity.getString(R.string.invalid_code_rescan), confirmListener);
        loadingDialog.show();
    }
}
