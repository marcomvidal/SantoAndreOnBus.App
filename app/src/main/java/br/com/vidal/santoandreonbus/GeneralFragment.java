package br.com.vidal.santoandreonbus;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.TooltipCompat;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import br.com.vidal.santoandreonbus.models.InterestPoint;
import br.com.vidal.santoandreonbus.models.Line;
import br.com.vidal.santoandreonbus.models.LineVehicle;
import br.com.vidal.santoandreonbus.models.Vehicle;

public class GeneralFragment extends Fragment {

    private TextView company;
    private TextView prefixes;
    private TextView headway;
    private LinearLayout interestPoints;
    private LinearLayout vehicles;
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
        drawVehicleIcons();
        fillInterestPoints();
    }

    private void setViews(View view) {
        headway = view.findViewById(R.id.headwayId);
        company = view.findViewById(R.id.companyId);
        prefixes = view.findViewById(R.id.prefixId);
        vehicles = view.findViewById(R.id.vehiclesId);
        interestPoints = view.findViewById(R.id.interestPointsId);
    }

    private void populateViews() {
        company.setText(line.company.name);
        prefixes.setText(line.getPrefixesSeparatedByComma());
        headway.setText(String.format(getContext().getString(R.string.placeholder_headway), String.valueOf(line.peakHeadway)));
    }

    private void setActionBarText() {
        AppCompatActivity activity = (AppCompatActivity)getActivity();
        if (activity == null) { return; }
        ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar == null ) { return; }

        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(R.layout.line_action_bar);
        View view = actionBar.getCustomView();

        TextView denomination = view.findViewById(R.id.barLineDenominationId);
        denomination.setText(line.getDenomination());

        TextView fromwards = view.findViewById(R.id.barLineFromwardsId);
        fromwards.setText(line.fromwards);

        TextView towards = view.findViewById(R.id.barLineTowardsId);
        towards.setText(line.towards);
    }

    private void drawVehicleIcons() {
        for (LineVehicle lineVehicle : line.lineVehicles) {
            ImageView vehicleIcon = new ImageView(getContext());
            vehicleIcon.setContentDescription(lineVehicle.vehicle.name);
            TooltipCompat.setTooltipText(vehicleIcon, lineVehicle.vehicle.name);
            vehicleIcon.setAdjustViewBounds(true);

            int iconSize = Math.round(getResources().getDimension(R.dimen.vehicle_icon_max_size));
            vehicleIcon.setMaxHeight(iconSize);
            vehicleIcon.setMaxWidth(iconSize);

            int iconPadding = Math.round(getResources().getDimension(R.dimen.vehicle_icon_padding));
            vehicleIcon.setPadding(iconPadding, iconPadding, iconPadding, iconPadding);

            switch (lineVehicle.vehicle.name) {
                case "Microônibus":
                    vehicleIcon.setImageResource(R.drawable.ic_microonibus_foreground); break;
                case "Médio":
                    vehicleIcon.setImageResource(R.drawable.ic_micrao_foreground); break;
                case "Padrão":
                    vehicleIcon.setImageResource(R.drawable.ic_onibus_foreground); break;
            }

            vehicles.addView(vehicleIcon);
        }
    }

    private void fillInterestPoints() {
        for (InterestPoint interestPoint : line.interestPoints) {
            TextView pointText = new TextView(getContext());
            pointText.setText(interestPoint.name);
            pointText.setGravity(Gravity.CENTER);
            pointText.setTextSize(16);

            interestPoints.addView(pointText);
        }
    }
}
