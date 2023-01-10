package id.ac.stiki.doleno.absenin.view.dialog;

import android.app.Activity;

import cn.pedant.SweetAlert.SweetAlertDialog;
import id.ac.stiki.doleno.absenin.R;

public class ErrorDialog {
    private final Activity activity;
    private String confirmButtonText = "";
    private final SweetAlertDialog.OnSweetClickListener cancelListener;
    private final SweetAlertDialog.OnSweetClickListener confirmListener;

    public ErrorDialog(Activity myactivity, SweetAlertDialog.OnSweetClickListener cancelListener) {
        activity = myactivity;
        this.cancelListener = cancelListener;
        this.confirmListener = cancelListener;
    }

    public ErrorDialog(Activity myactivity, SweetAlertDialog.OnSweetClickListener cancelListener, SweetAlertDialog.OnSweetClickListener confirmListener) {
        activity = myactivity;
        this.cancelListener = cancelListener;
        this.confirmListener = confirmListener;
    }

    public ErrorDialog(Activity activity, String confirmButtonText, SweetAlertDialog.OnSweetClickListener cancelListener, SweetAlertDialog.OnSweetClickListener confirmListener) {
        this.activity = activity;
        this.confirmButtonText = confirmButtonText;
        this.cancelListener = cancelListener;
        this.confirmListener = confirmListener;
    }

    public void show(String errorText) {
        SweetAlertDialog errorDialog = new SweetAlertDialog(activity, SweetAlertDialog.ERROR_TYPE);
        errorDialog.setTitleText(errorText);
        errorDialog.setCancelable(false);
        errorDialog.setCancelButton(activity.getString(R.string.invalid_code_exit), cancelListener);
        if (!confirmButtonText.isEmpty()) {
            errorDialog.setConfirmButton(confirmButtonText, confirmListener);
        } else {
            errorDialog.hideConfirmButton();
        }

        errorDialog.show();
    }
}
