package com.melzol.services.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.melzol.services.dao.LatitudeLongitudeDAO;
import com.melzol.services.model.LatitudeLongitude;
@Repository
@Service("latitudeLongitudeService")
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class LatitudeLongitudeServiceImpl implements LatitudeLongitudeService{
	
	private LatitudeLongitudeDAO latitudeLongitudeDAO;
	
	public LatitudeLongitudeDAO getLatitudeLongitudeDAO() {
		return latitudeLongitudeDAO;
	}
	@Autowired
	public void setLatitudeLongitudeDAO(LatitudeLongitudeDAO latitudeLongitudeDAO) {
		this.latitudeLongitudeDAO = latitudeLongitudeDAO;
	}



	public LatitudeLongitude checkLatLong(Double latitude, Double longitude) {
		return latitudeLongitudeDAO.checkLatLong(latitude,longitude);
	}
	@Override
	public void addLatitudeLongitude(LatitudeLongitude la) {
		latitudeLongitudeDAO.save(la);
		
	}

}
