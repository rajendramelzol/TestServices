package com.melzol.services.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.melzol.services.beans.ActivitiesEventsDTO;
import com.melzol.services.beans.ServiceDTO;
import com.melzol.services.dao.ServicesDAO;
import com.melzol.services.dao.WorkingDaysDAO;
import com.melzol.services.model.ActivitiesEvents;
import com.melzol.services.model.Services;
import com.melzol.services.model.WorkingDays;
import com.melzol.services.util.Utils;

@Repository
@Service("servicesService")
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class ServicesServiceImpl implements ServicesService {
	private ServicesDAO servicesDAO;
	private WorkingDaysDAO workingDaysDAO;
	
	

	public ServicesDAO getServicesDAO() {
		return servicesDAO;
	}
	@Autowired
	public void setServicesDAO(ServicesDAO servicesDAO) {
		this.servicesDAO = servicesDAO;
	}
	
	public WorkingDaysDAO getWorkingDaysDAO() {
		return workingDaysDAO;
	}
	@Autowired
	public void setWorkingDaysDAO(WorkingDaysDAO workingDaysDAO) {
		this.workingDaysDAO = workingDaysDAO;
	}
	public void addServices(Services services) {
		servicesDAO.save(services);
	}

	public Services searchById(Integer serviceId) {
		return servicesDAO.findById(serviceId);
	}
	
	public ServiceDTO searchServiceView(Integer serviceId,Integer refType) {
		Services services= servicesDAO.findServiceView(serviceId);
		List<WorkingDays> wrk=workingDaysDAO.findAll(serviceId,refType);
		/*for(WorkingDays w:wrk){
			w.setsDate(Utils.formatDateForum(w.getStartDate()));
			w.seteDate(Utils.formatDateForum(w.getEndDate()));
		}*/
			ServiceDTO ser = new ServiceDTO();
			ser.setServiceId(services.getServiceId());
			ser.setName(services.getName());
			ser.setDescCompany(services.getDescCompany());
			ser.setCreatedBy(services.getCreatedBy());
			ser.setMobile(services.getMobile());
			ser.setCategory(services.getCategory());
			ser.setEmail(services.getEmail());
			ser.setWebsite(services.getWebsite());
			ser.setAddress(services.getAddress());
			ser.setLatitude(services.getLatitude());
			ser.setLongitude(services.getLongitude());
		
		ser.setWrk(wrk);
		
		return ser;
	}

	public List<ServiceDTO> searchAllServices(int cat, String city,int page) {
		return servicesDAO.findAllServices(cat,city,page);
	}

	public List<ServiceDTO> searchMyServices(int memId, int start) {
		return servicesDAO.findMyServices(memId,start);
	}
	
	public List<ServiceDTO> searchNeighbourhoodServices(Double lat, Double lon, int start) {
		return servicesDAO.findNeighbourhoodServices(lat,lon,start);
	}
	
	public void updateService(ServiceDTO serviceDTO) {
		servicesDAO.update(serviceDTO);
		for(WorkingDays w:serviceDTO.getWrk()){
			/*w.setStartDate(Utils.parseDate(w.getsDate()));
			w.setEndDate(Utils.parseDate(w.geteDate()));*/
			w.setRefId(serviceDTO.getServiceId());
			workingDaysDAO.updateWorikingDays(w);
			}
		
	}
	
	public void deleteService(Services service) {
		servicesDAO.deleteServices(service);
		
	}
	
	public List<ServiceDTO> searchInCityServices(String city,int cat,String title) {
		return servicesDAO.findInCityServices(city,cat,title);
	}
	
	public List<ServiceDTO> searchInNeighbourhoodServices(Double lat, Double lon, String title) {
		return servicesDAO.findInNeighbourhoodServices(lat,lon,title);
	}
	

}
