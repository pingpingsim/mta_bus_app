package com.simpp.github.mta.bus.model;


public class StopInfo {
	private String routeId;
	private String tripId;
	private int stopId;
	private String stopName;
	private String stopSequence;
	private String tripHeadsign;

	public String getRouteId() {
		return routeId;
	}

	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}

	public String getTripId() {
		return tripId;
	}

	public void setTripId(String tripId) {
		this.tripId = tripId;
	}

	public int getStopId() {
		return stopId;
	}

	public void setStopId(int stopId) {
		this.stopId = stopId;
	}

	public String getStopName() {
		return stopName;
	}

	public void setStopName(String stopName) {
		this.stopName = stopName;
	}

	public String getStopSequence() {
		return stopSequence;
	}

	public void setStopSequence(String stopSequence) {
		this.stopSequence = stopSequence;
	}

	public String getTripHeadsign() {
		return tripHeadsign;
	}

	public void setTripHeadsign(String tripHeadsign) {
		this.tripHeadsign = tripHeadsign;
	}

}
