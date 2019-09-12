package br.com.vidal.santoandreonbus.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import br.com.vidal.santoandreonbus.R;

public class ConnectionFailedDialog extends AlertDialog {

    public ConnectionFailedDialog(Context context) { super(context); }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle(R.string.dialog_failed_api_title);
        setMessage(getContext().getResources().getString(R.string.dialog_failed_api_message));
        setButton(DialogInterface.BUTTON_NEUTRAL, "Ok", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                getOwnerActivity().finishAffinity();
            }
        });

        super.onCreate(savedInstanceState);
    }
}
