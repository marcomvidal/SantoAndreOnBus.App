package br.com.vidal.santoandreonbus.br.com.vidal.santoandreonbus.tasks;


import br.com.vidal.santoandreonbus.MainActivity;
import br.com.vidal.santoandreonbus.br.com.vidal.santoandreonbus.models.InterestPoint;
import br.com.vidal.santoandreonbus.br.com.vidal.santoandreonbus.models.Line;
import br.com.vidal.santoandreonbus.br.com.vidal.santoandreonbus.utilities.APIClient;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.ref.WeakReference;
import java.lang.reflect.Type;
import java.util.ArrayList;


public class GetLineTask extends AsyncTask<String, Object, Line> {

    private ProgressDialog dialog;
    private final WeakReference<MainActivity> reference;
    private APIClient client;
    private Gson gson;

    public GetLineTask(MainActivity activity) {
        this.reference = new WeakReference<>(activity);
        this.client = new APIClient();
        this.gson = new Gson();
    }

    @Override
    protected void onPreExecute() {
        this.dialog = new ProgressDialog(reference.get());
        dialog.setTitle("Informações da linha");
        dialog.setMessage("Carregando. Por favor, aguarde...");
        dialog.show();
    }

    @Override
    protected Line doInBackground(String... params) {
        String urn = "lines/" + params[0];

        try {
            String json = client.get(urn);
            Line line = gson.fromJson(json, Line.class);

            return line;
        } catch (Exception e) {
            return new Line();
        }
    }

    @Override
    protected void onPostExecute(Line line) {
        dialog.dismiss();
        MainActivity activity = reference.get();
        activity.retrieveLineFromAsyncTask(line);
    }
}
