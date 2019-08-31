package br.com.vidal.santoandreonbus;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import br.com.vidal.santoandreonbus.models.Line;

public class GeneralFragment extends Fragment {

    public TextView vehicles;
    public TextView lineDenomination;
    public TextView company;
    public TextView prefixes;
    public TextView headway;
    public ListView interestPoints;
    private Line line;

    public GeneralFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        if (arguments != null) { this.line = (Line) getArguments().getSerializable("line"); }

        return inflater.inflate(R.layout.fragment_general, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setActionBarText();
        setViews(view);
        populateViews();
        fillInterestPoints();
    }

    private void setViews(View view) {
        vehicles = view.findViewById(R.id.vehicleId);
        lineDenomination = view.findViewById(R.id.lineDenominationId);
        headway = view.findViewById(R.id.headwayId);
        company = view.findViewById(R.id.companyId);
        prefixes = view.findViewById(R.id.prefixId);
        interestPoints = view.findViewById(R.id.interestPointsId);
    }

    private void populateViews() {
        lineDenomination.setText(line.getDenomination());
        company.setText(line.company.name);
        prefixes.setText(line.getPrefixesSeparatedByComma());
        vehicles.setText(line.getVehiclesSeparatedByComma());

        headway.setText(String.format(
                getContext().getString(R.string.placeholder_headway),
                String.valueOf(line.peakHeadway)
        ));
    }

    private void setActionBarText() {
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowCustomEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setCustomView(R.layout.line_action_bar);
        View view = ((AppCompatActivity)getActivity()).getSupportActionBar().getCustomView();

        TextView denomination = view.findViewById(R.id.barLineDenominationId);
        TextView fromwards = view.findViewById(R.id.barLineFromwardsId);
        TextView towards = view.findViewById(R.id.barLineTowardsId);

        denomination.setText(line.getDenomination());
        fromwards.setText(line.fromwards);
        towards.setText(line.towards);
    }

    private void fillInterestPoints() {
        interestPoints.setAdapter(new ArrayAdapter<>(
                getContext(),
                R.layout.interest_points_listview,
                line.interestPoints)
        );
    }
}
