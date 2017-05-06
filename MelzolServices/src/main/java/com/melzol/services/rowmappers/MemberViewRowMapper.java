package com.melzol.services.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.melzol.services.beans.MemberDTO;

@JsonInclude(Include.NON_NULL)
public class MemberViewRowMapper implements RowMapper<MemberDTO> {

	public MemberDTO mapRow(ResultSet rs, int arg1) throws SQLException {
		MemberDTO m=new MemberDTO();
		m.setMemberId(rs.getInt("member_id"));
		m.setUserName(rs.getString("user_name"));
		m.setFullName(rs.getString("full_name"));
		m.setMobile(rs.getString("mobile"));
		m.setAge(rs.getInt("age"));
		m.setCompany(rs.getString("company"));
		m.setSex(rs.getInt("sex"));
		m.setProfession(rs.getString("profession"));
		m.setInterestHobbies(rs.getString("interest_hobbies"));
		m.setProfilePic(rs.getString("profile_pic"));
		m.setGcmKey(rs.getString("gcm_key"));
		m.setPlace(rs.getString("place"));
		m.setCity(rs.getString("city"));
		m.setState(rs.getString("state"));
		m.setCountry(rs.getString("country"));
		m.setPincode(rs.getString("pincode"));
		m.setLatitude(rs.getDouble("latitude"));
		m.setLongitude(rs.getDouble("longitude"));
		m.setTotTaggedCount(rs.getInt("TotTagged_Cnt"));
		m.setTaggedNeighCnt(rs.getInt("TaggedNeigh_Cnt"));
		m.setTagStatus(rs.getInt("tagstatus"));
		m.setRequestStatus(rs.getInt("requeststatus"));
		m.setTotNeighCnt(rs.getInt("TotNeighCnt"));
		m.setGroupsCount(rs.getInt("group_count"));
		m.setBlogCount(rs.getInt("blog_count"));
		m.setGossipsCount(rs.getInt("gossip_Count"));
		return m;
	}
}
