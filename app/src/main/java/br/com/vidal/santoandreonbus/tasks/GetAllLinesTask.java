package br.com.vidal.santoandreonbus.tasks;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import java.lang.ref.WeakReference;

import br.com.vidal.santoandreonbus.LinesRetrievableActivity;
import br.com.vidal.santoandreonbus.dialogs.FetchingDataDialog;
import br.com.vidal.santoandreonbus.models.Line;
import br.com.vidal.santoandreonbus.utilities.LineService;

public class GetAllLinesTask extends AsyncTask<Void, Void, Line[]> {

    private WeakReference<LinesRetrievableActivity> activityReference;
    private LineService service;
    private boolean showProgressBar;
    private ProgressDialog dialog;

    public GetAllLinesTask(LinesRetrievableActivity activity, boolean showProgressBar) {
        this.activityReference = new WeakReference<>(activity);
        this.showProgressBar = showProgressBar;
        this.service = new LineService();
    }

    @Override
    protected void onPreExecute() {
        if (!showProgressBar) { return; }

        this.dialog = new FetchingDataDialog(activityReference.get());
        dialog.show();
    }

    @Override
    protected Line[] doInBackground(Void ...params) {
        try {
            return service.getAllLines();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    protected void onPostExecute(Line[] lines) {
        if (showProgressBar) { dialog.dismiss(); }

        activityReference.get().retrieveAllLinesCallback(lines);
    }
}
