package br.com.vidal.santoandreonbus.br.com.vidal.santoandreonbus.tasks;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import br.com.vidal.santoandreonbus.R;
import br.com.vidal.santoandreonbus.br.com.vidal.santoandreonbus.models.InterestPoint;
import br.com.vidal.santoandreonbus.br.com.vidal.santoandreonbus.models.Line;
import br.com.vidal.santoandreonbus.br.com.vidal.santoandreonbus.parsers.InterestPointParser;
import br.com.vidal.santoandreonbus.br.com.vidal.santoandreonbus.parsers.JSONParseable;
import br.com.vidal.santoandreonbus.br.com.vidal.santoandreonbus.parsers.LineParser;
import br.com.vidal.santoandreonbus.br.com.vidal.santoandreonbus.presenters.GeneralFragmentPresenter;
import br.com.vidal.santoandreonbus.br.com.vidal.santoandreonbus.utilities.APIClient;

public class GetLineTask extends AsyncTask<String, Object, String> {

    private APIClient client;
    private GeneralFragmentPresenter presenter;
    private ProgressDialog progress;
    private JSONParseable<Line> lineParser = new LineParser();
    private JSONParseable<List<InterestPoint>> interestParser = new InterestPointParser();

    public GetLineTask(APIClient client,
                       GeneralFragmentPresenter presenter) {
        this.client = client;
        this.presenter = presenter;
    }

    @Override
    protected void onPreExecute() {
        //super.onPreExecute();
        progress = new ProgressDialog(presenter.getContext());
        progress.setTitle("TITLE");
        progress.setMessage("MESSAGE");

        try {
            progress.show();
        } catch (Exception e) {
            Log.i("ERRIM", e.getMessage());
        }
    }

    @Override
    protected String doInBackground(String... params) {
        String urn = "lines/" + params[0];
        String json = "";

        try {
            json = client.get(urn);
        } catch (Exception e) {
            Toast.makeText(presenter.getContext(),
                    R.string.network_connection_failure,
                    Toast.LENGTH_LONG
            ).show();
        }

        return json;
    }

    @Override
    protected void onPostExecute(String json) {
        Line line = lineParser.toInstance(json);

        InterestPointParser parser = new InterestPointParser();
        List<InterestPoint> interestPoints = interestParser.toInstance(json);
        line.interestPoints = interestPoints;

        presenter.populateViews(line);
        presenter.populateInterestPointsList(line.interestPoints);
        progress.dismiss();
    }
}
