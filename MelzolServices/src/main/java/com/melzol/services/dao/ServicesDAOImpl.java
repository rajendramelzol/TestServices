package com.melzol.services.dao;

import java.util.Date;
import java.util.List;

import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.melzol.services.beans.ActivitiesEventsDTO;
import com.melzol.services.beans.BlogViewRowMapper;
import com.melzol.services.beans.BlogsDTO;
import com.melzol.services.beans.ServiceDTO;
import com.melzol.services.model.ActivitiesEvents;
import com.melzol.services.model.Gossips;
import com.melzol.services.model.Services;
import com.melzol.services.rowmappers.CityActivitiesRowMapper;
import com.melzol.services.rowmappers.MyServicesRowMapper;
import com.melzol.services.rowmappers.NeighbourhoodActivitiesRowMapper;
import com.melzol.services.rowmappers.ServiceViewRowMapper;

@Repository("servicesDAO")
@Transactional
public class ServicesDAOImpl extends HibernateDaoSupport implements ServicesDAO{
	private static final Log log = LogFactory.getLog(ServicesDAOImpl.class);
	@Autowired
	private SessionFactory sessionFactory;
    private JdbcTemplate jdbcTemplate;
    
    public ServicesDAOImpl() {
        super();
     }
     
 	@Autowired
     public ServicesDAOImpl(DataSource dataSource) {
         jdbcTemplate = new JdbcTemplate(dataSource);
     }
	
	/* (non-Javadoc)
	 * @see com.websystique.springmvc.dao.LeadsDAO#getJdbcTemplate()
	 */
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	/* (non-Javadoc)
	 * @see com.websystique.springmvc.dao.LeadsDAO#setJdbcTemplate(org.springframework.jdbc.core.JdbcTemplate)
	 */
	protected void initDao() {
		// do nothing
	}
	/* (non-Javadoc)
	 * @see com.websystique.springmvc.dao.LeadsDAO#init(org.hibernate.SessionFactory)
	 */
	@Autowired
	public void init(SessionFactory sessionFactory) {
	    setSessionFactory(sessionFactory);
	    this.sessionFactory=sessionFactory;
	}
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	@Autowired
	protected HibernateTemplate createHibernateTemplate(
			SessionFactory sessionFactory) {
		return super.createHibernateTemplate(sessionFactory);
	}
	
	public void save(Services persistentInstance) {
		try {
			sessionFactory.getCurrentSession().save(persistentInstance);
		} catch (RuntimeException re) {
			System.out.println("the errror in the services save is "+re);
			throw re;
		}
	}

