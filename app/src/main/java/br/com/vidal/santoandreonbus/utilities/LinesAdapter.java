package br.com.vidal.santoandreonbus.utilities;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import br.com.vidal.santoandreonbus.R;
import br.com.vidal.santoandreonbus.models.Line;

public class LinesAdapter extends BaseAdapter implements Filterable {

    private Activity activity;
    private List<Line> lines;
    private List<Line> allLines;

    public LinesAdapter(Activity activity, List<Line> lines) {
        this.activity = activity;
        this.lines = lines;
        this.allLines = lines;
    }

    @Override
    public int getCount() { return lines.size(); }

    @Override
    public Object getItem(int position) { return lines.get(position); }

    @Override
    public long getItemId(int position) { return lines.get(position).id; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = activity.getLayoutInflater().inflate(
                R.layout.list_lines, parent, false);

        Line line = lines.get(position);
        TextView denomination = view.findViewById(R.id.listLineDenominationId);
        TextView fromwards = view.findViewById(R.id.listLineFromwardsId);
        TextView towards = view.findViewById(R.id.listLineTowardsId);

        denomination.setText(line.getDenomination());
        fromwards.setText(line.fromwards);
        towards.setText(line.towards);

        return view;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(final CharSequence constraint) {
                FilterResults results = new FilterResults();

                if (constraint == null || constraint.length() == 0) {
                    results.values = allLines;
                    results.count = allLines.size();

                    return results;
                }

                ArrayList<Line> filteredResults = new ArrayList<>();

                for (Line line : allLines) {
                    if (findInLineCaseInsensitive(constraint, line)) { filteredResults.add(line); }
                }

                results.values = filteredResults;
                results.count = filteredResults.size();

                return results;
            }

            private boolean findInLineCaseInsensitive(CharSequence term, Line line) {
                String pattern = Pattern.quote(term.toString());
                int insensitive = Pattern.CASE_INSENSITIVE;

                return Pattern.compile(pattern, insensitive).matcher(line.getDenomination()).find()
                        || Pattern.compile(pattern, insensitive).matcher(line.fromwards).find()
                        || Pattern.compile(pattern, insensitive).matcher(line.towards).find();
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results.count == 0) { notifyDataSetInvalidated(); return; }

                lines = (ArrayList<Line>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
