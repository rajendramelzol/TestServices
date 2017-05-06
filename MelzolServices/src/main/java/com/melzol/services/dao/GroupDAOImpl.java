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

import com.melzol.services.beans.GroupDTO;
import com.melzol.services.model.Group;
import com.melzol.services.rowmappers.GroupViewRowMapper;
import com.melzol.services.rowmappers.GroupsByCatRowMapper;

@Repository("groupDAO")
@Transactional
public class GroupDAOImpl extends HibernateDaoSupport implements GroupDAO {
	private static final Log log = LogFactory.getLog(GroupDAOImpl.class);
	@Autowired
	private SessionFactory sessionFactory;
    private JdbcTemplate jdbcTemplate;
	
    public GroupDAOImpl() {
       super();
    }
    
	@Autowired
    public GroupDAOImpl(DataSource dataSource) {
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
	
	public void save(Group persistentInstance) {
		try {
			sessionFactory.getCurrentSession().save(persistentInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public Group findById(java.lang.Integer groupId) {
		log.debug("getting Event instance with id: " + groupId);
		try {
			Group instance = (Group) sessionFactory.getCurrentSession().get("com.melzol.services.model.Group", groupId);
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

	public void update(Group g) {
		Session s=sessionFactory.getCurrentSession();
		Group group=(Group) s.load(Group.class,g.getGroupId());
		if(g.getTitle()!=null)
		group.setTitle(g.getTitle());
		if(g.getDescription()!=null)
		group.setDescription(g.getDescription());
		if(g.getCategory()!=null)
			group.setCategory(g.getCategory());
		if(g.getImageId()!=null)
			group.setImageId(g.getImageId());
		if(g.getUpdatedTs()!=null)
			group.setUpdatedTs(g.getUpdatedTs());
	
		s.update(group);
		
	}

	public void delete(Group group) {
		log.debug("deleting Group instance");
		try {
			sessionFactory.getCurrentSession().delete(group);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
		
	}
	public List<GroupDTO> findGroupsByCat(String city, int cat, int start) {
		List<GroupDTO> list = null;
		String sql="SELECT G.group_id, G.title,G.category,G.image_id,G.created_by,"
				+ "(select count(*) from joinings_opinions gj where gj.activity_type = 1 and gj.ref_id = G.group_id and gj.ref_type=1) as totmemcnt "
				+ " FROM groups G  LEFT OUTER JOIN  members M ON (M.member_id = G.created_by)  where M.city=?  and G.category=? and G.status=1 "
				+ "order by G.updated_ts desc LIMIT 10 OFFSET ?";
		try{
			list=jdbcTemplate.query(sql,new Object[]{city,cat,start},new GroupsByCatRowMapper());
			}catch(Exception e){
				System.out.println("error in joinedevents"+e);
			}
			return  list; 
	}

	
	public GroupDTO findGroupView(int id, int memid) {
		String sql="SELECT G.group_id, G.title,G.description,G.category,G.image_id,G.created_by,M.user_name,M.profile_pic,M.gcm_key,"
				+ "(select count(*) from joinings_opinions gj where gj.activity_type = 1 and gj.ref_id = G.group_id and gj.ref_type=1) as totmemcnt,"
				+ "(select activity_type from joinings_opinions gml where gml.member_id = ? and gml.ref_id = G.group_id and gml.ref_type=1) as joined "
				+ " FROM groups G LEFT OUTER JOIN  members M ON M.member_id = G.created_by where G.group_id=? and G.status=1 ";
		
		GroupDTO ac=jdbcTemplate.queryForObject(sql,new Object[]{memid,id},new GroupViewRowMapper());
		return ac;
	}

	public List<GroupDTO> findMyGroups(int memId, int start) {
		List<GroupDTO> list = null;
		String sql="SELECT G.group_id, G.title,G.category,G.image_id,G.created_by,"
				+ "(select count(*) from joinings_opinions gj where gj.activity_type = 1 and gj.ref_id = G.group_id and gj.ref_type=1) as totmemcnt "
				+ " FROM groups G  where G.status=1 and G.created_by=? or G.group_id IN "
				+ "(SELECT GM.ref_id FROM joinings_opinions GM WHERE GM.member_id =? and GM.ref_type=1 and GM.activity_type=1 order by GM.join_date desc) "
				+ "order by G.created_by=? desc  LIMIT 10 OFFSET ?";
		try{
			list=jdbcTemplate.query(sql,new Object[]{memId,memId,memId,start},new GroupsByCatRowMapper());
			}catch(Exception e){
				System.out.println("error in joinedevents"+e);
			}
			return  list; 
	}

	public List<GroupDTO> findMemberCreatedGroups(int memId, int start) {
		List<GroupDTO> list = null;
		String sql="SELECT G.group_id, G.title,G.category,G.image_id,G.created_by,"
				+ "(select count(*) from joinings_opinions gj where gj.activity_type = 1 and gj.ref_id = G.group_id and gj.ref_type=1) as totmemcnt "
				+ " FROM groups G  where G.status=1 and G.created_by=? order by G.created_ts desc  LIMIT 10 OFFSET ?";
		try{
			list=jdbcTemplate.query(sql,new Object[]{memId,start},new GroupsByCatRowMapper());
			}catch(Exception e){
				System.out.println("error in joinedevents"+e);
			}
			return  list; 
	}

}
