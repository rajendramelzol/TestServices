package com.melzol.services.service;

import java.util.List;

import com.melzol.services.beans.ServiceDTO;
import com.melzol.services.model.ActivitiesEvents;
import com.melzol.services.model.Services;

public interface ServicesService {
	
	public abstract void addServices(Services service);

	public abstract Services searchById(Integer serviceId);
	
	public abstract ServiceDTO searchServiceView(Integer serviceId,Integer refType);

	public abstract List<ServiceDTO> searchAllServices(int cat, String city,int page);

	public abstract List<ServiceDTO> searchMyServices(int memId, int start);
	
	public abstract List<ServiceDTO> searchNeighbourhoodServices(Double lat, Double lon, int start);

	public abstract void updateService(ServiceDTO serviceDTO);
	
	public abstract void deleteService(Services service);
	
	public abstract List<ServiceDTO> searchInCityServices(String city,int cat,String title);
	
	public abstract List<ServiceDTO> searchInNeighbourhoodServices(Double lat, Double lon, String title);



}
