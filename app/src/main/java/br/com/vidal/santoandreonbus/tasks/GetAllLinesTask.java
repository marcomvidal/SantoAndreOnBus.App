package br.com.vidal.santoandreonbus.tasks;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.google.gson.Gson;

import java.lang.ref.WeakReference;

import br.com.vidal.santoandreonbus.LinesRetrievableActivity;
import br.com.vidal.santoandreonbus.dialogs.FetchingAPIDialog;
import br.com.vidal.santoandreonbus.models.Line;
import br.com.vidal.santoandreonbus.utilities.APIClient;

public class GetAllLinesTask extends AsyncTask<Void, Void, Line[]> {

    private static final String ENDPOINT = "lines/";
    private WeakReference<LinesRetrievableActivity> activityReference;
    private boolean showProgressBar;
    private APIClient client;
    private Gson gson;
    private ProgressDialog dialog;

    public GetAllLinesTask(LinesRetrievableActivity activity, boolean showProgressBar) {
        this.activityReference = new WeakReference<>(activity);
        this.showProgressBar = showProgressBar;
        this.client = new APIClient();
        this.gson = new Gson();
    }

    @Override
    protected void onPreExecute() {
        if (!showProgressBar) { return; }

        this.dialog = new FetchingAPIDialog(activityReference.get());
        dialog.show();
    }

    @Override
    protected Line[] doInBackground(Void ...params) {
        try {
            String json = client.get(ENDPOINT);
            return gson.fromJson(json, Line[].class);
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
