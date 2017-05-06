package com.melzol.services.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.melzol.services.dao.MemberNeighbourhoodsDAO;
import com.melzol.services.model.MemberNeighbourhoods;

@Repository
@Service("memberNeighbourhoodService")
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class MemberNeighbourhoodsServiceImpl implements MemberNeighbourhoodsService{

	@Autowired
	MemberNeighbourhoodsDAO memberNeighbourhoodsDAO;
	
	
	public void save(MemberNeighbourhoods memberNeighbourhoods) {
		memberNeighbourhoodsDAO.save(memberNeighbourhoods);
		
	}

	public List<MemberNeighbourhoods> searchMemberNeighbourhoods(int memId) {
		
		return memberNeighbourhoodsDAO.findMemberNeighbourhoods(memId);
	}
	
	

}
