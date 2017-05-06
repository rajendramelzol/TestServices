package com.melzol.services.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.melzol.services.dao.GroupMembersDAO;
import com.melzol.services.model.GroupMembers;

@Repository
@Service("groupMemberService")
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class GroupMembersServiceImpl implements GroupMembersService  {
	
	private GroupMembersDAO groupMembersDAO;

	public GroupMembersDAO getGroupMembersDAO() {
		return groupMembersDAO;
	}
	@Autowired
	public void setGroupMembersDAO(GroupMembersDAO groupMembersDAO) {
		this.groupMembersDAO = groupMembersDAO;
	}
	public void addGroupMembers(GroupMembers groupMembers) {
		groupMembersDAO.save(groupMembers);
		
	}
	public void deleteGroupDetails(int id) {
		groupMembersDAO.deleteGroupDetails(id);
		
	}
	public void deleteGroupMembers(int groupid, int memid) {
		groupMembersDAO.deleteGroupMembers(groupid,memid);
	}
	
	

}
