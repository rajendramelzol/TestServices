package com.melzol.services.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.melzol.services.beans.BlogsDTO;
import com.melzol.services.beans.CommentsDTO;
import com.melzol.services.beans.MemberDTO;
import com.melzol.services.dao.BlogsDAO;
import com.melzol.services.dao.CommentsDAO;
import com.melzol.services.model.Blogs;
import com.melzol.services.util.Utils;

@Repository
@Service("blogsService")
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class BlogsServiceImpl implements BlogsService{
	@Autowired
	BlogsDAO blogsDAO;
	@Autowired
	CommentsDAO commentsDAO;
	
	
	public CommentsDAO getCommentsDAO() {
		return commentsDAO;
	}
	public void setCommentsDAO(CommentsDAO commentsDAO) {
		this.commentsDAO = commentsDAO;
	}
	public void addBlog(Blogs blogs) {
		blogsDAO.save(blogs);

		
	}
	public BlogsDTO searchBlogView(Integer memId,Integer blogId,Integer refType) {
		BlogsDTO blogsDTO = blogsDAO.findBlogView(memId,blogId);
		List<CommentsDTO> comments= commentsDAO.findAll(blogId,refType,0);
		for(CommentsDTO c:comments){
			c.setcDate(Utils.formatDateComments(c.getCreatedTs()));
		}
		blogsDTO.setComments(comments);
		return blogsDTO;
	}
	public List<BlogsDTO> searchAll(int memId,int page) {
		return blogsDAO.findAll(memId,page);
	}
	public List<BlogsDTO> searchMyBlogs(int memId, int start) {
		return blogsDAO.findMyBlogs(memId,start);
	}
	public List<BlogsDTO> searchBlog(int memId, String title) {
		
		return blogsDAO.findBlog(memId,title);
	}
	public List<BlogsDTO> searchInMyBlogs(int memId, String title) {
		return blogsDAO.findInMyBlogs(memId,title);
	}

	public void updateBlogs(Blogs blogs) {
		blogsDAO.updateBlogs(blogs);
		
	}
	public Blogs searchById(int blogId) {
		return blogsDAO.findById(blogId);
	}
	public void deleteBlog(Blogs blog) {
		blogsDAO.deleteBlog(blog);
		
	}
	
	public List<BlogsDTO> searchPopularBlogs(int memId,int page) {
		return blogsDAO.findPopular(memId,page);
	}
	
	public List<BlogsDTO> searchBlogsByCat(int catId,int memId,int page) {
		return blogsDAO.findByCat(catId,memId,page);
	}

	public List<MemberDTO> searchPopularBloggers(int memId, int start) {
	
		return blogsDAO.findPopularBloggers(memId,start);
	}

	public List<BlogsDTO> searchBloggerBlogs(int bloggerId, int memId, int start) {
	
		return blogsDAO.findBloggerBlogs(bloggerId,memId,start);
	}

}
