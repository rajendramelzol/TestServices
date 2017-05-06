package com.melzol.services.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.melzol.services.beans.ActivitiesEventsDTO;
import com.melzol.services.beans.ServiceDTO;
import com.melzol.services.model.ActivitiesEvents;
import com.melzol.services.model.Services;
import com.melzol.services.model.WorkingDays;
import com.melzol.services.service.ServicesService;
import com.melzol.services.service.WorkingDaysService;
import com.melzol.services.util.Utils;

@RestController
@RequestMapping("/services")
public class ServicesRestController {
	@Autowired
	ServicesService servicesService;
	@Autowired
	WorkingDaysService workingDaysService;
	
		//-------------------Create a service--------------------------------------------------------
	
			@RequestMapping(value = "/add", method = RequestMethod.POST)
			public ResponseEntity<Services> createService(@RequestBody ServiceDTO serviceDTO, 	UriComponentsBuilder ucBuilder) {
				System.out.println("Creating service x0001 " + serviceDTO.getName());
				Services services = new Services();
				services.setName(serviceDTO.getName());
				services.setDescCompany(serviceDTO.getDescCompany());
				services.setType(serviceDTO.getType());
				services.setCategory(serviceDTO.getCategory());
				services.setLongitude(serviceDTO.getLongitude());
				services.setLatitude(serviceDTO.getLatitude());
				services.setEmail(serviceDTO.getEmail());
				services.setWebsite(serviceDTO.getWebsite());
				services.setMobile(serviceDTO.getMobile());
				services.setAddress(serviceDTO.getAddress());
				services.setCreatedTs(Utils.parseCreatedDate(serviceDTO.getfDate()));
				services.setCreatedBy(serviceDTO.getCreatedBy());
				services.setStatus(1);
				services.setCreatedTs(new Date());
				services.setUpdatedTs(new Date());
				servicesService.addServices(services);
				
				for(WorkingDays w:serviceDTO.getWrk()){
					w.setStartDate(Utils.parseDate(w.getsDate()));
					w.setEndDate(Utils.parseDate(w.geteDate()));
					w.setRefId(services.getServiceId());
					workingDaysService.addWorikingDays(w);
					}
				
				Services s=servicesService.searchById(services.getServiceId());
				return new ResponseEntity<Services>(s, HttpStatus.CREATED);
			}
		//-------------------Retrieve single Service--------------------------------------------------------
			
