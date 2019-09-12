package br.com.vidal.santoandreonbus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import br.com.vidal.santoandreonbus.models.Line;
import br.com.vidal.santoandreonbus.tasks.GetAllLinesTask;

public class SplashScreenActivity extends LinesRetrievableActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        GetAllLinesTask task = new GetAllLinesTask(this, false);
        task.execute();
    }

    @Override
    public void retrieveAllLinesCallback(List<Line> lines) {
        Intent intent = new Intent(this, MainActivity.class)
                .putExtra("lines", (ArrayList) lines);

        startActivity(intent);
    }
}
