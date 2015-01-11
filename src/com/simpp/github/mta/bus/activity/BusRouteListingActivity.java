package com.simpp.github.mta.bus.activity;

import java.util.List;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import com.android.mta_app.R;
import com.simpp.github.mta.bus.BusRouteController;
import com.simpp.github.mta.bus.data.BusRouteArrayAdapter;
import com.simpp.github.mta.bus.model.BusRoute;

public class BusRouteListingActivity extends BaseActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.route_list);
		BusRouteListLoader busRouteListLoader = new BusRouteListLoader();
		busRouteListLoader.execute();
	}

	private class BusRouteListLoader extends
			AsyncTask<Void, Void, BusRouteArrayAdapter> {
		ProgressDialog progress;
		ListView listview;

		public BusRouteListLoader() {

		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progress = new ProgressDialog(BusRouteListingActivity.this);
			progress.setTitle(BusRouteListingActivity.this
					.getText(R.string.app_name));
			progress.setMessage(BusRouteListingActivity.this
					.getText(R.string.msg_loading));
			progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			progress.setIcon(R.drawable.ic_launcher);
			progress.setCancelable(false);
			progress.show();
		}

		@Override
		protected BusRouteArrayAdapter doInBackground(Void... params) {
			try {
				return populateBusRouteListing();
			} catch (Exception e) {
				finish();
			}

			return null;
		}

		@Override
		protected void onPostExecute(BusRouteArrayAdapter busRouteList) {
			listview.setAdapter(busRouteList);
			busRouteList.notifyDataSetChanged();
			progress.dismiss();
		}

		private BusRouteArrayAdapter populateBusRouteListing() {
			listview = (ListView) findViewById(R.id.listview);

			List<BusRoute> busRouteList = ((BusRouteController) BusRouteListingActivity.this
					.getApplication()).getBusRouteList(
					BusRouteListingActivity.this);

			BusRouteArrayAdapter busRouteArrayAdapter = new BusRouteArrayAdapter(
					BusRouteListingActivity.this, R.layout.route_list,
					busRouteList);

			return busRouteArrayAdapter;
		}

	}
}
