package com.melzol.services.service;

import com.melzol.services.model.LatitudeLongitude;

public interface LatitudeLongitudeService {

	public abstract LatitudeLongitude checkLatLong(Double lat, Double lon);

	public abstract void addLatitudeLongitude(LatitudeLongitude la);

}
