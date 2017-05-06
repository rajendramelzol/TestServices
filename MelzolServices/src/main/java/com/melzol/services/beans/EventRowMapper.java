package com.melzol.services.beans;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.melzol.services.model.Event;
@JsonInclude(Include.NON_NULL)
public class EventRowMapper implements RowMapper<Event> {

	public Event mapRow(ResultSet rs, int arg1) throws SQLException {
		Event e=new Event();
		e.setId(rs.getInt("id"));
		e.setEventName(rs.getString("event_name"));
		e.setMemberCount(rs.getInt("member_count"));
		e.setStatus(rs.getInt("status"));
		return e;
	}

}
