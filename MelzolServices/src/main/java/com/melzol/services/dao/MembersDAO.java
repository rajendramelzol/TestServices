package com.melzol.services.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.melzol.services.beans.MemberDTO;
import com.melzol.services.model.Members;

public interface MembersDAO {

	public abstract JdbcTemplate getJdbcTemplate();

	public abstract void setJdbcTemplate(JdbcTemplate jdbcTemplate);

	public abstract void init(SessionFactory sessionFactory);

	public abstract List<Members> findAllMembers(Double lat,Double lon, int memId, int start);

	public abstract Members findByMobile(String mobile);

	public abstract void save(Members member);

	public abstract Members findById(Integer memberId);

	public abstract void updateMember(Members member);

	public abstract List<Members> findInviteMembers(Double lat,Double lon,int memId);

	public abstract Members findUserName(String userName);

	public abstract Members findBylat(Double lat, Double lon);

	public abstract void updateProfilePic(String profilePic,String memId);

	public abstract List<Members> findTaggedMembers(int memId);

	public abstract int findNeighbourCount(int memId);

	public abstract int findTaggedCount(Double lat, Double lon, int memId);

	public abstract MemberDTO findMemberView(int memid, int id);

	public abstract List<Members> findTagRequest(Double lat,Double lon,int memId);

	public abstract List<Members> findGroupMembers(int id,int refType,int start);

	public abstract List<MemberDTO> findPopularMembers(Double lat, Double lon, int memId, int start);

	public abstract List<Members> findMemberOpinions(int id, int refType,int opinion, int start);

}
