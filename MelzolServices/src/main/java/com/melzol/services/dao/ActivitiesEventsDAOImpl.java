package com.melzol.services.dao;

import java.util.Date;
import java.util.List;

import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.melzol.services.beans.ActivitiesEventsDTO;
import com.melzol.services.model.ActivitiesEvents;
import com.melzol.services.rowmappers.ActivityEventViewRowMapper;
import com.melzol.services.rowmappers.CityActivitiesRowMapper;
import com.melzol.services.rowmappers.MyActivitiesEventsRowMapper;
import com.melzol.services.rowmappers.NeighbourhoodActivitiesRowMapper;

@Repository("activitiesEventsDAO")
@Transactional
public class ActivitiesEventsDAOImpl extends HibernateDaoSupport implements ActivitiesEventsDAO{
	
	private static final Log log = LogFactory.getLog(ActivitiesEventsDAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
    private JdbcTemplate jdbcTemplate;
	
    public ActivitiesEventsDAOImpl() {
       super();
    }
    
	@Autowired
    public ActivitiesEventsDAOImpl(DataSource dataSource) {
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

	
	public void save(ActivitiesEvents activitiesevents) {
		try {
			sessionFactory.getCurrentSession().save(activitiesevents);
		} catch (RuntimeException re) {
			throw re;
		}

		
	}


	public ActivitiesEvents findById(Integer id) {
		log.debug("getting ActivitiesEvents instance with id: " + id);
		try {
			ActivitiesEvents instance = (ActivitiesEvents) sessionFactory.getCurrentSession().get("com.melzol.services.model.ActivitiesEvents", id);
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

	public List<ActivitiesEventsDTO> searchCityActivities(int memId, String city,Date curDate, int start) {
		List<ActivitiesEventsDTO> list = null;
		String sql="SELECT AE.id, AE.title,AE.category,AE.description,AE.address,WD.image_id,WD.start_date,"
				+ "(select count(*) from joinings_opinions gj where gj.activity_type = 1 and gj.ref_id = AE.id and gj.ref_type=2) as totmemcnt "
				+ " FROM activities_events AE LEFT OUTER JOIN  working_days WD ON (WD.ref_id = AE.id AND WD.ref_type=2)  LEFT OUTER JOIN  "
				+ "members M ON (M.member_id = AE.created_by)  where M.city=? and AE.type=1 and AE.status=1 and WD.start_date > ? "
				+ " group by AE.id order by WD.start_date asc  LIMIT 10 OFFSET ?";
		try{
			list=jdbcTemplate.query(sql,new Object[]{city,curDate,start},new CityActivitiesRowMapper());
			}catch(Exception e){
				System.out.println("error in joinedevents"+e);
			}
			return  list; 
	}
	public ActivitiesEventsDTO findActivityEventView(int id, int memId,int type) {
		String sql="SELECT AE.id, AE.title,AE.description,AE.address,AE.created_by,AE.mobile,AE.type,AE.category,AE.email_id,AE.website,AE.latitude,AE.longitude,M.user_name,M.profile_pic,M.gcm_key,"
				+ "(select count(*) from joinings_opinions gj where gj.activity_type = 1 and gj.ref_id = AE.id and gj.ref_type=2) as totmemcnt,"
				+ "(select activity_type from joinings_opinions gml where gml.member_id = ? and gml.ref_id = AE.id  and gml.ref_type=2) as joined "
				+ "FROM activities_events AE left outer join members M on AE.created_by=M.member_id where AE.id=?";
		ActivitiesEventsDTO ac=jdbcTemplate.queryForObject(sql,new Object[]{memId,id},new ActivityEventViewRowMapper());
		return ac;
	}

	public List<ActivitiesEventsDTO> findNeighbourhoodActivities(Double lat, Double lon, int memId,Date curDate, int start) {
		List<ActivitiesEventsDTO> list = null;
		String sql="SELECT AE.id, AE.title,AE.description,AE.created_by,AE.created_ts,AE.category,w.image_id,w.ref_type,w.start_date,Ng.gcm_key,"
				+ "(select count(*) from joinings_opinions gj where gj.activity_type = 1 and gj.ref_id = AE.id and gj.ref_type=2) as totmemcnt,"
				+ "(((acos(sin((?*pi()/180)) * sin((AE.latitude*pi()/180))+cos((?*pi()/180)) "
				+ "* cos((AE.latitude*pi()/180)) * cos(((? - AE.longitude)*pi()/180))))*180/pi())*60*1.1515)  AS `distance` FROM "
				+ "  members Ng,activities_events AE,working_days w where  Ng.member_id=AE.created_by  and w.ref_id=AE.id "
				+ "and (w.ref_type=1 or w.ref_type=2) and AE.status=1 and w.start_date > ? group by AE.id HAVING `distance`  <= 1.5  order by "
				+ "w.start_date asc  LIMIT 10 OFFSET ?";
		//try{
			list=jdbcTemplate.query(sql,new Object[]{lat,lat,lon,curDate,start},new NeighbourhoodActivitiesRowMapper());
			/*}catch(Exception e){
				System.out.println("error in joinedevents"+e);
			}*/
			return  list; 
	}
	public List<ActivitiesEventsDTO> findActivitiesByCat(int memId, String city, int refType, int cat, int start) {
		List<ActivitiesEventsDTO> list = null;
		String sql="SELECT AE.id, AE.title,AE.category,AE.description,AE.address,WD.image_id,"
				+ "(select activity_type from joinings_opinions gm where gm.member_id = ? and gm.ref_id = AE.id and gm.ref_type=?) as JoinInd,"
				+ "(select count(*) from joinings_opinions gj where gj.activity_type = 1 and gj.ref_id = AE.id and gj.ref_type=?) as totmemcnt,"
				+ "(select count(*) from joinings_opinions gl where gl.opinion = 1 and gl.ref_id = AE.id AND gl.ref_type=?) as totlikecnt,"
				+ "(select opinion from joinings_opinions gml where gml.member_id = ? and gml.ref_id = AE.id and gml.ref_type=?) as useopinion "
				+ "FROM activities_events AE LEFT OUTER JOIN  working_days WD ON (WD.ref_id = AE.id AND WD.ref_type=?)  LEFT OUTER JOIN  members M "
				+ "ON (M.member_id = AE.created_by)  where M.city=? and AE.type=1 and AE.category=? and AE.sub_type=? and AE.status=1 group by AE.id "
				+ "order by AE.updated_ts desc LIMIT 10 OFFSET ?";
		try{
			list=jdbcTemplate.query(sql,new Object[]{memId,refType,refType,refType,memId,refType,refType,city,cat,refType,start},new CityActivitiesRowMapper());
			}catch(Exception e){
				System.out.println("error in joinedevents"+e);
			}
			return  list; 
	}

	public List<ActivitiesEventsDTO> findInCityActivities(int memId, String city, int refType, String title) {
		List<ActivitiesEventsDTO> list = null;
		title="%" + title + "%";
		String sql="SELECT AE.id, AE.title,AE.category,AE.description,AE.address,WD.image_id,"
				+ "(select activity_type from joinings_opinions gm where gm.member_id = ? and gm.ref_id = AE.id and gm.ref_type=?) as JoinInd,"
				+ "(select count(*) from joinings_opinions gj where gj.activity_type = 1 and gj.ref_id = AE.id and gj.ref_type=?) as totmemcnt,"
				+ "(select count(*) from joinings_opinions gl where gl.opinion = 1 and gl.ref_id = AE.id AND gl.ref_type=?) as totlikecnt,"
				+ "(select opinion from joinings_opinions gml where gml.member_id = ? and gml.ref_id = AE.id and gml.ref_type=?) as useopinion "
				+ "FROM activities_events AE LEFT OUTER JOIN  working_days WD ON (WD.ref_id = AE.id AND WD.ref_type=?)  LEFT OUTER JOIN  members M "
				+ "ON (M.member_id = AE.created_by)  where M.city=? and AE.type=1 and AE.title like ? and AE.sub_type=? and AE.status=1 group by AE.id "
				+ "order by AE.updated_ts desc";
		try{
			list=jdbcTemplate.query(sql,new Object[]{memId,refType,refType,refType,memId,refType,refType,city,title,refType},new CityActivitiesRowMapper());
			}catch(Exception e){
				System.out.println("error in joinedevents"+e);
			}
			return  list; 
	}
	public List<ActivitiesEventsDTO> findInNeighbourhoodActivities(Double lat, Double lon, int memId, Date curDate,
			String title) {
		List<ActivitiesEventsDTO> list = null;
		title="%" + title + "%";
		String sql="SELECT AE.id, AE.title,AE.description,AE.created_by,AE.created_ts,AE.category,w.image_id,w.ref_type,w.start_date,Ng.gcm_key,"
				+ "(select count(*) from joinings_opinions gl where gl.opinion = 1 and gl.ref_id = AE.id AND (gl.ref_type=1 or gl.ref_type=2)) as totlikecnt,"
				+ "(select opinion from joinings_opinions gml where gml.member_id = ? and gml.ref_id = AE.id and (gml.ref_type=1 or gml.ref_type=2)) as useopinion,"
				+ "(((acos(sin((?*pi()/180)) * sin((LN.latitude*pi()/180))+cos((?*pi()/180)) "
				+ "* cos((LN.latitude*pi()/180)) * cos(((? - LN.longitude)*pi()/180))))*180/pi())*60*1.1515)  AS `distance` FROM "
				+ "latitude_longitude LN, members Ng,activities_events AE,working_days w where Ng.address_refid = LN.id and Ng.member_id=AE.created_by  and w.ref_id=AE.id "
				+ "and (w.ref_type=1 or w.ref_type=2) and AE.title like ? and AE.status=1 group by AE.id HAVING `distance`  <= 1.5  order by "
				+ "w.start_date=? DESC";
		//try{
			list=jdbcTemplate.query(sql,new Object[]{memId,lat,lat,lon,title,curDate},new NeighbourhoodActivitiesRowMapper());
			/*}catch(Exception e){
				System.out.println("error in joinedevents"+e);
			}*/
			return  list; 
	}

	public List<ActivitiesEventsDTO> findMyActivities(int memId, int start) {
		List<ActivitiesEventsDTO> list = null;
		String sql="SELECT AE.id, AE.title,AE.description,AE.created_by,AE.created_ts,AE.category,w.image_id,w.ref_type,w.start_date,M.gcm_key,"
				+ "(select count(*) from joinings_opinions gj where gj.activity_type = 1 and gj.ref_id = AE.id and gj.ref_type=2) as totmemcnt"
				+ " FROM  members M,activities_events AE left outer join working_days w on (w.ref_id=AE.id and (w.ref_type=1 or w.ref_type=2)) where "
				+ " M.member_id=AE.created_by and AE.status=1 and AE.created_by=? or AE.id IN "
				+ "(SELECT GM.ref_id FROM joinings_opinions GM WHERE GM.member_id =? and GM.ref_type=2 and GM.activity_type=1 order by GM.join_date desc) "
				+ "group by AE.id order by AE.created_by=? desc LIMIT 10 OFFSET ?";
		//try{
			list=jdbcTemplate.query(sql,new Object[]{memId,memId,memId,start},new NeighbourhoodActivitiesRowMapper());
			/*}catch(Exception e){
				System.out.println("error in joinedevents"+e);
			}*/
			return  list; 
	}

	public void updateActivityEvent(ActivitiesEventsDTO a) {
		
		Session s=sessionFactory.getCurrentSession();
		ActivitiesEvents activitiesEvents=(ActivitiesEvents) s.load(ActivitiesEvents.class,a.getId());
		if(a.getTitle()!=null)
		activitiesEvents.setTitle(a.getTitle());
		if(a.getDescription()!=null)
		activitiesEvents.setDescription(a.getDescription());
		if(a.getType()!=null)
		activitiesEvents.setType(a.getType());
		if(a.getSubType()!=null)
		activitiesEvents.setSubType(a.getSubType());
		if(a.getCategory()!=null)
		activitiesEvents.setCategory(a.getCategory());
		if(a.getLatitude()!=null)
		activitiesEvents.setLatitude(a.getLatitude());
		if(a.getLongitude()!=null)
		activitiesEvents.setLongitude(a.getLongitude());
		if(a.getActivityMode()!=null)
		activitiesEvents.setActivityMode(a.getActivityMode());
		if(a.getMobile()!=null)
		activitiesEvents.setMobile(a.getMobile());
		if(a.getAddress()!=null)
		activitiesEvents.setAddress(a.getAddress());
		if(a.getEmailId()!=null)
		activitiesEvents.setEmailId(a.getEmailId());
		if(a.getAmount()!=null)
		activitiesEvents.setAmount(a.getAmount());
		if(a.getPaymentMode()!=null)
		activitiesEvents.setPaymentMode(a.getPaymentMode());
		if(a.getUpdatedTs()!=null)
		activitiesEvents.setUpdatedTs(a.getUpdatedTs());
		if(a.getWebsite() != null)
			activitiesEvents.setWebsite(a.getWebsite());
	
		s.update(activitiesEvents);
		
		
	}
	public void deleteActivityEvents(ActivitiesEvents activitiesEvents) {
		log.debug("deleting activitiesEvents instance");
		try {
			sessionFactory.getCurrentSession().delete(activitiesEvents);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	public List<ActivitiesEventsDTO> findInMyActivities(int memId, String title) {
		List<ActivitiesEventsDTO> list = null;
		title="%" + title + "%";
		String sql="SELECT AE.id, AE.title,AE.description,AE.created_by,AE.created_ts,AE.category,w.image_id,w.ref_type,w.start_date,M.gcm_key,"
				+ "(select count(*) from joinings_opinions gl where gl.opinion = 1 and gl.ref_id = AE.id AND (gl.ref_type=1 or gl.ref_type=2)) as totlikecnt,"
				+ "(select opinion from joinings_opinions gml where gml.member_id =? and gml.ref_id = AE.id and (gml.ref_type=1 or gml.ref_type=2)) as useopinion "
				+ " FROM members M,activities_events AE left outer join working_days w on (w.ref_id=AE.id and (w.ref_type=1 or w.ref_type=2)) where "
				+ " M.member_id=AE.created_by and AE.status=1 and AE.title like ? and AE.created_by=? or AE.id IN "
				+ "(SELECT GM.ref_id FROM joinings_opinions GM WHERE GM.member_id =? and (GM.ref_type=1 or GM.ref_type=2) and GM.opinion=1 order by GM.join_date desc) "
				+ "group by AE.id order by AE.updated_ts desc";
		//try{
			list=jdbcTemplate.query(sql,new Object[]{memId,title,memId,memId},new NeighbourhoodActivitiesRowMapper());
			/*}catch(Exception e){
				System.out.println("error in joinedevents"+e);
			}*/
			return  list; 
		
	}
	
	public List<ActivitiesEventsDTO> findCreatedActivities(int memId,int refType, int start) {
		List<ActivitiesEventsDTO> list = null;
		String sql="SELECT AE.id,AE.title,AE.created_by,AE.created_ts,AE.sub_type,w.image_id,m.user_name "+
					"From activities_events AE left outer join working_days w on (w.ref_id=AE.id and w.ref_type = AE.sub_type) "+
					"left outer join members m on m.member_id = AE.created_by "+
					"where (AE.status=1 and AE.created_by = ? and AE.sub_type = ?) group by AE.id order by AE.updated_ts desc "+
					"LIMIT 10 OFFSET ?";
		//try{
			list=jdbcTemplate.query(sql,new Object[]{memId,refType,start},new MyActivitiesEventsRowMapper());
			/*}catch(Exception e){
				System.out.println("error in joinedevents"+e);
			}*/
			return  list; 
		
	}

	public List<ActivitiesEventsDTO> findGroupEvents(int groupId, Date curDate,int start) {
		List<ActivitiesEventsDTO> list = null;
		String sql="SELECT AE.id, AE.title,AE.category,AE.description,AE.address,WD.image_id,WD.start_date,"
				+ "(select count(*) from joinings_opinions gj where gj.activity_type = 1 and gj.ref_id = AE.id and gj.ref_type=2) as totmemcnt "
				+ " FROM activities_events AE LEFT OUTER JOIN  working_days WD ON (WD.ref_id = AE.id AND WD.ref_type=2)  LEFT OUTER JOIN  "
				+ "members M ON (M.member_id = AE.created_by)  where AE.group_id=? and AE.status=1 and WD.start_date > ? "
				+ " order by WD.start_date asc  LIMIT 10 OFFSET ?";
		//try{
			list=jdbcTemplate.query(sql,new Object[]{groupId,curDate,start},new CityActivitiesRowMapper());
			/*}catch(Exception e){
				System.out.println("error in joinedevents"+e);
			}*/
			return  list; 
	}
	public List<ActivitiesEventsDTO> findGroupPastEvents(int groupId, Date curDate,int start) {
		List<ActivitiesEventsDTO> list = null;
		String sql="SELECT AE.id, AE.title,AE.category,AE.description,AE.address,WD.image_id,WD.start_date,"
				+ "(select count(*) from joinings_opinions gj where gj.activity_type = 1 and gj.ref_id = AE.id and gj.ref_type=2) as totmemcnt "
				+ " FROM activities_events AE LEFT OUTER JOIN  working_days WD ON (WD.ref_id = AE.id AND WD.ref_type=2)  LEFT OUTER JOIN  "
				+ "members M ON (M.member_id = AE.created_by)  where AE.group_id=? and AE.status=1 and WD.start_date < ? "
				+ " order by WD.start_date desc  LIMIT 10 OFFSET ?";
		//try{
			list=jdbcTemplate.query(sql,new Object[]{groupId,curDate,start},new CityActivitiesRowMapper());
			/*}catch(Exception e){
				System.out.println("error in joinedevents"+e);
			}*/
			return  list; 
	}

}
