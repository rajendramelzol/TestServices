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
import com.melzol.services.beans.MemberDTO;
import com.melzol.services.model.Blogs;
import com.melzol.services.rowmappers.BlogsRowMapper;
import com.melzol.services.rowmappers.MyBlogsRowMapper;
import com.melzol.services.rowmappers.PopularBloggerRowMapper;

@Repository("blogsDAO")
@Transactional
public class BlogsDAOImpl extends HibernateDaoSupport implements BlogsDAO {
	private static final Log log = LogFactory.getLog(BlogsDAOImpl.class);
	@Autowired
	private SessionFactory sessionFactory;
	private JdbcTemplate jdbcTemplate;
	
	  public BlogsDAOImpl() {
	       super();
	    }
	    
		@Autowired
	    public BlogsDAOImpl(DataSource dataSource) {
	        jdbcTemplate = new JdbcTemplate(dataSource);
	    }

	
	public JdbcTemplate getJdbcTemplate() {
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
	public void save(Blogs blogs) {
	try {
		sessionFactory.getCurrentSession().save(blogs);
	} catch (RuntimeException e) {
		e.printStackTrace();
	}
		
	}
	public BlogsDTO findBlogView(Integer memId,Integer blogId) {
		BlogsDTO blogs = null;
		String sql="select B.blog_id,B.title,B.description,B.web_link,B.created_by,B.created_ts,B.image_id,M.user_name,M.profile_pic,M.gcm_key,"
				+ "(select count(*) from joinings_opinions gl where gl.opinion = 1 and gl.ref_id = B.blog_id and gl.ref_type=4) as totlikecnt,"
				+ "(select opinion from joinings_opinions jo where jo.member_id = ? and jo.ref_id=? and jo.ref_type=4) as useopinion, "
				+"(select status from blog_subscribe bs where bs.melzol_id=? and bs.subscribe_id=B.created_by) as subscribestatus "
				+ "from blogs B LEFT OUTER JOIN  members M ON M.member_id = B.created_by  where B.blog_id=?";
		//try{
			blogs=(BlogsDTO) jdbcTemplate.queryForObject(sql, new Object[]{memId,blogId,memId,blogId},new BlogViewRowMapper());
		/*}catch(Exception e){
			System.out.println("error in joinedevents"+blogs);
		}*/
		return  blogs;
	}
	public List<BlogsDTO> findAll(int memId,int page) {
		List<BlogsDTO> blogs = null;
		String sql="SELECT B.blog_id, B.title,B.description,B.created_by,B.created_ts,B.image_id,M.user_name,M.profile_pic,M.gcm_key,"
				+ "(select count(*) from joinings_opinions gl where gl.opinion = 1 and gl.ref_id = B.blog_id and gl.ref_type=4) as totlikecnt,"
				+ "(select opinion from joinings_opinions gml where gml.member_id = ? and gml.ref_id = B.blog_id and gml.ref_type=4) as useopinion "
				+ "FROM blogs B LEFT OUTER JOIN  members M ON (M.member_id = B.created_by) where B.status=1 order by B.updated_ts desc  LIMIT 10 OFFSET ?";
		//try{
			blogs=jdbcTemplate.query(sql, new Object[]{memId,page},new BlogsRowMapper());
		//}catch(Exception e){
			System.out.println("error in joinedevents"+blogs);
		//}
		return  blogs;
		
	}
	public List<BlogsDTO> findMyBlogs(int memId, int start) {
		List<BlogsDTO> blogs = null;
		String sql="SELECT B.blog_id, B.title,B.description,B.created_by,B.created_ts,B.image_id,M.user_name,M.profile_pic,M.gcm_key,"
				+ "(select count(*) from joinings_opinions gl where gl.opinion = 1 and gl.ref_id = B.blog_id and gl.ref_type=4) as totlikecnt,"
				+ "(select opinion from joinings_opinions gml where gml.member_id =? and gml.ref_id = B.blog_id and gml.ref_type=4) as useopinion "
				+ "FROM blogs B LEFT OUTER JOIN  members M ON M.member_id = B.created_by where B.status=1 and B.created_by=? or B.blog_id IN "
				+ "(SELECT GM.ref_id FROM joinings_opinions GM WHERE GM.member_id =? and GM.ref_type=4 and GM.opinion=1 order by GM.join_date desc) "
				+ "order by B.created_by=? desc,B.created_ts desc  LIMIT 10 OFFSET ?";
		//try{
			blogs=jdbcTemplate.query(sql, new Object[]{memId,memId,memId,memId,start},new MyBlogsRowMapper());
		//}catch(Exception e){
			System.out.println("error in joinedevents"+blogs);
		//}
		return  blogs;
	}
	public List<BlogsDTO> findBlog(int memId, String title) {
		List<BlogsDTO> blogs = null;
		title="%" + title + "%";
		String sql="SELECT B.blog_id,B.title,B.description,B.created_by,B.created_ts,B.image_id,M.user_name,M.profile_pic,M.gcm_key,"
				+ "(select count(*) from joinings_opinions gl where gl.opinion = 1 and gl.ref_id = B.blog_id and gl.ref_type=4) as totlikecnt,"
				+ "(select opinion from joinings_opinions gml where gml.member_id =? and gml.ref_id = B.blog_id and gml.ref_type=4) as useopinion "
				+ "FROM blogs B LEFT OUTER JOIN  members M ON (M.member_id = B.created_by) where B.status=1 and B.title like ?";
		//try{
			blogs=jdbcTemplate.query(sql, new Object[]{memId,title},new MyBlogsRowMapper());
		//}catch(Exception e){
			System.out.println("error in joinedevents"+blogs);
		//}
		return  blogs;
	}
	public List<BlogsDTO> findInMyBlogs(int memId, String title) {
		List<BlogsDTO> blogs = null;
		title="%" + title + "%";
		String sql="SELECT B.blog_id, B.title,B.description,B.created_by,B.created_ts,B.image_id,M.user_name,M.profile_pic,M.gcm_key,"
				+ "(select count(*) from joinings_opinions gl where gl.opinion = 1 and gl.ref_id = B.blog_id and gl.ref_type=4) as totlikecnt,"
				+ "(select opinion from joinings_opinions gml where gml.member_id =? and gml.ref_id = B.blog_id and gml.ref_type=4) as useopinion "
				+ "FROM blogs B LEFT OUTER JOIN  members M ON M.member_id = B.created_by where B.status=1 and B.title like ? and B.created_by=? or B.blog_id IN "
				+ "(SELECT GM.ref_id FROM joinings_opinions GM WHERE GM.member_id =? and GM.ref_type=4 and GM.opinion=1 order by GM.join_date desc) "
				+ "order by B.created_by=? desc";
		//try{
			blogs=jdbcTemplate.query(sql, new Object[]{memId,title,memId,memId,memId},new MyBlogsRowMapper());
		//}catch(Exception e){
			System.out.println("error in joinedevents"+blogs);
		//}
		return  blogs;
	}

	public void updateBlogs(Blogs b) {

		Session s=sessionFactory.getCurrentSession();
		Blogs blog=(Blogs) s.load(Blogs.class,b.getBlogId());
		
		if(b.getTitle()!=null)
		blog.setTitle(b.getTitle());
		if(b.getDescription()!=null)
		blog.setDescription(b.getDescription());
		if(b.getCategoryId()!=null)
			blog.setCategoryId(b.getCategoryId());
		if(b.getImageId()!=null)
		blog.setImageId(b.getImageId());
		if(b.getUpdatedTs()!=null)
		blog.setUpdatedTs(b.getUpdatedTs());
		if(b.getCategoryId() !=null)
			blog.setCategoryId(b.getCategoryId());

		s.update(blog);
	}

	
	public Blogs findById(int blogId) {
		log.debug("getting blogId instance with id: " + blogId);
		try {
			Blogs instance = (Blogs) sessionFactory.getCurrentSession().get("com.melzol.services.model.Blogs", blogId);
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

	public void deleteBlog(Blogs blog) {
		log.debug("deleting blog instance");
		try {
			sessionFactory.getCurrentSession().delete(blog);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}	
	}
	
	public List<BlogsDTO> findPopular(int memId,int page) {
		List<BlogsDTO> blogs = null;
		String sql="SELECT B.blog_id, B.title,B.description,B.created_by,B.created_ts,B.image_id,M.user_name,M.profile_pic,M.gcm_key,"
				+ "(select count(*) from joinings_opinions gl where gl.opinion = 1 and gl.ref_id = B.blog_id and gl.ref_type=4) as totlikecnt,"
				+ "(select opinion from joinings_opinions gml where gml.member_id = ? and gml.ref_id = B.blog_id and gml.ref_type=4) as useopinion "
				+ "FROM blogs B LEFT OUTER JOIN  members M ON (M.member_id = B.created_by) where B.status=1 order by totlikecnt desc  LIMIT 10 OFFSET ?";
		//try{
			blogs=jdbcTemplate.query(sql, new Object[]{memId,page},new BlogsRowMapper());
		//}catch(Exception e){
			System.out.println("error in joinedevents"+blogs);
		//}
		return  blogs;
		
	}
	
	public List<BlogsDTO> findByCat(int catId,int memId,int page) {
		List<BlogsDTO> blogs = null;
		String sql="SELECT B.blog_id, B.title,B.description,B.created_by,B.created_ts,B.image_id,M.user_name,M.profile_pic,M.gcm_key,"
				+ "(select count(*) from joinings_opinions gl where gl.opinion = 1 and gl.ref_id = B.blog_id and gl.ref_type=4) as totlikecnt,"
				+ "(select opinion from joinings_opinions gml where gml.member_id = ? and gml.ref_id = B.blog_id and gml.ref_type=4) as useopinion "
				+ "FROM blogs B LEFT OUTER JOIN  members M ON (M.member_id = B.created_by) where B.status=1 and B.category=? order by B.updated_ts desc  LIMIT 10 OFFSET ?";
		//try{
			blogs=jdbcTemplate.query(sql, new Object[]{memId,catId,page},new BlogsRowMapper());
		//}catch(Exception e){
			System.out.println("error in joinedevents"+blogs);
		//}
		return  blogs;
		
	}
	public List<MemberDTO> findPopularBloggers(int memId, int start) {
		List<MemberDTO> list = null;
		String sql=" SELECT M.member_id,M.user_name,M.profile_pic,M.gcm_key,M.latitude,M.longitude,count(B.created_by) as count ,"
				+ "(select status from blog_subscribe bs where bs.melzol_id=? and bs.subscribe_id=B.created_by) as subscribestatus "
				+ " FROM members M,blogs B WHERE  M.member_id=B.created_by AND B.status=1 group by B.created_by order by "
				+ "count desc LIMIT 10 OFFSET ?";
		try{
		list=jdbcTemplate.query(sql,new Object[]{memId,start},new PopularBloggerRowMapper());
		System.out.println("the result in tagged members xooo838"+list);
		}catch(Exception e){
			System.out.println("error in InviteMembers"+e);
		}
		return  list;
	}

	public List<BlogsDTO> findBloggerBlogs(int bloggerId, int memId, int start) {
		List<BlogsDTO> blogs = null;
		String sql="SELECT B.blog_id, B.title,B.description,B.created_by,B.created_ts,B.image_id,M.user_name,M.profile_pic,M.gcm_key,"
				+ "(select count(*) from joinings_opinions gl where gl.opinion = 1 and gl.ref_id = B.blog_id and gl.ref_type=4) as totlikecnt,"
				+ "(select opinion from joinings_opinions gml where gml.member_id =? and gml.ref_id = B.blog_id and gml.ref_type=4) as useopinion "
				+ " FROM blogs B LEFT OUTER JOIN  members M ON M.member_id = B.created_by where B.status=1 and B.created_by=? "
				+ "order by B.created_ts desc  LIMIT 10 OFFSET ?";
		//try{
			blogs=jdbcTemplate.query(sql, new Object[]{memId,bloggerId,start},new MyBlogsRowMapper());
		//}catch(Exception e){
			System.out.println("error in joinedevents"+blogs);
		//}
		return  blogs;
	}
	
	

}
