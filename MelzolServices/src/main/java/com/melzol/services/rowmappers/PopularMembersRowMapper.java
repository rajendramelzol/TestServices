package com.melzol.services.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.melzol.services.beans.MemberDTO;

public class PopularMembersRowMapper implements RowMapper<MemberDTO> {

	public MemberDTO mapRow(ResultSet rs, int arg1) throws SQLException {
		MemberDTO m=new MemberDTO();
		m.setMemberId(rs.getInt("member_id"));
		m.setUserName(rs.getString("user_name"));
		m.setGcmKey(rs.getString("gcm_key"));
		m.setProfilePic(rs.getString("profile_pic"));
		m.setLatitude(rs.getDouble("latitude"));
		m.setLongitude(rs.getDouble("longitude"));
		m.setPopularity(rs.getFloat("popularity"));
		m.setTagStatus(rs.getInt("tagstatus"));
		return m;
	}
}
