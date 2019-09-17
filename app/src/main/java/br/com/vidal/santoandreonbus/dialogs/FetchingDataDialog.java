package br.com.vidal.santoandreonbus.dialogs;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import br.com.vidal.santoandreonbus.R;

public class FetchingDataDialog extends ProgressDialog {

    public FetchingDataDialog(Context context) { super(context); }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle(getContext().getResources().getString(R.string.dialog_fetching_api_title));
        setMessage(getContext().getResources().getString(R.string.dialog_fetching_api_message));

        super.onCreate(savedInstanceState);
    }
}
