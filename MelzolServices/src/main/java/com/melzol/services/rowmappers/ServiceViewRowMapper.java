package com.melzol.services.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.melzol.services.beans.ServiceDTO;

public class ServiceViewRowMapper implements RowMapper<ServiceDTO> {

	public ServiceDTO mapRow(ResultSet rs, int arg1) throws SQLException {
		
		ServiceDTO s=new ServiceDTO();
		s.setServiceId(rs.getInt("service_id"));
		s.setName(rs.getString("name"));
		//s.setDescCompany(rs.getString("desc_company"));
		s.setCreatedBy(rs.getString("created_by"));
		s.setCreatedTs(rs.getDate("created_ts"));
		//s.setSubType(rs.getString("ref_type"));
		s.setImageId(rs.getString("image_id"));
		s.setOwnerName(rs.getString("user_name"));
		s.setLatitude(rs.getDouble("latitude"));
		s.setLongitude(rs.getDouble("longitude"));
		return s;
	}

}
