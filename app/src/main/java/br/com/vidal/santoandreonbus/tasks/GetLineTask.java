package br.com.vidal.santoandreonbus.tasks;


import br.com.vidal.santoandreonbus.LineActivity;
import br.com.vidal.santoandreonbus.R;
import br.com.vidal.santoandreonbus.models.Line;
import br.com.vidal.santoandreonbus.utilities.APIClient;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.google.gson.Gson;

import java.lang.ref.WeakReference;


public class GetLineTask extends AsyncTask<String, Void, Line> {

    private ProgressDialog dialog;
    private final WeakReference<LineActivity> reference;
    private APIClient client;
    private Gson gson;

    public GetLineTask(LineActivity activity) {
        this.reference = new WeakReference<>(activity);
        this.client = new APIClient();
        this.gson = new Gson();
    }

    @Override
    protected void onPreExecute() {
        LineActivity activity = reference.get();

        this.dialog = new ProgressDialog(activity);
        dialog.setTitle(activity.getResources().getString(R.string.progress_title));
        dialog.setMessage(activity.getResources().getString(R.string.progress_message));
        dialog.show();
    }

    @Override
    protected Line doInBackground(String... params) {
        String urn = "lines/" + params[0];

        try {
            String json = client.get(urn);
            return gson.fromJson(json, Line.class);
        } catch (Exception e) {
            return new Line();
        }
    }

    @Override
    protected void onPostExecute(Line line) {
        dialog.dismiss();
        LineActivity activity = reference.get();
        activity.retrieveLineFromAsyncTask(line);
    }
}
