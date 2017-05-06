package com.melzol.services.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.melzol.services.beans.ActivitiesEventsDTO;
@JsonInclude(Include.NON_NULL)
public class CityActivitiesRowMapper implements RowMapper<ActivitiesEventsDTO> {

	public ActivitiesEventsDTO mapRow(ResultSet rs, int arg1) throws SQLException {
		
		ActivitiesEventsDTO a=new ActivitiesEventsDTO();
		a.setId(rs.getInt("id"));
		a.setTitle(rs.getString("title"));
		a.setDescription(rs.getString("description"));
		a.setCategory(rs.getInt("category"));
		a.setAddress(rs.getString("address"));
		a.setImageId(rs.getString("image_id"));
		a.setBookCount(rs.getInt("totmemcnt"));
		a.setStartDate(rs.getTimestamp("start_date"));
		return a;
	}

}
