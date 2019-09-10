package br.com.vidal.santoandreonbus.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.vidal.santoandreonbus.R;
import br.com.vidal.santoandreonbus.models.Line;

public class LinesAdapter extends BaseAdapter {

    public Activity activity;
    public List<Line> lines;

    public LinesAdapter(Activity activity, List<Line> lines) {
        this.activity = activity;
        this.lines = lines;
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
                R.layout.lines_list, parent, false);

        Line line = lines.get(position);
        TextView denomination = view.findViewById(R.id.listLineDenominationId);
        TextView fromwards = view.findViewById(R.id.listLineFromwardsId);
        TextView towards = view.findViewById(R.id.listLineTowardsId);

        denomination.setText(line.getDenomination());
        fromwards.setText(line.fromwards);
        towards.setText(line.towards);

        return view;
    }
}
