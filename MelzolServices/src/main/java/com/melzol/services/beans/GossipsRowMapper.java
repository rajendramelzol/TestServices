package com.melzol.services.beans;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class GossipsRowMapper implements RowMapper<GossipsDTO> {

	public GossipsDTO mapRow(ResultSet rs, int arg1) throws SQLException {
		GossipsDTO g=new GossipsDTO();
		g.setGossipId(rs.getInt("gossip_id"));
		g.setTitle(rs.getString("title"));
		g.setDescription(rs.getString("description"));
		g.setCategory(rs.getInt("category"));
		g.setWebLink(rs.getString("web_link"));
		g.setImageId(rs.getString("image_id"));
		g.setOwnerName(rs.getString("user_name"));
		g.setCreatedBy(rs.getInt("created_by"));
		g.setGcmKey(rs.getString("gcm_key"));
		g.setLikeCount(rs.getInt("totlikecnt"));
		g.setUnlikeCount(rs.getInt("totunlikecnt"));
		g.setOpinion(rs.getInt("useopinion"));
		return g;
	}

}
