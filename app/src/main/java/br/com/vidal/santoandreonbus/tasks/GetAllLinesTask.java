package br.com.vidal.santoandreonbus.tasks;


import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import br.com.vidal.santoandreonbus.LinesRetrievableActivity;
import br.com.vidal.santoandreonbus.R;
import br.com.vidal.santoandreonbus.models.Line;
import br.com.vidal.santoandreonbus.utilities.APIClient;


public class GetAllLinesTask extends AsyncTask<Void, Void, List<Line>> {

    private WeakReference<LinesRetrievableActivity> reference;
    private boolean showProgressBar;
    private APIClient client;
    private Gson gson;
    private ProgressDialog dialog;

    public GetAllLinesTask(LinesRetrievableActivity activity, boolean showProgressBar) {
        this.reference = new WeakReference<>(activity);
        this.showProgressBar = showProgressBar;
        this.client = new APIClient();
        this.gson = new Gson();
    }

    @Override
    protected void onPreExecute() {
        if (!showProgressBar) { return; }

        LinesRetrievableActivity activity = reference.get();
        this.dialog = new ProgressDialog(activity);
        dialog.setTitle(activity.getResources().getString(R.string.progress_title));
        dialog.setMessage(activity.getResources().getString(R.string.progress_message));
        dialog.show();
    }

    @Override
    protected List<Line> doInBackground(Void ...params) {
        String urn = "lines/";

        try {
            String json = client.get(urn);
            return gson.fromJson(json, (new TypeToken<List<Line>>() {}.getType()));
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    protected void onPostExecute(List<Line> lines) {
        if (showProgressBar) { dialog.dismiss(); }

        LinesRetrievableActivity activity = reference.get();
        activity.retrieveAllLinesCallback(lines);
    }
}
