package br.com.vidal.santoandreonbus.br.com.vidal.santoandreonbus.tasks;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import br.com.vidal.santoandreonbus.R;
import br.com.vidal.santoandreonbus.br.com.vidal.santoandreonbus.models.Line;
import br.com.vidal.santoandreonbus.br.com.vidal.santoandreonbus.utilities.APIClient;
import br.com.vidal.santoandreonbus.br.com.vidal.santoandreonbus.utilities.JsonParser;
import br.com.vidal.santoandreonbus.br.com.vidal.santoandreonbus.utilities.LineFillerObserver;

public class GetLineTask extends AsyncTask<Integer, Object, Line> {

    private LineFillerObserver observer;
    private APIClient client;
    private JsonParser parser;
    private ProgressDialog dialog;

    public GetLineTask(LineFillerObserver observer) {
        this.observer = observer;
        this.client = new APIClient();
        this.parser = new JsonParser();
    }

    @Override
    protected void onPreExecute() {
        Fragment fragment = (Fragment) observer;
        dialog = new ProgressDialog(fragment.getActivity());
        dialog.setTitle(fragment.getResources().getString(R.string.progress_title));
        dialog.setMessage(fragment.getResources().getString(R.string.progress_message));
        dialog.show();
    }

    @Override
    protected Line doInBackground(Integer... params) {
        String urn = "lines/" + params[0].toString();

        try {
            String json = client.get(urn);
            Line line = parser.toLine(json);
            line.interestPoints = parser.toInterestPointsList(json);

            return line;
        } catch (Exception e) {
            Fragment fragment = (Fragment) observer;

            Toast.makeText(fragment.getContext(),
                    fragment.getResources().getString(R.string.network_connection_failure),
                    Toast.LENGTH_LONG
            ).show();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Line line) {
        observer.fillViews(line);
        dialog.dismiss();
    }

}
