package com.melzol.services.controller;

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

import com.melzol.services.beans.BlogsDTO;
import com.melzol.services.beans.MemberDTO;
import com.melzol.services.model.Blogs;
import com.melzol.services.service.BlogsService;
import com.melzol.services.util.Utils;

@RestController
@RequestMapping("/blogs")
public class BlogsRestController {
	@Autowired
	BlogsService blogsService;
	
	
	//-------------------Create a Blog--------------------------------------------------------
	@RequestMapping(value ="/add" ,method = RequestMethod.POST)
	public ResponseEntity<Blogs> createBlog(@RequestBody Blogs blogs,UriComponentsBuilder ucBuilder){
		System.out.println("Creating createBlog x0001 " + blogs.getTitle());
		blogs.setStatus(1);
		blogs.setCreatedTs(Utils.parseCreatedDate(blogs.getfDate()));
		blogs.setUpdatedTs(Utils.parseCreatedDate(blogs.getfDate()));
		blogsService.addBlog(blogs);
		//BlogsDTO a=blogsService.searchById(blogs.getBlogId());
		return new ResponseEntity<Blogs>(blogs, HttpStatus.CREATED);
	}
	//-------------------Retrieve a Blogs--------------------------------------------------------
			@RequestMapping(value ="/search/{blogId}" ,method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
			public ResponseEntity<Blogs> searchBlog(@PathVariable("blogId") int blogId){
				System.out.println("searching Blog x0001 " + blogId);
				Blogs blog=blogsService.searchById(blogId);
				if(blog==null){
					return new ResponseEntity<Blogs>(HttpStatus.NOT_FOUND);
				}
				return new ResponseEntity<Blogs>(blog, HttpStatus.OK);
			}
	//-------------------Retrieve a Blogs--------------------------------------------------------
		@RequestMapping(value ="/blogview/{memId}/{blogId}" ,method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<BlogsDTO> searchBlogView(@PathVariable("memId") int memId,@PathVariable("blogId") int blogId){
			System.out.println("searching Blog x0001 " + blogId);
			int refType=4;
			BlogsDTO blog=blogsService.searchBlogView(memId,blogId,refType);
			if(blog==null){
				return new ResponseEntity<BlogsDTO>(HttpStatus.NOT_FOUND);
			}
			String fdate=Utils.formatDateBlogs(blog.getCreatedTs());
				blog.setfDate(fdate);
			
			return new ResponseEntity<BlogsDTO>(blog, HttpStatus.OK);
		}
	//-------------------Retrieve city  Blogs--------------------------------------------------------
		
		@RequestMapping(value = "/searchall/{memId}/{page}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<List<BlogsDTO>> getAllBlogs(@PathVariable("memId") int memId,@PathVariable("page") int page) {
			System.out.println("Fetching all blogs with X00001 page " + page);
			int start = (page-1)*10;
			//int end=page*10;
			List<BlogsDTO> blogs = blogsService.searchAll(memId,start);
			if (blogs == null) {
				System.out.println("blogs with page " + page + " not found");
				return new ResponseEntity<List<BlogsDTO>>(HttpStatus.NOT_FOUND);
			}
			String fdate;
			for(BlogsDTO b:blogs){
				fdate=Utils.formatDateBlogs(b.getCreatedTs());
				b.setfDate(fdate);
			}
			return new ResponseEntity<List<BlogsDTO>>(blogs, HttpStatus.OK);
		}
	//-------------------Retrieve my  Blogs--------------------------------------------------------
		
				@RequestMapping(value = "/searchMyBlogs/{memId}/{page}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
				public ResponseEntity<List<BlogsDTO>> getMyBlogs(@PathVariable("memId") int memId,@PathVariable("page") int page) {
					System.out.println("Fetching all blogs with X00001 page " + page);
					int start = (page-1)*10;
					//int end=page*10;
					List<BlogsDTO> blogs = blogsService.searchMyBlogs(memId,start);
					if (blogs == null) {
						System.out.println("blogs with page " + page + " not found");
						return new ResponseEntity<List<BlogsDTO>>(HttpStatus.NOT_FOUND);
					}
					String fdate;
					for(BlogsDTO b:blogs){
						fdate=Utils.formatDateBlogs(b.getCreatedTs());
						b.setfDate(fdate);
					}
					return new ResponseEntity<List<BlogsDTO>>(blogs, HttpStatus.OK);
				}
				
	//-------------------search a  Blog--------------------------------------------------------
				
				@RequestMapping(value = "/searchblog/{memId}/{title}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
				public ResponseEntity<List<BlogsDTO>> searchBlogs(@PathVariable("memId") int memId,@PathVariable("title") String title) {
					System.out.println("Fetching all blogs with X00001 title " + title);
					List<BlogsDTO> blogs = blogsService.searchBlog(memId,title);
					if (blogs == null) {
						System.out.println("blogs with page " + title + " not found");
						return new ResponseEntity<List<BlogsDTO>>(HttpStatus.NOT_FOUND);
					}
					String fdate;
					for(BlogsDTO b:blogs){
						fdate=Utils.formatDateBlogs(b.getCreatedTs());
						b.setfDate(fdate);
					}
					return new ResponseEntity<List<BlogsDTO>>(blogs, HttpStatus.OK);
				}
	//-------------------Search in my  Blogs--------------------------------------------------------
				
				@RequestMapping(value = "/searchinmyblogs/{memId}/{title}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
				public ResponseEntity<List<BlogsDTO>> searchInMyBlogs(@PathVariable("memId") int memId,@PathVariable("title") String title) {
					System.out.println("Fetching all blogs with X00001 page " + title);
					List<BlogsDTO> blogs = blogsService.searchInMyBlogs(memId,title);
					if (blogs == null) {
						System.out.println("blogs with page " + title + " not found");
						return new ResponseEntity<List<BlogsDTO>>(HttpStatus.NOT_FOUND);
					}
					String fdate;
					for(BlogsDTO b:blogs){
						fdate=Utils.formatDateBlogs(b.getCreatedTs());
						b.setfDate(fdate);
					}
					return new ResponseEntity<List<BlogsDTO>>(blogs, HttpStatus.OK);
				}
     //-------------------Update Blogs--------------------------------------------------------
				
				@RequestMapping(value = "update/{id}", method = RequestMethod.PUT)
				public ResponseEntity<Blogs> modify(@PathVariable("id") int id, @RequestBody Blogs blogs){
					System.out.println("updating event with X00001 id " + id);
					Blogs blog = blogsService.searchById(id);
					
					if (blog == null) {
						System.out.println("mem with id " + id + " not found");
						return new ResponseEntity<Blogs>(HttpStatus.NOT_FOUND);
					}
					
					blogsService.updateBlogs(blogs);
					Blogs b = blogsService.searchById(blogs.getBlogId());
					return new ResponseEntity<Blogs>(b , HttpStatus.OK);
				}
	//-------------------delete a Blogs--------------------------------------------------------
				
				@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
				public ResponseEntity<Blogs> deleteBlogs(@PathVariable("id") int id) {
					System.out.println("deleting  Blogs x0001 " + id);
					Blogs blog=blogsService.searchById(id);
					if (blog == null) {
						System.out.println("Unable to delete. event with id " + id + " not found");
						return new ResponseEntity<Blogs>(HttpStatus.NOT_FOUND);
					}
					blogsService.deleteBlog(blog);
					return new ResponseEntity<Blogs>(HttpStatus.NO_CONTENT);
				}
				
	//-------------------------popular blogs-------------------------------------------------------

				@RequestMapping(value = "/popularblogs/{memId}/{page}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
				public ResponseEntity<List<BlogsDTO>> getPopularBlogs(@PathVariable("memId") int memId,@PathVariable("page") int page) {
					System.out.println("Fetching all blogs with X00001 page " + page);
					int start = (page-1)*10;
					//int end=page*10;
					List<BlogsDTO> blogs = blogsService.searchPopularBlogs(memId,start);
					if (blogs == null) {
						System.out.println("blogs with page " + page + " not found");
						return new ResponseEntity<List<BlogsDTO>>(HttpStatus.NOT_FOUND);
					}
					String fdate;
					for(BlogsDTO b:blogs){
						fdate=Utils.formatDateBlogs(b.getCreatedTs());
						b.setfDate(fdate);
					}
					return new ResponseEntity<List<BlogsDTO>>(blogs, HttpStatus.OK);
				}
				
	//------------------------------blogs by cat------------------------------------------------------
				
				@RequestMapping(value = "/searchbycat/{catId}/{memId}/{page}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
				public ResponseEntity<List<BlogsDTO>> getBlogsByCat(@PathVariable("catId") int catId,@PathVariable("memId") int memId,@PathVariable("page") int page) {
					System.out.println("Fetching all blogs with X00001 page " + page);
					int start = (page-1)*10;
					//int end=page*10;
					List<BlogsDTO> blogs = blogsService.searchBlogsByCat(catId,memId,start);
					if (blogs == null) {
						System.out.println("blogs with page " + page + " not found");
						return new ResponseEntity<List<BlogsDTO>>(HttpStatus.NOT_FOUND);
					}
					String fdate;
					for(BlogsDTO b:blogs){
						fdate=Utils.formatDateBlogs(b.getCreatedTs());
						b.setfDate(fdate);
					}
					return new ResponseEntity<List<BlogsDTO>>(blogs, HttpStatus.OK);
				}
		//-------------------------popular bloggers-------------------------------------------------------

				@RequestMapping(value = "/popularbloggers/{memId}/{page}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
				public ResponseEntity<List<MemberDTO>> getPopularBloggerss(@PathVariable("memId") int memId,@PathVariable("page") int page) {
					int start = (page-1)*10;
					List<MemberDTO> mem = blogsService.searchPopularBloggers(memId,start);
					if (mem == null) {
						System.out.println("bloggers not found");
						return new ResponseEntity<List<MemberDTO>>(HttpStatus.NOT_FOUND);
					}
					return new ResponseEntity<List<MemberDTO>>(mem, HttpStatus.OK);
				}
		//-------------------Retrieve Blogger  Blogs--------------------------------------------------------
				
				@RequestMapping(value = "/searchbloggerblogs/{bloggerId}/{memId}/{page}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
				public ResponseEntity<List<BlogsDTO>> getBloggerBlogs(@PathVariable("bloggerId") int bloggerId,@PathVariable("memId") int memId,@PathVariable("page") int page) {
					System.out.println("Fetching all blogs with X00001 page " + page);
					int start = (page-1)*10;
					List<BlogsDTO> blogs = blogsService.searchBloggerBlogs(bloggerId,memId,start);
					if (blogs == null) {
						System.out.println("blogs with page " + page + " not found");
						return new ResponseEntity<List<BlogsDTO>>(HttpStatus.NOT_FOUND);
					}
					String fdate;
					for(BlogsDTO b:blogs){
						fdate=Utils.formatDateBlogs(b.getCreatedTs());
						b.setfDate(fdate);
					}
					return new ResponseEntity<List<BlogsDTO>>(blogs, HttpStatus.OK);
				}
				
}
