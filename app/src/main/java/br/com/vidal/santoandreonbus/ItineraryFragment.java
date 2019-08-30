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

import br.com.vidal.santoandreonbus.models.Line;

public class ItineraryFragment extends Fragment {

    private Line line;
    public ListView places;

    public ItineraryFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        if (arguments != null) { this.line = (Line) getArguments().getSerializable("line"); }

        return inflater.inflate(R.layout.fragment_itinerary, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setViews(view);
        fillInterestPoints();
    }

    private void setViews(View view) {
        places = view.findViewById(R.id.placesId);
    }

    private void fillInterestPoints() {
        places.setAdapter(new ArrayAdapter<>(
                getContext(),
                android.R.layout.simple_list_item_1,
                line.places)
        );
    }
}
