package com.simpp.github.mta.bus.activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ExpandableListView;

import com.android.mta_app.R;
import com.simpp.github.mta.bus.data.BusTripInfoListAdapter;

public class BusTripInfoActivity extends BaseActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.bus_trip_info_list);

		getActionBar().setDisplayShowHomeEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
		getActionBar().setDisplayHomeAsUpEnabled(true);

		BusTripInfoListLoader busTripInfoListLoader = new BusTripInfoListLoader();
		busTripInfoListLoader.execute();
	}

	private class BusTripInfoListLoader extends
			AsyncTask<Void, Void, BusTripInfoListAdapter> {
		ProgressDialog progress;
		ExpandableListView listView;

		public BusTripInfoListLoader() {

		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progress = new ProgressDialog(BusTripInfoActivity.this);
			progress.setTitle(BusTripInfoActivity.this
					.getText(R.string.app_name));
			progress.setMessage(BusTripInfoActivity.this
					.getText(R.string.msg_loading));
			progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			progress.setIcon(R.drawable.ic_launcher);
			progress.setCancelable(false);
			progress.show();
		}

		@Override
		protected BusTripInfoListAdapter doInBackground(Void... params) {
			try {
				return populateBusRouteListing();
			} catch (Exception e) {
				finish();
			}

			return null;
		}

		@Override
		protected void onPostExecute(
				BusTripInfoListAdapter busTripInfoListAdapter) {
			listView.setAdapter(busTripInfoListAdapter);
			busTripInfoListAdapter.notifyDataSetChanged();
			progress.dismiss();
		}

		private BusTripInfoListAdapter populateBusRouteListing() {
			final Bundle bundle = getIntent().getExtras();
			BusTripInfoListAdapter adapter = null;
			if (bundle != null
					&& bundle.containsKey(getString(R.string.bus_route_id))) {
				final String busRouteId = bundle
						.getString(getString(R.string.bus_route_id));
//				List<BusTrip> busTripList = ((BusRouteController) BusTripInfoActivity.this
//						.getApplication()).getBusRouteDataSource(
//						BusTripInfoActivity.this).getMainTripByRouteId(
//						busRouteId);

				listView = (ExpandableListView) findViewById(R.id.listView);
				adapter = new BusTripInfoListAdapter(BusTripInfoActivity.this,
						null);

			}
			return adapter;
		}

	}
}
