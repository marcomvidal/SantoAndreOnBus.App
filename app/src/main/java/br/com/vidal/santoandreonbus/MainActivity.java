package br.com.vidal.santoandreonbus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.vidal.santoandreonbus.adapters.LinesAdapter;
import br.com.vidal.santoandreonbus.models.Line;
import br.com.vidal.santoandreonbus.tasks.GetAllLinesTask;

public class MainActivity extends AppCompatActivity {

    private List<Line> lines;
    private ListView linesList;

    private AdapterView.OnItemClickListener listViewItemClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(getApplicationContext(), LineActivity.class)
                    .putExtra("lineDenomination", lines.get(position).getDenomination());

            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setViews();
        GetAllLinesTask task = new GetAllLinesTask(this);
        task.execute();
    }

    private void setViews() {
        linesList = findViewById(R.id.linesId);
        linesList.setOnItemClickListener(listViewItemClick);
    }

    public void retrieveAllLinesFromAsyncTask(List<Line> lines) {
        this.lines = lines;
        linesList.setAdapter(new LinesAdapter(this, lines));
    }
}
