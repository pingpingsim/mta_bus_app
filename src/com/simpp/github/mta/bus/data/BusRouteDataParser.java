package com.simpp.github.mta.bus.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.android.mta_app.R;
import com.simpp.github.mta.bus.model.BusRoute;

public class BusRouteDataParser {
	
	public static List<BusRoute> parseJSONData(Context context) {
		List<BusRoute> busRouteList = new ArrayList<BusRoute>();
		BusRoute busRoute;
		StringBuilder builder = new StringBuilder();
		HttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(String.format(context.getString(R.string.api_base_url), context.getString(R.string.api_key)).replaceAll(" ", "%20"));
		try {
			HttpResponse response = client.execute(httpGet);
			StatusLine statusLine = response.getStatusLine();
			int statusCode = statusLine.getStatusCode();
			if (statusCode == 200) {
				HttpEntity entity = response.getEntity();
				InputStream content = entity.getContent();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(content));
				String line;
				while ((line = reader.readLine()) != null) {
					builder.append(line);
				}

				JSONObject jsonObj = new JSONObject(builder.toString());
				JSONObject jsonDataObj = jsonObj.getJSONObject("data");
				JSONArray routeArray = jsonDataObj.getJSONArray("list");

				for (int i = 0; i < routeArray.length(); i++) {
					busRoute = new BusRoute();
					busRoute.setId(routeArray.getJSONObject(i).getString("id"));
					busRoute.setShortName(routeArray.getJSONObject(i).getString("shortName"));
					busRoute.setLongName(routeArray.getJSONObject(i).getString("longName"));
					busRoute.setDesc(routeArray.getJSONObject(i).getString("description"));
					busRoute.setType(routeArray.getJSONObject(i).getInt("type"));
					busRoute.setColor(routeArray.getJSONObject(i).getString("color"));
					busRoute.setTextColor(routeArray.getJSONObject(i).getString("textColor"));
					busRoute.setAgencyId(routeArray.getJSONObject(i).getString("agencyId"));
					busRouteList.add(busRoute);
				}
			} else {
				// Log.e(ParseJSON.class.toString(), "Failed to download file");
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return busRouteList;
	}
}
