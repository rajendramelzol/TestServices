package com.melzol.services.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.melzol.services.beans.GossipsDTO;
import com.melzol.services.model.Gossips;

public interface GossipsDAO {
	
	public abstract JdbcTemplate getJdbcTemplate();
	
	public abstract void setJdbcTemplate(JdbcTemplate jdbcTemplate);
	
	public abstract void init(SessionFactory sessionFactory);

	public abstract void save(Gossips gossips);

	public abstract Gossips findById(int id);

	public abstract List<GossipsDTO> findByCat(int memId,int cat, int start);

	public abstract void incrementCount(Integer gossipId);

	public abstract List<GossipsDTO> findMyGossips(int memId, int start);

	public abstract List<GossipsDTO> findByTitle(int memId,String title);

	public abstract List<GossipsDTO> findAll(int memId, int start);

	public abstract List<GossipsDTO> findInMyGossips(int memId, String title);

	public abstract void updateGossips(Gossips gossips);

	public abstract void deleteGossip(Gossips gossip);

	public abstract List<GossipsDTO> findPopularGossips(int memId, int start);

	public abstract GossipsDTO findGossipView(int id, int memId);

	public abstract List<GossipsDTO> findMemberCreatedGossips(int memId, int start);

}
