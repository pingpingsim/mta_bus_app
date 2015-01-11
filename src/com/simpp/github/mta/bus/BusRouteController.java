package com.simpp.github.mta.bus;

import java.util.List;

import android.app.Application;
import android.content.Context;

import com.simpp.github.mta.bus.data.BusRouteDataParser;
import com.simpp.github.mta.bus.model.BusRoute;

public class BusRouteController extends Application {
	private List<BusRoute> busRouteList;

	public List<BusRoute> getBusRouteList(Context context) {
		if (busRouteList == null) {
			busRouteList = BusRouteDataParser.parseJSONData(context);
		}
		return busRouteList;
	}
}
