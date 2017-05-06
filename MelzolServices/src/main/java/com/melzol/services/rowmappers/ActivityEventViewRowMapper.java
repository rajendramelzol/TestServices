package com.melzol.services.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.melzol.services.beans.ActivitiesEventsDTO;
@JsonInclude(Include.NON_NULL)
public class ActivityEventViewRowMapper implements RowMapper<ActivitiesEventsDTO> {

	public ActivitiesEventsDTO mapRow(ResultSet rs, int arg1) throws SQLException {
		
		ActivitiesEventsDTO b=new ActivitiesEventsDTO();
		b.setId(rs.getInt("id"));
		b.setTitle(rs.getString("title"));
		b.setDescription(rs.getString("description"));
		b.setAddress(rs.getString("address"));
		b.setCreatedBy(rs.getInt("created_by"));
		b.setOwnerName(rs.getString("user_name"));
		b.setOwnerImage(rs.getString("profile_pic"));
		b.setGcmKey(rs.getString("gcm_key"));
		b.setMobile(rs.getString("mobile"));
		b.setType(rs.getInt("type"));
		b.setCategory(rs.getInt("category"));
		b.setLatitude(rs.getDouble("latitude"));
		b.setLongitude(rs.getDouble("longitude"));
		b.setBookCount(rs.getInt("totmemcnt"));
		b.setJoined(rs.getInt("joined"));
		b.setEmailId(rs.getString("email_id"));
		b.setWebsite(rs.getString("website"));

		return b;
	}

}
