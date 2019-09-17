package br.com.vidal.santoandreonbus.tasks;

import br.com.vidal.santoandreonbus.LineActivity;
import br.com.vidal.santoandreonbus.dialogs.FetchingDataDialog;
import br.com.vidal.santoandreonbus.models.Line;
import br.com.vidal.santoandreonbus.utilities.LineService;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import java.lang.ref.WeakReference;

public class GetLineTask extends AsyncTask<String, Void, Line> {

    private final WeakReference<LineActivity> activityReference;
    private LineService service;
    private ProgressDialog dialog;

    public GetLineTask(LineActivity activity) {
        this.activityReference = new WeakReference<>(activity);
        this.service = new LineService();
    }

    @Override
    protected void onPreExecute() {
        this.dialog = new FetchingDataDialog(activityReference.get());
        dialog.show();
    }

    @Override
    protected Line doInBackground(String... params) {
        try {
            return service.getLine(params[0]);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    protected void onPostExecute(Line line) {
        dialog.dismiss();
        activityReference.get().retrieveLineCallback(line);
    }
}
