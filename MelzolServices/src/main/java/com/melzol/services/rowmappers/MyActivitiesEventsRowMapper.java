package com.melzol.services.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.melzol.services.beans.ActivitiesEventsDTO;

public class MyActivitiesEventsRowMapper implements RowMapper<ActivitiesEventsDTO> {

	public ActivitiesEventsDTO mapRow(ResultSet rs, int arg1) throws SQLException {
		
		ActivitiesEventsDTO a=new ActivitiesEventsDTO();
		a.setId(rs.getInt("id"));
		a.setTitle(rs.getString("title"));
		a.setCreatedBy(rs.getInt("created_by"));
		a.setCreatedTs(rs.getDate("created_ts"));
		a.setImageId(rs.getString("image_id"));
		a.setSubType(rs.getInt("sub_type"));
		a.setOwnerName(rs.getString("user_name"));
		return a;
	}
}