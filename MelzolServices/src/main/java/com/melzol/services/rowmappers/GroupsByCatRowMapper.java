package com.melzol.services.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.melzol.services.beans.GroupDTO;

public class GroupsByCatRowMapper implements RowMapper<GroupDTO> {

	public GroupDTO mapRow(ResultSet rs, int arg1) throws SQLException {
		
		GroupDTO g=new GroupDTO();
		g.setGroupId(rs.getInt("group_id"));
		g.setTitle(rs.getString("title"));
		g.setCategory(rs.getInt("category"));
		g.setImageId(rs.getString("image_id"));
		g.setCreatedBy(rs.getInt("created_by"));
		g.setMemCount(rs.getInt("totmemcnt"));
	
		return g;
	}
}
