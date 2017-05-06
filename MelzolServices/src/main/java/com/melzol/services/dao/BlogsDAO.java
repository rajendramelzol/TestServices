package com.melzol.services.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.melzol.services.beans.BlogsDTO;
import com.melzol.services.beans.MemberDTO;
import com.melzol.services.model.Blogs;

public interface BlogsDAO {
	
	public abstract JdbcTemplate getJdbcTemplate();
	
	public abstract void setJdbcTemplate(JdbcTemplate jdbcTemplate);
	
	public abstract void init(SessionFactory sessionFactory);
	
   public abstract void save(Blogs blogs);

   public abstract BlogsDTO findBlogView(Integer memId,Integer blogId);

   public abstract List<BlogsDTO> findAll(int memId, int page);

   public abstract List<BlogsDTO> findMyBlogs(int memId, int start);

   public abstract List<BlogsDTO> findBlog(int memId, String title);

   public abstract List<BlogsDTO> findInMyBlogs(int memId, String title);

   public abstract void updateBlogs(Blogs blogs);

    public abstract Blogs findById(int blogId);

	public abstract void deleteBlog(Blogs blog);
	
	public abstract List<BlogsDTO> findPopular(int memId, int page);
	
	public abstract List<BlogsDTO> findByCat(int catId,int memId, int page);

	public abstract List<MemberDTO> findPopularBloggers(int memId, int start);

	public abstract List<BlogsDTO> findBloggerBlogs(int bloggerId, int memId, int start);

}
