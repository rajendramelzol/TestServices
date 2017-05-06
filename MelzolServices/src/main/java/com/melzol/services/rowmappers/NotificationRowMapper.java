package com.melzol.services.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.melzol.services.beans.NotificationDTO;

public class NotificationRowMapper implements RowMapper<NotificationDTO> {

	public NotificationDTO mapRow(ResultSet rs, int arg1) throws SQLException {
		
		NotificationDTO n=new NotificationDTO();
		n.setSenderId(rs.getInt("sender_id"));
		n.setReceiverId(rs.getInt("receiver_id"));
		n.setMessage(rs.getString("message"));
		n.setType(rs.getInt("type"));
		n.setRefType(rs.getInt("ref_type"));
		n.setRefId(rs.getInt("ref_id"));
		n.setStatus(rs.getInt("status"));
		n.setCreatedTs(rs.getDate("created_ts"));
		n.setProfilePic(rs.getString("profile_pic"));
		n.setUserName(rs.getString("user_name"));
		
		return n;
	}

}
