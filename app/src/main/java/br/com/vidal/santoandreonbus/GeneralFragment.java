package br.com.vidal.santoandreonbus;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import br.com.vidal.santoandreonbus.br.com.vidal.santoandreonbus.models.InterestPoint;
import br.com.vidal.santoandreonbus.br.com.vidal.santoandreonbus.models.Line;


public class GeneralFragment extends Fragment {

    public TextView fromwards;
    public TextView towards;
    public TextView lineDenomination;
    public TextView headway;
    public ListView interestPoints;
    private Line line;

    public GeneralFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle arguments = getArguments();

        if (arguments != null) {
            this.line = (Line) getArguments().getSerializable("line");
        }

        return inflater.inflate(R.layout.fragment_general, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setViews(view);
        populateViews();
        fillInterestPoints();
    }

    private void setViews(View view) {
        fromwards = view.findViewById(R.id.fromwardsId);
        towards = view.findViewById(R.id.towardsId);
        lineDenomination = view.findViewById(R.id.lineDenominationId);
        headway = view.findViewById(R.id.headwayId);
        interestPoints = view.findViewById(R.id.interestPointsId);
    }

    private void populateViews() {
        fromwards.setText(line.fromwards);
        towards.setText(line.towards);
        lineDenomination.setText(line.getDenomination());
        headway.setText(String.valueOf(line.peakHeadway));
    }

    private void fillInterestPoints() {
        interestPoints.setAdapter(new ArrayAdapter<>(
                getContext(),
                android.R.layout.simple_list_item_1,
                line.interestPoints)
        );
    }
}
