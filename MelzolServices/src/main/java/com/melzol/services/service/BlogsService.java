package com.melzol.services.service;

import java.util.List;

import com.melzol.services.beans.BlogsDTO;
import com.melzol.services.beans.MemberDTO;
import com.melzol.services.model.Blogs;

public interface BlogsService {

	public abstract void addBlog(Blogs blogs);

	public abstract BlogsDTO searchBlogView(Integer memId,Integer blogId,Integer refType);

	public abstract List<BlogsDTO> searchAll(int memId, int page);

	public abstract List<BlogsDTO> searchMyBlogs(int memId, int start);

	public abstract List<BlogsDTO> searchBlog(int memId, String title);

	public abstract List<BlogsDTO> searchInMyBlogs(int memId, String title);

	public abstract void updateBlogs(Blogs blogs);

	public abstract Blogs searchById(int blogId);

	public abstract void deleteBlog(Blogs blog);
	
	public abstract List<BlogsDTO> searchPopularBlogs(int memId, int page);
	
	public abstract List<BlogsDTO> searchBlogsByCat(int catId,int memId, int page);

	public abstract List<MemberDTO> searchPopularBloggers(int memId, int start);

	public abstract List<BlogsDTO> searchBloggerBlogs(int bloggerId, int memId, int start);

}
