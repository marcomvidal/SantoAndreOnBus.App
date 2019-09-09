package br.com.vidal.santoandreonbus.tasks;


import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import br.com.vidal.santoandreonbus.LineActivity;
import br.com.vidal.santoandreonbus.MainActivity;
import br.com.vidal.santoandreonbus.R;
import br.com.vidal.santoandreonbus.models.Line;
import br.com.vidal.santoandreonbus.utilities.APIClient;


public class GetAllLinesTask extends AsyncTask<Void, Void, List<Line>> {

    private ProgressDialog dialog;
    private final WeakReference<MainActivity> reference;
    private APIClient client;
    private Gson gson;

    public GetAllLinesTask(MainActivity activity) {
        this.reference = new WeakReference<>(activity);
        this.client = new APIClient();
        this.gson = new Gson();
    }

    @Override
    protected void onPreExecute() {
        MainActivity activity = reference.get();

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
        dialog.dismiss();
        MainActivity activity = reference.get();
        activity.retrieveAllLinesFromAsyncTask(lines);
    }
}
