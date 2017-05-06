package com.melzol.services.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.melzol.services.beans.TopicsDTO;
@JsonInclude(Include.NON_NULL)
public class TopicsRowMapper implements RowMapper<TopicsDTO> {

	public TopicsDTO mapRow(ResultSet rs, int arg1) throws SQLException {
		
		TopicsDTO t=new TopicsDTO();
		t.setTopicId(rs.getInt("topic_id"));
		t.setTitle(rs.getString("title"));
		t.setDescription(rs.getString("description"));
		t.setRefId(rs.getInt("ref_id"));
		t.setRefType(rs.getInt("ref_type"));
		t.setCreatedBy(rs.getInt("created_by"));
		t.setCreatedTs(rs.getTimestamp("created_ts"));
		t.setUserName(rs.getString("user_name"));
		t.setProfilePic(rs.getString("profile_pic"));
		

		return t;
	}

}
