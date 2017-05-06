package com.melzol.services.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.melzol.services.beans.MemberDTO;

@JsonInclude(Include.NON_NULL)
public class BlogSubscriberRowMapper implements RowMapper<MemberDTO> {

	public MemberDTO mapRow(ResultSet rs, int arg1) throws SQLException {
		MemberDTO m=new MemberDTO();
		m.setMemberId(rs.getInt("member_id"));
		m.setUserName(rs.getString("user_name"));
		m.setGcmKey(rs.getString("gcm_key"));
		m.setProfilePic(rs.getString("profile_pic"));
		m.setLatitude(rs.getDouble("latitude"));
		m.setLongitude(rs.getDouble("longitude"));
		m.setBlogCount(rs.getInt("blogcount"));
		return m;
	}
}
