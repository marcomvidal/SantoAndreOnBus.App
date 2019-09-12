package br.com.vidal.santoandreonbus;

import android.content.Intent;
import android.os.Bundle;

import br.com.vidal.santoandreonbus.models.Line;
import br.com.vidal.santoandreonbus.tasks.GetAllLinesTask;
import br.com.vidal.santoandreonbus.dialogs.ConnectionFailedDialog;

public class SplashScreenActivity extends LinesRetrievableActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        GetAllLinesTask task = new GetAllLinesTask(this, false);
        task.execute();
    }

    @Override
    public void retrieveAllLinesCallback(Line[] lines) {
        if (lines == null) { (new ConnectionFailedDialog(this)).show(); return; }

        Intent intent = new Intent(this, MainActivity.class)
                .putExtra("lines", lines);

        startActivity(intent);
    }
}
