package br.com.vidal.santoandreonbus.tasks;

import br.com.vidal.santoandreonbus.LineActivity;
import br.com.vidal.santoandreonbus.dialogs.FetchingAPIDialog;
import br.com.vidal.santoandreonbus.models.Line;
import br.com.vidal.santoandreonbus.utilities.APIClient;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.google.gson.Gson;

import java.lang.ref.WeakReference;

public class GetLineTask extends AsyncTask<String, Void, Line> {

    private static final String ENDPOINT = "lines/";
    private ProgressDialog dialog;
    private final WeakReference<LineActivity> activityReference;
    private APIClient client;
    private Gson gson;

    public GetLineTask(LineActivity activity) {
        this.activityReference = new WeakReference<>(activity);
        this.client = new APIClient();
        this.gson = new Gson();
    }

    @Override
    protected void onPreExecute() {
        this.dialog = new FetchingAPIDialog(activityReference.get());
        dialog.show();
    }

    @Override
    protected Line doInBackground(String... params) {
        String urn = ENDPOINT + params[0];

        try {
            String json = client.get(urn);
            return gson.fromJson(json, Line.class);
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
