package com.melzol.services.dao;

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

import com.melzol.services.beans.BlogViewRowMapper;
import com.melzol.services.beans.BlogsDTO;
import com.melzol.services.beans.ForumDTO;
import com.melzol.services.model.Forum;
import com.melzol.services.rowmappers.ForumRowMapper;
import com.melzol.services.rowmappers.ForumViewRowMapper;

@Repository("forumDAO")
@Transactional
public class ForumDAOImpl extends HibernateDaoSupport implements ForumDAO{
	private static final Log log = LogFactory.getLog(ForumDAOImpl.class);
	@Autowired
	private SessionFactory sessionFactory;
	private JdbcTemplate jdbcTemplate;

	 public ForumDAOImpl() {
	       super();
	    }
	    
		@Autowired
	    public ForumDAOImpl(DataSource dataSource) {
	        jdbcTemplate = new JdbcTemplate(dataSource);
	    }
	public JdbcTemplate geJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate=jdbcTemplate;
		
	}

	@Autowired
	public void init(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
		this.sessionFactory=sessionFactory;
		
	}
	@Override
	@Autowired
	protected HibernateTemplate createHibernateTemplate(
			SessionFactory sessionFactory) {
		return super.createHibernateTemplate(sessionFactory);
	}
	public void save(Forum forum) {
		try {
			sessionFactory.getCurrentSession().save(forum);
		} catch (RuntimeException e) {
			e.printStackTrace();
	     }
	}
	public Forum findById(Integer forumId) {
		log.debug("getting Forum instance with id: " + forumId);
		try {
			Forum instance = (Forum) sessionFactory.getCurrentSession().get("com.melzol.services.model.Forum", forumId);
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

	public List<ForumDTO> findAll(Double lat,Double lon,int memId, int start) { 
		List<ForumDTO> list = null;
		String sql="SELECT F.forum_id, F.title,F.description,Ng.user_name,Ng.gcm_key,Ng.profile_pic,F.image_id,F.created_by,F.created_ts,"
				+ "(select count(*) from joinings_opinions gl where gl.opinion = 1 and gl.ref_id = F.forum_id and gl.ref_type=5) as totlikecnt,"
				+ "(select opinion from joinings_opinions gml where gml.member_id = ? and gml.ref_id = F.forum_id and gml.ref_type=5) as useopinion,"
				+ "(((acos(sin((?*pi()/180)) * sin((F.latitude*pi()/180))+cos((?*pi()/180))* cos((F.latitude*pi()/180)) * "
				+ "cos(((? - F.longitude)*pi()/180))))*180/pi())*60*1.1515) AS `distance` "
				+ "FROM  members Ng,forum F where  Ng.member_id=F.created_by and F.status=1 and not exists"
				+ "(select  +1 from joinings_opinions jo where jo.ref_id = F.forum_id and jo.ref_type = 5 and jo.activity_type = 3 and jo.member_id = ?) "
				+ " HAVING `distance` <= 1.5 order by F.updated_ts desc LIMIT 10 OFFSET ?";
		//try{
		list=jdbcTemplate.query(sql,new Object[]{memId,lat,lat,lon,memId,start},new ForumRowMapper());
		/*}catch(Exception e){
			System.out.println("error in joinedevents"+e);
		}*/
		return  list; 
	}

	public List<ForumDTO> findForum(Double lat,Double lon,int memId, String title) {
		List<ForumDTO> list = null;
		title="%" + title + "%";
		String sql="SELECT F.forum_id, F.title,F.description,Ng.user_name,Ng.gcm_key,Ng.profile_pic,F.image_id,F.created_by,F.created_ts,"
				+ "(select count(*) from joinings_opinions gl where gl.opinion = 1 and gl.ref_id = F.forum_id and gl.ref_type=5) as totlikecnt,"
				+ "(select opinion from joinings_opinions gml where gml.member_id = ? and gml.ref_id = F.forum_id and gml.ref_type=5) as useopinion,"
				+ "(((acos(sin((?*pi()/180)) * sin((LN.latitude*pi()/180))+cos((?*pi()/180))* cos((LN.latitude*pi()/180)) * "
				+ "cos(((? - LN.longitude)*pi()/180))))*180/pi())*60*1.1515) AS `distance` "
				+ "FROM latitude_longitude LN, members Ng,forum F where Ng.address_refid = LN.id and Ng.member_id=F.created_by and F.status=1 and F.title like ? and not exists"
				+ "(select  +1 from joinings_opinions jo where jo.ref_id = F.forum_id and jo.ref_type = 5 and jo.activity_type = 3 and jo.member_id = ?) "
				+ " HAVING `distance` <= 1.5 order by F.updated_ts desc";
		//try{
		list=jdbcTemplate.query(sql,new Object[]{memId,lat,lat,lon,title,memId},new ForumRowMapper());
		//}catch(Exception e){
			//System.out.println("error in joinedevents"+e);
		//}
		return  list; 
	}

	public List<ForumDTO> findMyForum(int memId, int start) {
		List<ForumDTO> list = null;
		String sql="SELECT F.forum_id, F.title,F.description,M.user_name,M.gcm_key,M.profile_pic,F.image_id,F.created_by,F.created_ts,"
				+ "(select count(*) from joinings_opinions gl where gl.opinion = 1 and gl.ref_id = F.forum_id and gl.ref_type=5) as totlikecnt,"
				+ "(select opinion from joinings_opinions gml where gml.member_id =? and gml.ref_id = F.forum_id and gml.ref_type=5) as useopinion "
				+ "FROM forum F left outer join members M on (F.created_by=M.member_id) WHERE F.status=1 and F.created_by=? or F.forum_id IN "
				+ "(SELECT GM.ref_id FROM joinings_opinions GM WHERE GM.member_id =? and GM.ref_type=5 and GM.opinion=1 order by GM.join_date desc) "
				+ "order by F.created_by=? desc LIMIT 10 OFFSET ?";
		try{
		list=jdbcTemplate.query(sql,new Object[]{memId,memId,memId,memId,start},new ForumRowMapper());
		}catch(Exception e){
			System.out.println("error in joinedevents"+e);
		}
		return  list; 
	}
	public List<ForumDTO> findInMyForum(int memId, String title) {
		List<ForumDTO> list = null;
		title="%" + title + "%";
		String sql="SELECT F.forum_id, F.title,F.description,M.user_name,M.gcm_key,M.profile_pic,F.image_id,F.created_by,F.created_ts,"
				+ "(select count(*) from joinings_opinions gl where gl.opinion = 1 and gl.ref_id = F.forum_id and gl.ref_type=5) as totlikecnt,"
				+ "(select opinion from joinings_opinions gml where gml.member_id =? and gml.ref_id = F.forum_id and gml.ref_type=5) as useopinion "
				+ "FROM forum F left outer join members M on (F.created_by=M.member_id) WHERE F.status=1 and F.title like ? and F.created_by=? or F.forum_id IN "
				+ "(SELECT GM.ref_id FROM joinings_opinions GM WHERE GM.member_id =? and GM.ref_type=5 and GM.opinion=1 order by GM.join_date desc) "
				+ "order by F.created_by=? desc";
		try{
		list=jdbcTemplate.query(sql,new Object[]{memId,title,memId,memId,memId},new ForumRowMapper());
		}catch(Exception e){
			System.out.println("error in joinedevents"+e);
		}
		return  list; 
	}

	public void updateForum(Forum f) {
		
		Session s=sessionFactory.getCurrentSession();
		Forum forum=(Forum) s.load(Forum.class,f.getForumId());
		
		if(f.getTitle()!=null)
		forum.setTitle(f.getTitle());
		if(f.getDescription()!=null)
		forum.setDescription(f.getDescription());
		if(f.getImageId()!=null)
			forum.setImageId(f.getImageId());
		if(f.getUpdatedTs()!=null)
		forum.setUpdatedTs(f.getUpdatedTs());
		s.update(forum);
		
	}

	public void deleteForum(Forum forum) {
		log.debug("deleting forum instance");
		try {
			sessionFactory.getCurrentSession().delete(forum);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
		
	}

	public ForumDTO findForumView(int id) {
		ForumDTO forum = null;
		String sql="select F.forum_id,F.description,F.created_by,F.created_ts,F.image_id,M.user_name,M.profile_pic,M.gcm_key "
				+ " from forum F LEFT OUTER JOIN  members M ON M.member_id = F.created_by  where F.forum_id=?";
		//try{
		forum=(ForumDTO) jdbcTemplate.queryForObject(sql, new Object[]{id},new ForumViewRowMapper());
		/*}catch(Exception e){
			System.out.println("error in joinedevents"+blogs);
		}*/
		return  forum;
	}
	
	
	

}
