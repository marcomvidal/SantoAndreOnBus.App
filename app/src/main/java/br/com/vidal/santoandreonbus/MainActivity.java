package br.com.vidal.santoandreonbus;

import android.support.v7.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.vidal.santoandreonbus.utilities.LinesAdapter;
import br.com.vidal.santoandreonbus.models.Line;
import br.com.vidal.santoandreonbus.tasks.GetAllLinesTask;

public class MainActivity extends LinesRetrievableActivity {

    private List<Line> lines;
    private ListView linesList;
    private LinesAdapter adapter;

    private AdapterView.OnItemClickListener listViewItemClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(getApplicationContext(), LineActivity.class)
                    .putExtra("lineDenomination", lines.get(position).getDenomination());

            startActivity(intent);
        }
    };

    private TextWatcher lineSearch = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            MainActivity.this.adapter.getFilter().filter(s);
        }

        @Override
        public void afterTextChanged(Editable s) {}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setActionBarText();
        setViews();
        fetchData(getIntent());
    }

    private void setActionBarText() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar == null) { return; }

        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(R.layout.action_bar_main);
    }

    private void fetchData(Intent intent) {
        if (intent.hasExtra("lines")) {
            Line[] lines = (Line[]) intent.getSerializableExtra("lines");
            retrieveAllLinesCallback(lines);
        }

        GetAllLinesTask task = new GetAllLinesTask(this, true);
        task.execute();
    }

    @Override
    public void retrieveAllLinesCallback(Line[] lines) {
        this.lines = new ArrayList<>(Arrays.asList(lines));
        adapter = new LinesAdapter(this, this.lines);
        linesList.setAdapter(adapter);
    }

    private void setViews() {
        linesList = findViewById(R.id.linesId);
        linesList.setOnItemClickListener(listViewItemClick);

        EditText searchField = findViewById(R.id.searchFieldId);
        searchField.addTextChangedListener(lineSearch);
    }
}
