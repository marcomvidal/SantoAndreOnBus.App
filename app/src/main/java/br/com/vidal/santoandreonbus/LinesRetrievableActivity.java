package br.com.vidal.santoandreonbus;

import android.support.v7.app.AppCompatActivity;

import java.util.List;

import br.com.vidal.santoandreonbus.models.Line;

public abstract class LinesRetrievableActivity extends AppCompatActivity {
    public abstract void retrieveAllLinesCallback(Line[] lines);
}
