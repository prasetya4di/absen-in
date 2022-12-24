package id.ac.stiki.doleno.absenin.view.dialog;

import android.app.Activity;

import cn.pedant.SweetAlert.SweetAlertDialog;
import id.ac.stiki.doleno.absenin.R;

public class ConfirmDialog {
    private final Activity activity;

    public ConfirmDialog(Activity activity) {
        this.activity = activity;
    }

    public void show(String message, SweetAlertDialog.OnSweetClickListener listener) {
        SweetAlertDialog loadingDialog = new SweetAlertDialog(activity, SweetAlertDialog.NORMAL_TYPE);
        loadingDialog.setTitleText(message);
        loadingDialog.setCancelText(activity.getString(R.string.txt_no));
        loadingDialog.setConfirmButton(activity.getString(R.string.txt_yes), listener);
        loadingDialog.show();
    }
}
