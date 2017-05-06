package com.melzol.services.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.melzol.services.beans.ActivitiesEventsDTO;
@JsonInclude(Include.NON_NULL)
public class NeighbourhoodActivitiesRowMapper implements RowMapper<ActivitiesEventsDTO> {

	public ActivitiesEventsDTO mapRow(ResultSet rs, int arg1) throws SQLException {
		
		ActivitiesEventsDTO a=new ActivitiesEventsDTO();
		a.setId(rs.getInt("id"));
		a.setTitle(rs.getString("title"));
		a.setDescription(rs.getString("description"));
		a.setCreatedBy(rs.getInt("created_by"));
		a.setCreatedTs(rs.getDate("created_ts"));
		a.setCategory(rs.getInt("category"));
		a.setImageId(rs.getString("image_id"));
		a.setSubType(rs.getInt("ref_type"));
		a.setStartDate(rs.getTimestamp("start_date"));
		a.setGcmKey(rs.getString("gcm_key"));
		a.setBookCount(rs.getInt("totmemcnt"));
		return a;
	}
}
