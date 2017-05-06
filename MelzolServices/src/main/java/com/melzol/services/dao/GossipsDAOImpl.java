package com.melzol.services.dao;

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

import com.melzol.services.beans.GossipsDTO;
import com.melzol.services.beans.GossipsRowMapper;
import com.melzol.services.model.Gossips;
import com.melzol.services.util.Utils;

@Repository("gossipsDAO")
@Transactional
public class GossipsDAOImpl extends HibernateDaoSupport implements GossipsDAO{
	private static final Log log = LogFactory.getLog(GossipsDAOImpl.class);
	@Autowired
	private SessionFactory sessionFactory;
	private JdbcTemplate jdbcTemplate;
	
	 public GossipsDAOImpl() {
	       super();
	    }
	    
		@Autowired
	    public GossipsDAOImpl(DataSource dataSource) {
	        jdbcTemplate = new JdbcTemplate(dataSource);
	    }
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	protected void initDao() {
		// do nothing
	}
	@Autowired
	public void init(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
		this.sessionFactory=sessionFactory;
		
	}
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate=jdbcTemplate;
		
	}
	@Override
	@Autowired
	protected HibernateTemplate createHibernateTemplate(
			SessionFactory sessionFactory) {
		return super.createHibernateTemplate(sessionFactory);
	}
	public void save(Gossips gossips) {
	try {
		sessionFactory.getCurrentSession().save(gossips);
	} catch (RuntimeException e) {
		e.printStackTrace();
	}
		
	}
	public Gossips findById(int gossipId) {
		log.debug("getting Gossips instance with id: " + gossipId);
		try {
			Gossips instance = (Gossips) sessionFactory.getCurrentSession().get("com.melzol.services.model.Gossips", gossipId);
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
	
	public List<GossipsDTO> findByCat(int memId,int cat, int start) {
		List<GossipsDTO> list = null;
		String sql="SELECT G.gossip_id,G.title,G.description,G.image_id,G.category,G.web_link,M.user_name,G.created_by,M.gcm_key,"
				+ "(select count(*) from joinings_opinions gl where gl.opinion = 1 and gl.ref_id = G.gossip_id and gl.ref_type=3) as totlikecnt,"
				+ "(select count(*) from joinings_opinions gu where gu.opinion = 2 and gu.ref_id = G.gossip_id and gu.ref_type=3) as totunlikecnt,"
				+ "(select opinion from joinings_opinions gml where gml.member_id = ? and gml.ref_id = G.gossip_id and gml.ref_type=3) as useopinion "
				+ "FROM gossips G left outer join members M on (G.created_by=M.member_id) where G.category=? and G.status=1 and not exists "
				+ "(select  +1 from joinings_opinions jo where jo.ref_id = G.gossip_id and jo.ref_type = 3 and jo.activity_type = 3 and jo.member_id = ?)  "
				+ "order by G.updated_ts desc LIMIT 20 OFFSET ?";
		try{
		list=jdbcTemplate.query(sql,new Object[]{memId,cat,memId,start},new GossipsRowMapper());
		}catch(Exception e){
			System.out.println("error in joinedevents"+e);
		}
		return  list; 
	}

	public void incrementCount(Integer gossipId) {
		String sql="update Gossips set joinCount = IFNULL(memberCount, 0) + 1  where gossipId=:gossipId";
		try{
		Query query=sessionFactory.getCurrentSession().createQuery(sql);
			query.setParameter("gossipId", gossipId);
			query.executeUpdate();
		}catch(Exception e){
			System.out.println("the error in increment"+e);
		}
		
	}
	public List<GossipsDTO> findMyGossips(int memId, int start) {
		List<GossipsDTO> list = null;																	
		String sql="SELECT G.gossip_id, G.title,G.description,G.category,G.web_link,M.user_name,G.image_id,G.created_by,M.gcm_key,"
				+ "(select count(*) from joinings_opinions gl where gl.opinion = 1 and gl.ref_id = G.gossip_id and gl.ref_type=3) as totlikecnt,"
				+ "(select count(*) from joinings_opinions gu where gu.opinion = 2 and gu.ref_id = G.gossip_id and gu.ref_type=3) as totunlikecnt,"
				+ "(select opinion from joinings_opinions gml where gml.member_id =? and gml.ref_id = G.gossip_id and gml.ref_type=3) as useopinion "
				+ "FROM gossips G left outer join members M on (G.created_by=M.member_id) WHERE G.status=1 and G.created_by=? or G.gossip_id IN "
				+ "(SELECT GM.ref_id FROM joinings_opinions GM WHERE GM.member_id =? and GM.ref_type=3 and GM.opinion=1 order by GM.join_date desc) "
				+ "order by G.created_by=? desc,G.created_date desc LIMIT 20 OFFSET ?";
		try{
		list=jdbcTemplate.query(sql,new Object[]{memId,memId,memId,memId,start},new GossipsRowMapper());
		}catch(Exception e){
			System.out.println("error in joinedevents"+e);
		}
		return  list; 
	}
	public List<GossipsDTO> findByTitle(int memId,String title) {
		List<GossipsDTO> list = null;		
		title="%" + title + "%";
		String sql="SELECT G.gossip_id,G.title,G.description,G.category,G.web_link,G.image_id,M.user_name,G.created_by,M.gcm_key,"
				+ "(select count(*) from joinings_opinions gl where gl.opinion = 1 and gl.ref_id = G.gossip_id and gl.ref_type=3) as totlikecnt,"
				+ "(select count(*) from joinings_opinions gu where gu.opinion = 2 and gu.ref_id = G.gossip_id and gu.ref_type=3) as totunlikecnt,"
				+ "(select opinion from joinings_opinions gml where gml.member_id = ? and gml.ref_id = G.gossip_id and gml.ref_type=3) as useopinion "
				+ "FROM gossips G left outer join members M on (G.created_by=M.member_id) where G.status=1 and G.title like ? and  not exists "
				+ "(select  +1 from joinings_opinions jo where jo.ref_id = G.gossip_id and jo.ref_type = 3 and jo.activity_type = 3 and jo.member_id =?)  "
				+ " order by G.updated_ts desc";
		try{
		list=jdbcTemplate.query(sql,new Object[]{memId,title,memId},new GossipsRowMapper());
		}catch(Exception e){
			System.out.println("error in joinedevents"+e);
		}
		return  list; 
	}
	public List<GossipsDTO> findAll(int memId, int start) {
		List<GossipsDTO> list = null;
		String sql="SELECT G.gossip_id,G.title,G.description,G.category,G.web_link,G.image_id,M.user_name,G.created_by,M.gcm_key,"
				+ "(select count(*) from joinings_opinions gl where gl.opinion = 1 and gl.ref_id = G.gossip_id and gl.ref_type=3) as totlikecnt,"
				+ "(select count(*) from joinings_opinions gu where gu.opinion = 2 and gu.ref_id = G.gossip_id and gu.ref_type=3) as totunlikecnt,"
				+ "(select opinion from joinings_opinions gml where gml.member_id = ? and gml.ref_id = G.gossip_id and gml.ref_type=3) as useopinion  "
				+ "FROM gossips G left outer join members M on (G.created_by=M.member_id) where G.status=1 and not exists "
				+ "(select  +1 from joinings_opinions jo where jo.ref_id = G.gossip_id and jo.ref_type = 3 and jo.activity_type = 3 and jo.member_id = ?)  "
				+ "order by G.updated_ts desc LIMIT 10 OFFSET ?";
		//try{
		list=jdbcTemplate.query(sql,new Object[]{memId,memId,start},new GossipsRowMapper());
		/*}catch(Exception e){
			System.out.println("error in joinedevents"+e);
		}*/
		return  list; 
	}
	public List<GossipsDTO> findInMyGossips(int memId, String title) {
		List<GossipsDTO> list = null;	
		title="%" + title + "%";
		String sql="SELECT G.gossip_id, G.title,G.description,G.category,G.web_link,M.user_name,G.image_id,G.created_by,M.gcm_key,"
				+ "(select count(*) from joinings_opinions gl where gl.opinion = 1 and gl.ref_id = G.gossip_id and gl.ref_type) as totlikecnt,"
				+ "(select count(*) from joinings_opinions gu where gu.opinion = 2 and gu.ref_id = G.gossip_id and gu.ref_type=3) as totunlikecnt,"
				+ "(select opinion from joinings_opinions gml where gml.member_id =? and gml.ref_id = G.gossip_id and gml.ref_type=3) as useopinion "
				+ "FROM gossips G left outer join members M on (G.created_by=M.member_id) WHERE (G.status=1 and G.title like ? and G.created_by=?) or G.gossip_id IN "
				+ "(SELECT GM.ref_id FROM joinings_opinions GM WHERE GM.member_id =? and GM.ref_type=3 and GM.opinion=1 order by GM.join_date desc) "
				+ "order by G.created_by=? desc";
		try{
		list=jdbcTemplate.query(sql,new Object[]{memId,title,memId,memId,memId},new GossipsRowMapper());
		}catch(Exception e){
			System.out.println("error in joinedevents"+e);
		}
		return  list; 
	}

	public void updateGossips(Gossips g) {
		Session s=sessionFactory.getCurrentSession();
		Gossips gossip=(Gossips) s.load(Gossips.class,g.getGossipId());
		
		if(g.getTitle()!=null)
			gossip.setTitle(g.getTitle());
		if(g.getDescription()!=null)
			gossip.setDescription(g.getDescription());
		if(g.getWebLink()!=null)
			gossip.setWebLink(g.getWebLink());
		if(g.getCategory()!=null)
			gossip.setCategory(g.getCategory());
		if(g.getImageId()!=null)
			gossip.setImageId(g.getImageId());
		if(g.getcDate()!=null)
			gossip.setUpdatedTs(Utils.parseCreatedDate(g.getcDate()));
		s.update(gossip);
		
	}
	public void deleteGossip(Gossips gossip) {
		log.debug("deleting gossip instance");
		try {
			sessionFactory.getCurrentSession().delete(gossip);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	public List<GossipsDTO> findPopularGossips(int memId, int start) {
		List<GossipsDTO> list = null;
		String sql="SELECT G.gossip_id,G.title,G.description,G.category,G.web_link,G.image_id,M.user_name,G.created_by,M.gcm_key,"
				+ "(select count(*) from joinings_opinions gl where gl.opinion = 1 and gl.ref_id = G.gossip_id and gl.ref_type=3) as totlikecnt,"
				+ "(select count(*) from joinings_opinions gu where gu.opinion = 2 and gu.ref_id = G.gossip_id and gu.ref_type=3) as totunlikecnt,"
				+ "(select opinion from joinings_opinions gml where gml.member_id = ? and gml.ref_id = G.gossip_id and gml.ref_type=3) as useopinion  "
				+ "FROM gossips G left outer join members M on (G.created_by=M.member_id) where G.status=1 and not exists "
				+ "(select  +1 from joinings_opinions jo where jo.ref_id = G.gossip_id and jo.ref_type = 3 and jo.activity_type = 3 and jo.member_id = ?)  "
				+ "order by totlikecnt desc LIMIT 20 OFFSET ?";
		//try{
		list=jdbcTemplate.query(sql,new Object[]{memId,memId,start},new GossipsRowMapper());
		/*}catch(Exception e){
			System.out.println("error in joinedevents"+e);
		}*/
		return  list; 
	}

	public GossipsDTO findGossipView(int id, int memId) {
		GossipsDTO list = null;
		String sql="SELECT G.gossip_id, G.title,G.description,G.category,G.web_link,M.user_name,G.image_id,G.created_by,M.gcm_key,"
				+ "(select count(*) from joinings_opinions gl where gl.opinion = 1 and gl.ref_id =? and gl.ref_type=3) as totlikecnt,"
				+ "(select count(*) from joinings_opinions gu where gu.opinion = 2 and gu.ref_id = ? and gu.ref_type=3) as totunlikecnt,"
				+ "(select opinion from joinings_opinions gml where gml.member_id =? and gml.ref_id = ? and gml.ref_type=3) as useopinion "
				+ "FROM gossips G left outer join members M on (G.created_by=M.member_id) WHERE G.status=1 and G.gossip_id=?";
		//try{
		list=jdbcTemplate.queryForObject(sql,new Object[]{id,id,memId,id,id},new GossipsRowMapper());
		/*}catch(Exception e){
			System.out.println("error in joinedevents"+e);
		}*/
		return  list; 
	}

	public List<GossipsDTO> findMemberCreatedGossips(int memId, int start) {
		List<GossipsDTO> list = null;																	
		String sql="SELECT G.gossip_id, G.title,G.description,G.category,G.web_link,M.user_name,G.image_id,G.created_by,M.gcm_key,"
				+ "(select count(*) from joinings_opinions gl where gl.opinion = 1 and gl.ref_id = G.gossip_id and gl.ref_type=3) as totlikecnt,"
				+ "(select count(*) from joinings_opinions gu where gu.opinion = 2 and gu.ref_id = G.gossip_id and gu.ref_type=3) as totunlikecnt,	"
				+ "(select opinion from joinings_opinions gml where gml.member_id = ? and gml.ref_id = G.gossip_id and gml.ref_type=3) as useopinion  "
				+ " FROM gossips G left outer join members M on (G.created_by=M.member_id) WHERE G.status=1 and G.created_by=? order by G.created_date desc LIMIT 20 OFFSET ?";
		//try{
		list=jdbcTemplate.query(sql,new Object[]{memId,memId,start},new GossipsRowMapper());
		/*}catch(Exception e){
			System.out.println("error in joinedevents"+e);
		}*/
		return  list; 
	}

}
