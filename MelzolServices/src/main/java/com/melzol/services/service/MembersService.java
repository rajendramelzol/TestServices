package com.melzol.services.service;

import java.util.List;

import com.melzol.services.beans.MemberDTO;
import com.melzol.services.beans.TagRequest;
import com.melzol.services.model.Members;

public interface MembersService {

	public abstract List<Members> searchAllMembers(Double lat,Double lon, int memId, int start);

	public abstract Members searchByMobile(String mobile);

	public abstract void addMember(Members member);

	public abstract Members searchById(Integer memberId);

	public abstract MemberDTO searchMemberView(int memid, int id);

	public abstract void updateMember(Members member);

	public abstract List<MemberDTO> searchInviteMembers(Double lat,Double lon,int memId, int start);

	public abstract Members searchUserName(String userName);

	public abstract Members searchBylat(Double lat, Double lon);

	public abstract void updateProfilePic( String profilePic,String memId);

	public abstract TagRequest searchTaggedMembers(Double lat, Double lon, int memId);

	public abstract List<Members> searchGroupMembers(int id, int refType, int start);

	public abstract List<Members> searchMemberOpinions(int id, int refType, int opinion, int start);

	public abstract List<Members> searchMemberTaggedContacts(Double lat, Double lon, int memId);

}
