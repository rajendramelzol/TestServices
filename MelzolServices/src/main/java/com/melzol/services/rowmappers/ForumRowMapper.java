package com.melzol.services.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.melzol.services.beans.ForumDTO;
@JsonInclude(Include.NON_NULL)
public class ForumRowMapper implements RowMapper<ForumDTO> {

	public ForumDTO mapRow(ResultSet rs, int arg1) throws SQLException {
	
		ForumDTO f=new ForumDTO();
		f.setForumId(rs.getInt("forum_id"));
		f.setTitle(rs.getString("title"));
		f.setDescription(rs.getString("description"));
		f.setOwnerName(rs.getString("user_name"));
		f.setGcmKey(rs.getString("gcm_key"));
		f.setOwnerImage(rs.getString("profile_pic"));
		f.setImageId(rs.getString("image_id"));
		f.setCreatedBy(rs.getInt("created_by"));
		f.setCreatedTs(rs.getDate("created_ts"));
		f.setLikeCount(rs.getInt("totlikecnt"));
		f.setOpinion(rs.getInt("useopinion"));
		
		return f;
	}

}
