package com.example.apphelper2019.ex.dialogs;


import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;

import com.example.apphelper2019.R;

/**
 * A simple {@link Fragment} subclass.
 * https://youtu.be/WWaNnaRCk_w
 */
public class MyDialogFragment extends DialogFragment {
    public static final String ALERT_TAG = "ALERT";


    public MyDialogFragment() {
        // Required empty public constructor
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = null;

        if (getTag().equals(ALERT_TAG)) {
            dialog = getAlertDialog();
        }
        return dialog;
    }

    private Dialog getAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setIcon(R.drawable.ic_error_black_24dp)
                .setTitle(R.string.title_fragment_dialog)
                .setMessage(R.string.message_fragment_dialog);
        return builder.create();
    }
}
