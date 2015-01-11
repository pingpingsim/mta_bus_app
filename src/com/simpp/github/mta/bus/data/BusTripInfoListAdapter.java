package com.simpp.github.mta.bus.data;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;

import com.android.mta_app.R;
import com.simpp.github.mta.bus.model.BusTrip;
import com.simpp.github.mta.bus.model.StopInfo;

public class BusTripInfoListAdapter extends BaseExpandableListAdapter {

	private final List<BusTrip> groups;
	public LayoutInflater inflater;
	public Activity activity;

	public BusTripInfoListAdapter(Activity act, List<BusTrip> groups) {
		this.activity = act;
		this.groups = groups;
		this.inflater = act.getLayoutInflater();
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return groups.get(groupPosition).getStopInfoList().get(childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return 0;
	}

	@Override
	public View getChildView(int groupPosition, final int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		final StopInfo children = (StopInfo) getChild(
				groupPosition, childPosition);
		TextView text = null;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.bus_trip_info_list_row, null);
		}
		text = (TextView) convertView.findViewById(R.id.textview_stop_name);
		text.setText(children.getStopName());

		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return groups.get(groupPosition).getStopInfoList().size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return groups.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return groups.size();
	}

	@Override
	public void onGroupCollapsed(int groupPosition) {
		super.onGroupCollapsed(groupPosition);
	}

	@Override
	public void onGroupExpanded(int groupPosition) {
		super.onGroupExpanded(groupPosition);
	}

	@Override
	public long getGroupId(int groupPosition) {
		return 0;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.bus_trip_info_list_group,
					null);
		}
		BusTrip group = (BusTrip) getGroup(groupPosition);
		
		((TextView) convertView).setText(group.getHeadsign());
		
		((CheckedTextView) convertView).setChecked(isExpanded);
		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return false;
	}
}