	public Services findById(Integer serviceId) {
		log.debug("getting Services instance with id: " + serviceId);
		try {
			Services instance = (Services) sessionFactory.getCurrentSession().get("com.melzol.services.model.Services", serviceId);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public Services findServiceView(Integer serviceId) {
		
		log.debug("getting Gossips instance with id: " + serviceId);
		try {
			Services instance = (Services) sessionFactory.getCurrentSession().get("com.melzol.services.model.Services", serviceId);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<ServiceDTO> findAllServices(int cat, String city,int page) {
		List<ServiceDTO> list = null;
			String sql="SELECT S.service_id,S.name,S.created_by,S.created_ts,w.image_id,m.user_name from services S "+
						"left outer join working_days w on (w.ref_id = S.service_id and w.ref_type = 6) "+
						"left outer join members m on m.member_id = S.created_by and m.city=?"+
						"where  S.type=1 and S.category=? and S.status=1  group by S.service_id order by S.created_ts desc LIMIT 10 OFFSET ?";
			try{
				list=jdbcTemplate.query(sql,new Object[]{city,cat,page},new MyServicesRowMapper());
				}catch(Exception e){
					System.out.println("error in joinedevents"+e);
				}
			return list;
	}

	public List<ServiceDTO> findMyServices(int memId, int start) {
		List<ServiceDTO> list = null;
		String sql="SELECT S.service_id, S.name,S.created_by,S.created_ts,w.image_id,M.user_name "+
					"FROM services S LEFT OUTER JOIN working_days w ON (w.ref_id=S.service_id and w.ref_type=6) "+ 
					"LEFT OUTER JOIN members M ON M.member_id = S.created_by "+
					"where (S.status=1 and S.created_by=?) group by S.service_id order by S.created_ts desc LIMIT 5 OFFSET ?";
		list=jdbcTemplate.query(sql,new Object[]{memId,start},new MyServicesRowMapper());
		return  list; 
	}
	
	public List<ServiceDTO> findNeighbourhoodServices(Double lat, Double lon, int start) {
		List<ServiceDTO> list = null;
		String sql=" SELECT S.service_id,S.name,S.created_by,S.created_ts,S.latitude,S.longitude,w.image_id,m.user_name, "+
					"(((acos(sin((?*pi()/180)) * sin((S.latitude*pi()/180))+cos((?*pi()/180))* cos((S.latitude*pi()/180)) * "+ 
					"cos(((? - S.longitude)*pi()/180))))*180/pi())*60*1.1515) AS `distance` "+
					"FROM members m,services S "+
					"left outer join working_days w on (w.ref_id = S.service_id and w.ref_type = 6) "+
				"where  m.member_id=S.created_by and S.status=1 group by S.service_id "+
					"HAVING `distance` <= 1.5  order by created_ts desc LIMIT 10 OFFSET ?";
		list=jdbcTemplate.query(sql,new Object[]{lat,lat,lon,start},new ServiceViewRowMapper());
		return  list; 
	
	}

	public void update(ServiceDTO ser) {
		Session s=sessionFactory.getCurrentSession();
		Services service=(Services) s.load(Services.class, ser.getServiceId());
		if(ser.getName() != null)
		service.setName(ser.getName());
		if(ser.getCategory() != 0)
		service.setCategory(ser.getCategory());
		if(ser.getDescCompany() != null)
		service.setDescCompany(ser.getDescCompany());
		//service.setWorkingHrs(ser.getWorkingHrs());
		if(ser.getAddress() != null)
		service.setAddress(ser.getAddress());
		if(ser.getWebsite() != null)
		service.setWebsite(ser.getWebsite());
		if(ser.getMobile() != null)
		service.setMobile(ser.getMobile());
		if(ser.getEmail() != null)
		service.setEmail(ser.getEmail());
		service.setUpdatedTs(new Date());
		s.update(service);
		
	}
	
	public void deleteServices(Services service) {
		log.debug("deleting service instance");
		try {
			sessionFactory.getCurrentSession().delete(service);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	
	public List<ServiceDTO> findInCityServices(String city,int cat,String title) {
		List<ServiceDTO> list = null;
		title="%" + title + "%";
			String sql="SELECT S.service_id,S.category,S.name,S.created_by,S.created_ts,w.image_id,m.user_name from services S "+
						"left outer join working_days w on (w.ref_id = S.service_id and w.ref_type = 6) "+
						"left outer join members m on m.member_id = S.created_by and m.city=? "+
						"where  S.type=1 and S.status=1 and S.category=? and S.name like ? group by S.service_id order by S.created_ts desc";
			try{
				list=jdbcTemplate.query(sql,new Object[]{city,cat,title},new MyServicesRowMapper());
				}catch(Exception e){
					System.out.println("error in joinedevents"+e);
				}
			return list;
	}
	
	public List<ServiceDTO> findInNeighbourhoodServices(Double lat, Double lon, String title) {
		List<ServiceDTO> list = null;
		title="%" + title + "%";
		String sql="SELECT S.service_id,S.name,S.created_by,S.created_ts,S.latitude,S.longitude,w.image_id,m.user_name, "+
					"(((acos(sin((?*pi()/180)) * sin((S.latitude*pi()/180))+cos((?*pi()/180))* cos((S.latitude*pi()/180)) * "+ 
					"cos(((? - S.longitude)*pi()/180))))*180/pi())*60*1.1515) AS `distance` "+
					"FROM members m,services S "+
					"left outer join working_days w on (w.ref_id = S.service_id and w.ref_type = 6) "+
				"where  m.member_id=S.created_by and S.status=1 and S.name like ? group by S.service_id "+
					"HAVING `distance` <= 1.5  order by created_ts desc ";
		list=jdbcTemplate.query(sql,new Object[]{lat,lat,lon,title},new ServiceViewRowMapper());
		return  list; 
	
	}
	

}
