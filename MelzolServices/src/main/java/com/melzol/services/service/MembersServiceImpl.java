package com.melzol.services.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.melzol.services.beans.MemberDTO;
import com.melzol.services.beans.TagRequest;
import com.melzol.services.dao.MembersDAO;
import com.melzol.services.dao.WorkingDaysDAO;
import com.melzol.services.model.Members;
import com.melzol.services.model.WorkingDays;
import com.melzol.services.util.Utils;
@Repository
@Service("membersService")
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class MembersServiceImpl implements MembersService{
	
	private MembersDAO membersDAO;
	@Autowired
	private WorkingDaysDAO workingDaysDAO;
	
	
public MembersDAO getMembersDAO() {
		return membersDAO;
	}

@Autowired
	public void setMembersDAO(MembersDAO membersDAO) {
		this.membersDAO = membersDAO;
	}
	
	public void addMember(Members member){
		membersDAO.save(member);
	}
	public Members searchById(Integer memberId){
		return membersDAO.findById(memberId);
	}
	public List<Members> searchAllMembers(Double lat,Double lon,int memId,int page) {
		return membersDAO.findAllMembers(lat,lon,memId,page);
	}
	
	public Members searchByMobile(String mobile){
	return membersDAO.findByMobile(mobile);
	}
	
	public MemberDTO searchMemberView(int memid,int id) {
		MemberDTO mem=membersDAO.findMemberView(memid,id);
		if(mem.getTotNeighCnt()>1){
		float percentage= mem.getTaggedNeighCnt()*100/(mem.getTotNeighCnt()-1);
		mem.setPopularity(percentage);
		}else{
			mem.setPopularity(0);
		}
		List<WorkingDays> wrk=workingDaysDAO.findAll(id,7);
		mem.setWrk(wrk);
		return mem;
	}

	public void updateMember(Members member) {
		 membersDAO.updateMember(member);
		
	}

	public List<MemberDTO> searchInviteMembers(Double lat,Double lon,int memId,int start) {
		return membersDAO.findPopularMembers(lat,lon,memId,start);
	}

	public Members searchUserName(String userName) {
		return membersDAO.findUserName(userName);
	}
	public Members searchBylat(Double lat, Double lon) {
		
		return membersDAO.findBylat(lat,lon);
	}
	public void updateProfilePic(String profilePic,String memId) {
		membersDAO.updateProfilePic(profilePic,memId);
	}
	public TagRequest searchTaggedMembers(Double lat, Double lon,int memId) {
		Members member=membersDAO.findById(memId);
		int TotNeighCnt=membersDAO.findNeighbourCount(memId);
		int TagCnt=membersDAO.findTaggedCount(lat,lon,memId);
		List<MemberDTO> popmem = membersDAO.findPopularMembers(lat,lon,memId,0);
		List<Members> sentTagRequest=membersDAO.findTagRequest(lat,lon,memId);
		List<Members> m= membersDAO.findTaggedMembers(memId);
		
		TagRequest mem=new TagRequest();
		mem.setMemberId(member.getMemberId());
		mem.setUserName(member.getUserName());
		mem.setProfilePic(member.getProfilePic());
		mem.setLatitude(member.getLatitude());
		mem.setLongitude(member.getLongitude());
		if(TotNeighCnt>1){
			float percentage= TagCnt*100/(TotNeighCnt-1);
			mem.setPopularity(percentage);
			}else{
				mem.setPopularity(0);
			}
		if(TotNeighCnt>1){
			float rating = TagCnt*5/(TotNeighCnt-1);
			mem.setRating(rating);
			}else{
				mem.setRating(0);
			}
		mem.setPopularMembers(popmem);
		mem.setSentTagRequest(sentTagRequest);
		mem.setTaggedMembers(m);
		
		return mem;
		
	}

	public List<Members> searchGroupMembers(int id, int refType, int start) {
		
		return membersDAO.findGroupMembers(id,refType,start);
	}

	public List<Members> searchMemberOpinions(int id, int refType,int opinion, int start) {
		
		return membersDAO.findMemberOpinions(id,refType,opinion,start);
	}

	public List<Members> searchMemberTaggedContacts(Double lat, Double lon, int memId) {
		
		return membersDAO.findTaggedMembers(memId);
	}


	

}
