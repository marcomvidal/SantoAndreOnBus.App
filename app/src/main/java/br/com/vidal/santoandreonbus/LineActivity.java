package br.com.vidal.santoandreonbus;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import br.com.vidal.santoandreonbus.models.Line;
import br.com.vidal.santoandreonbus.tasks.GetLineTask;

public class LineActivity extends AppCompatActivity {

    private Line line;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_general:
                    placeFragment(new GeneralFragment());
                    return true;
                case R.id.navigation_itinerary:
                    placeFragment(new ItineraryFragment());
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line);
        setViews();
        fetchData(getIntent());
    }

    private void fetchData(Intent intent) {
        if (!intent.hasExtra("lineDenomination")) { return; }
        GetLineTask task = new GetLineTask(this);

        task.execute(intent.getExtras().getString("lineDenomination"));
    }

    public void retrieveLineFromAsyncTask(Line line) {
        this.line = line;
        placeFragment(new GeneralFragment());
    }

    private void setViews() {
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void placeFragment(Fragment fragment) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("line", line);
        fragment.setArguments(bundle);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainerId, fragment)
                .commit();
    }
}
