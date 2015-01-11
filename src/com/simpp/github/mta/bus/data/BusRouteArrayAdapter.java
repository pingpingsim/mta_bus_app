package com.simpp.github.mta.bus.data;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.mta_app.R;
import com.simpp.github.mta.bus.activity.BusTripInfoActivity;
import com.simpp.github.mta.bus.model.BusRoute;

public class BusRouteArrayAdapter extends ArrayAdapter<BusRoute> {
	private Context context;
	private List<BusRoute> data;

	public BusRouteArrayAdapter(Context context, int layoutResourceId,
			List<BusRoute> data) {
		super(context, layoutResourceId, data);
		this.context = context;
		this.data = data;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;

		if (view == null) {
			final LayoutInflater layoutInflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = layoutInflater.inflate(R.layout.route_list_row, null);// route_details_list_row
		}

		final BusRoute busRoute = data.get(position);

		if (busRoute != null) {
			// route short name
			TextView busRouteShortNameTextView = (TextView) view
					.findViewById(R.id.textview_route_short_name);
			busRouteShortNameTextView.setText(busRoute.getShortName());
			// route long name
			TextView busRouteLongNameTextView = (TextView) view
					.findViewById(R.id.textview_route_long_name);
			busRouteLongNameTextView.setText(busRoute.getLongName());
			// route description
			TextView busRouteDescTextView = (TextView) view
					.findViewById(R.id.textview_route_desc);
			busRouteDescTextView.setText(busRoute.getDesc());
			// set route color
			LinearLayout ll = (LinearLayout) view
					.findViewById(R.id.linearlayout_route_left);
			ll.setBackgroundResource(R.drawable.linear_layout_border);
			GradientDrawable drawable = (GradientDrawable) ll.getBackground();
			drawable.setColor(Color.parseColor(String.format("#%1$s",
					busRoute.getColor())));

			view.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					final ListView parent = (ListView) v.getParent();
					final int pos = parent.getPositionForView(v);
					final BusRoute busRoute = (BusRoute) parent
							.getItemAtPosition(pos);
					startDetailsActivity(busRoute);
				}
			});
		}
		return view;
	}

	private void startDetailsActivity(BusRoute busRoute) {
		final Bundle bundle = new Bundle();
		bundle.putString(context.getString(R.string.bus_route_id),
				busRoute.getId());

		final Intent intent = new Intent(context, BusTripInfoActivity.class);
		intent.putExtras(bundle);
		//context.startActivity(intent);
	}
}
