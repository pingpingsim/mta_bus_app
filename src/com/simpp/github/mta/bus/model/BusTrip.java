package com.simpp.github.mta.bus.model;

import java.util.List;

public class BusTrip {
	private String routeId;
	private String serviceId;
	private String tripId;
	private String headsign;
	private int directionId;
	private int blockId;
	private String shapeId;
	private List<StopInfo> stopInfoList;

	
	public String getRouteId() {
		return routeId;
	}

	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getTripId() {
		return tripId;
	}

	public void setTripId(String tripId) {
		this.tripId = tripId;
	}

	public String getHeadsign() {
		return headsign;
	}

	public void setHeadsign(String headsign) {
		this.headsign = headsign;
	}

	public int getDirectionId() {
		return directionId;
	}

	public void setDirectionId(int directionId) {
		this.directionId = directionId;
	}

	public int getBlockId() {
		return blockId;
	}

	public void setBlockId(int blockId) {
		this.blockId = blockId;
	}

	public String getShapeId() {
		return shapeId;
	}

	public void setShapeId(String shapeId) {
		this.shapeId = shapeId;
	}

	public List<StopInfo> getStopInfoList() {
		return stopInfoList;
	}

	public void setStopInfoList(List<StopInfo> busTripJourney) {
		this.stopInfoList = busTripJourney;
	}
}
