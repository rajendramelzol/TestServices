package com.melzol.services.beans;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.melzol.services.model.Members;
@JsonInclude(Include.NON_NULL)
public class MemberRowMapper implements RowMapper<Members> {

	public Members mapRow(ResultSet rs, int arg1) throws SQLException {
		Members m=new Members();
		m.setMemberId(rs.getInt("member_id"));
		m.setUserName(rs.getString("user_name"));
		m.setGcmKey(rs.getString("gcm_key"));
		m.setProfilePic(rs.getString("profile_pic"));
		m.setLatitude(rs.getDouble("latitude"));
		m.setLongitude(rs.getDouble("longitude"));
		return m;
	}
}
