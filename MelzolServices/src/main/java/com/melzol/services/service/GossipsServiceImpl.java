package com.melzol.services.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.melzol.services.beans.CommentsDTO;
import com.melzol.services.beans.GossipsDTO;
import com.melzol.services.dao.CommentsDAO;
import com.melzol.services.dao.GossipsDAO;
import com.melzol.services.dao.WorkingDaysDAO;
import com.melzol.services.model.Gossips;
import com.melzol.services.util.Utils;

@Repository
@Service("gossipsService")
public class GossipsServiceImpl implements GossipsService{
	
	GossipsDAO gossipsDAO;
	WorkingDaysDAO workingDaysDAO;
	@Autowired
	CommentsDAO commentsDAO;
	

	public GossipsDAO getGossipsDAO() {
		return gossipsDAO;
	}
	@Autowired
	public void setGossipsDAO(GossipsDAO gossipsDAO) {
		this.gossipsDAO = gossipsDAO;
	}
	public WorkingDaysDAO getWorkingDaysDAO() {
		return workingDaysDAO;
	}
	@Autowired
	public void setWorkingDaysDAO(WorkingDaysDAO workingDaysDAO) {
		this.workingDaysDAO = workingDaysDAO;
	}
	
	public void addGossips(Gossips gossips) {
		gossipsDAO.save(gossips);
	}
	public Gossips searchById(int id) {
		return gossipsDAO.findById(id);
	}
	public List<GossipsDTO> searchByCat(int memId,int cat, int start) {
		return gossipsDAO.findByCat(memId,cat, start);
	}
	public void incrementCount(Integer gossipId) {
		gossipsDAO.incrementCount(gossipId);
	}
	public List<GossipsDTO> searchMyGossips(int memId, int start) {
		return gossipsDAO.findMyGossips(memId,start);
	}
	public List<GossipsDTO> searchByTitle(int memId,String title) {
		return gossipsDAO.findByTitle(memId,title);
	}
	public List<GossipsDTO> searchAll(int memId, int start) {
		return gossipsDAO.findAll(memId,start);
	}
	public List<GossipsDTO> searchInMyGossips(int memId, String title) {
		return gossipsDAO.findInMyGossips(memId,title);
	}
	public void updateGossips(Gossips gossips) {
		gossipsDAO.updateGossips(gossips);
		
	}
	public void deleteGossip(Gossips gossip) {
		gossipsDAO.deleteGossip(gossip);
	}
	public List<GossipsDTO> searchPopularGossips(int memId, int start) {
		return gossipsDAO.findPopularGossips(memId,start);
	}
	public GossipsDTO searchGossipView(int id, int memId) {
		GossipsDTO gossipsDTO= gossipsDAO.findGossipView(id, memId);
		List<CommentsDTO> comments= commentsDAO.findAll(id,3,0);
		for(CommentsDTO c:comments){
			c.setcDate(Utils.formatDateComments(c.getCreatedTs()));
		}
		gossipsDTO.setComments(comments);
		return gossipsDTO;
	}
	public List<GossipsDTO> searchMemberCreatedGossips(int memId, int start) {
		return  gossipsDAO.findMemberCreatedGossips(memId,start);
	}

}
