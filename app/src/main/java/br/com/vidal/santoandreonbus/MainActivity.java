package br.com.vidal.santoandreonbus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import br.com.vidal.santoandreonbus.models.Line;
import br.com.vidal.santoandreonbus.tasks.GetAllLinesTask;

public class MainActivity extends AppCompatActivity {

    private ListView lines;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setViews();
        GetAllLinesTask task = new GetAllLinesTask(this);
        task.execute();
    }

    private void setViews() { lines = findViewById(R.id.linesId); }

    public void retrieveAllLinesFromAsyncTask(List<Line> lines) {
        this.lines.setAdapter(
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lines)
        );
    }
}
