package com.melzol.services.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.melzol.services.beans.CommentsDTO;
@JsonInclude(Include.NON_NULL)
public class CommentsRowMapper implements RowMapper<CommentsDTO> {

	public CommentsDTO mapRow(ResultSet rs, int arg1) throws SQLException {
		
		CommentsDTO c=new CommentsDTO();
		c.setRefId(rs.getInt("ref_id"));
		c.setRefType(rs.getInt("ref_type"));
		c.setComment(rs.getString("comment"));
		c.setCommentedBy(rs.getInt("commented_by"));
		c.setCreatedTs(rs.getTimestamp("created_ts"));
		c.setUserName(rs.getString("user_name"));
		c.setProfilePic(rs.getString("profile_pic"));
		

		return c;
	}
}