			@RequestMapping(value = "/serviceview/{serviceId}/{refType}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
			public ResponseEntity<ServiceDTO> getService(@PathVariable("serviceId") int serviceId,@PathVariable("refType") int refType) {
				System.out.println("Fetching service with X00001 id " + serviceId);
				ServiceDTO services = servicesService.searchServiceView(serviceId,refType);
				if (services == null) {
					System.out.println("service with id " + serviceId + " not found");
					return new ResponseEntity<ServiceDTO>(HttpStatus.NOT_FOUND);
				}
				return new ResponseEntity<ServiceDTO>(services, HttpStatus.OK);
			}
		//-------------------Retrieve All category&pincode Services--------------------------------------------------------
			
			@RequestMapping(value = "/servicesbycat/{cat}/{city}/{page}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
			public ResponseEntity<List<ServiceDTO>> getAllServices(@PathVariable("cat") int cat,@PathVariable("city") String city,@PathVariable("page") int page) {
				System.out.println("Fetching all services with X00001 category"+cat+"and page " + page);
				int start = (page-1)*10;
				List<ServiceDTO> services = servicesService.searchAllServices(cat,city,start);
				if (services == null) {
					System.out.println("services with category"+cat+"and page " + page+ " not found");
					return new ResponseEntity<List<ServiceDTO>>(HttpStatus.NOT_FOUND);
				}
				return new ResponseEntity<List<ServiceDTO>>(services, HttpStatus.OK);
			}
		//-------------------Retrieve All MyServices--------------------------------------------------------
			
			@RequestMapping(value = "/myservices/{memId}/{page}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
			public ResponseEntity<List<ServiceDTO>> getMyServices(@PathVariable("memId") int memId,@PathVariable("page") int page) {
				System.out.println("Fetching all gossips with X00001 page " + page);
				int start = (page-1)*10;
				List<ServiceDTO> services = servicesService.searchMyServices(memId,start);
				if (services == null) {
					System.out.println("services with owner id " + memId + " not found"); 
					return new ResponseEntity<List<ServiceDTO>>(HttpStatus.NOT_FOUND);
				}
				return new ResponseEntity<List<ServiceDTO>>(services , HttpStatus.OK);
			}
		//-------------------Update Service--------------------------------------------------------
			
			@RequestMapping(value = "update/{id}", method = RequestMethod.PUT)
			public ResponseEntity<ServiceDTO> modify(@PathVariable("id") int id, @RequestBody ServiceDTO serviceDTO){
				System.out.println("updating Services with X00001 id " + id);
				Services currentservice = servicesService.searchById(id);
				if (currentservice == null) {
					System.out.println("Services with id " + id + " not found");
					return new ResponseEntity<ServiceDTO>(HttpStatus.NOT_FOUND);
				}
				servicesService.updateService(serviceDTO);
				ServiceDTO e=servicesService.searchServiceView(serviceDTO.getServiceId(),serviceDTO.getSubType());
				//ActivitiesEventsDTO act = activitiesEventsService.searchActivityEventView(activitiesEventsDTO.getId(),activitiesEventsDTO.getCreatedBy(),activitiesEventsDTO.getSubType());
				return new ResponseEntity<ServiceDTO>(e, HttpStatus.OK);
			}
			
		//---------------------neighborhood services----------------------------------------------
			
			@RequestMapping(value = "/neighbourhoodservices/{lat}/{lon}/{page}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
			public ResponseEntity<List<ServiceDTO>> getNeighbourhoodServices(@PathVariable("lat") Double lat,@PathVariable("lon") Double lon,@PathVariable("page") int page) {
				System.out.println("Fetching all services with X00001 lat "+lat+"and lon " + lon);
				int start = (page-1)*10;
				List<ServiceDTO> services = servicesService.searchNeighbourhoodServices(lat,lon,start);
				if (services == null) {
					System.out.println("services with lat"+lat+"and lon " + lon+ " not found");
					return new ResponseEntity<List<ServiceDTO>>(HttpStatus.NOT_FOUND);
				}
				return new ResponseEntity<List<ServiceDTO>>(services, HttpStatus.OK);
			}
			
		//-------------------------delete service------------------------------------------------
			
			@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
			public ResponseEntity<Services> deleteService(@PathVariable("id") int id) {
				System.out.println("deleting  service x0001 " + id);
				Services services=servicesService.searchById(id);
				if (services == null) {
					System.out.println("Unable to delete. service with id " + id + " not found");
					return new ResponseEntity<Services>(HttpStatus.NOT_FOUND);
				}
				servicesService.deleteService(services);
				return new ResponseEntity<Services>(HttpStatus.NO_CONTENT);
			}
		//---------------------------search service in city level-----------------------------------------------
			
			@RequestMapping(value = "/searchincityservices/{city}/{cat}/{title}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
			public ResponseEntity<List<ServiceDTO>> searchInCityServices(@PathVariable("city") String city,@PathVariable("cat") int cat,@PathVariable("title")  String title) {
				System.out.println("Fetching all services with X00001 title " + title);
				List<ServiceDTO> servicesDTO = servicesService.searchInCityServices(city,cat,title);
				if (servicesDTO == null) {
					System.out.println("ActivitiesEvents with page " + title + " not found");
					return new ResponseEntity<List<ServiceDTO>>(HttpStatus.NOT_FOUND);
				}
				return new ResponseEntity<List<ServiceDTO>>(servicesDTO, HttpStatus.OK);
			}
			
		//-------------------------------search in neighbourhood services----------------------------------------
			
			@RequestMapping(value = "/searchinneighbourhoodservices/{lat}/{lon}/{title}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
			public ResponseEntity<List<ServiceDTO>> searchInNeighbourhoodServices(@PathVariable("lat") Double lat,@PathVariable("lon") Double lon,@PathVariable("title")  String title) {
				System.out.println("Fetching all services with X00001 lat "+lat+"and lon " + lon);

				List<ServiceDTO> services = servicesService.searchInNeighbourhoodServices(lat,lon,title);
				if (services == null) {
					System.out.println("services with lat"+lat+"and lon " + lon+ " not found");
					return new ResponseEntity<List<ServiceDTO>>(HttpStatus.NOT_FOUND);
				}
				return new ResponseEntity<List<ServiceDTO>>(services, HttpStatus.OK);
			}

}
