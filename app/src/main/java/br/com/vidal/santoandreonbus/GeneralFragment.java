package br.com.vidal.santoandreonbus;


import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import br.com.vidal.santoandreonbus.br.com.vidal.santoandreonbus.models.Line;
import br.com.vidal.santoandreonbus.br.com.vidal.santoandreonbus.parsers.LineParser;
import br.com.vidal.santoandreonbus.br.com.vidal.santoandreonbus.presenters.GeneralFragmentPresenter;
import br.com.vidal.santoandreonbus.br.com.vidal.santoandreonbus.services.LineService;


public class GeneralFragment extends Fragment {

    public TextView fromwards;
    public TextView towards;
    public TextView lineDenomination;
    public TextView headway;
    public ListView interestPoints;
    private GeneralFragmentPresenter presenter;
    private Line line;
    private LineService service;

    public GeneralFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.service = new LineService();
        this.line = service.find();
        this.presenter = new GeneralFragmentPresenter(this, getResources());

        return inflater.inflate(R.layout.fragment_general, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getViews(view);
        presenter.populateViews(line);
        presenter.populateInterestPointsList(line);
    }

    private void getViews(View view) {
        fromwards = view.findViewById(R.id.fromwardsId);
        towards = view.findViewById(R.id.towardsId);
        lineDenomination = view.findViewById(R.id.lineDenominationId);
        headway = view.findViewById(R.id.headwayId);
        interestPoints = view.findViewById(R.id.interestPointsId);
    }
}
