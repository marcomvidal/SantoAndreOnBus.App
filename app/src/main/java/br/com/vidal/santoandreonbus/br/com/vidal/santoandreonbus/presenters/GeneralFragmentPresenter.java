package br.com.vidal.santoandreonbus.br.com.vidal.santoandreonbus.presenters;

import android.app.Activity;
import android.app.Service;
import android.content.res.Resources;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import br.com.vidal.santoandreonbus.GeneralFragment;
import br.com.vidal.santoandreonbus.R;
import br.com.vidal.santoandreonbus.br.com.vidal.santoandreonbus.models.InterestPoint;
import br.com.vidal.santoandreonbus.br.com.vidal.santoandreonbus.models.Line;
import br.com.vidal.santoandreonbus.br.com.vidal.santoandreonbus.services.LineService;

public class GeneralFragmentPresenter {

    private GeneralFragment fragment;
    private Resources resources;

    public GeneralFragmentPresenter(GeneralFragment fragment,
                                    Resources resources) {
        this.fragment = fragment;
        this.resources = resources;
    }

    public void populateViews(Line line) {
        String headwayText = String.format(resources.getString(R.string.placeholder_headway),
                line.peakHeadway,
                line.offpeakHeadway);

        fragment.fromwards.setText(line.fromwards);
        fragment.towards.setText(line.towards);
        fragment.lineDenomination.setText(line.getDenomination());
        fragment.headway.setText(headwayText);
    }

    public void populateInterestPointsList(Line line) {
        if (fragment.getActivity() == null) { return; }

        fragment.interestPoints.setAdapter(new ArrayAdapter<>(fragment.getActivity(),
                R.layout.interest_points_listview,
                line.interestPoints)
        );
    }
}
