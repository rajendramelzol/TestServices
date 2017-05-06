package com.melzol.services.beans;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class BlogViewRowMapper implements RowMapper<BlogsDTO> {

	public BlogsDTO mapRow(ResultSet rs, int arg1) throws SQLException {
		
		BlogsDTO b=new BlogsDTO();
		b.setBlogId(rs.getInt("blog_id"));
		b.setTitle(rs.getString("title"));
		b.setDescription(rs.getString("description"));
		b.setWebLink(rs.getString("web_link"));
		b.setCreatedBy(rs.getInt("created_by"));
		b.setCreatedTs(rs.getDate("created_ts"));
		b.setImageId(rs.getString("image_id"));
		b.setOwnerName(rs.getString("user_name"));
		b.setOwnerImage(rs.getString("profile_pic"));
		b.setGcmKey(rs.getString("gcm_key"));
		b.setLikeCount(rs.getInt("totlikecnt"));
		b.setOpinion(rs.getInt("useopinion"));
		b.setSubscribeStatus(rs.getInt("subscribestatus"));

		return b;
	}

}
