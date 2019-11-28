package br.com.vidal.santoandreonbus;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import br.com.vidal.santoandreonbus.models.Line;
import br.com.vidal.santoandreonbus.models.Place;

public class ItineraryFragment extends Fragment {

    private Line line;
    private ListView places;
    private ArrayAdapter<Place> adapter;

    private TextWatcher placeSearch = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            ItineraryFragment.this.adapter.getFilter().filter(s);
        }

        @Override
        public void afterTextChanged(Editable s) {}
    };

    public ItineraryFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        if (arguments != null) { this.line = (Line) arguments.getSerializable("line"); }

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
        EditText searchField = view.findViewById(R.id.searchPlaceId);
        searchField.addTextChangedListener(placeSearch);
    }

    private void fillInterestPoints() {
        if (getContext() == null) { return; }

        adapter = new ArrayAdapter<>(
                getContext(),
                android.R.layout.simple_list_item_1,
                line.places);

        places.setAdapter(adapter);
    }
}
