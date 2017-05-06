package com.melzol.services.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.melzol.services.beans.CommentsDTO;
import com.melzol.services.beans.GroupDTO;
import com.melzol.services.beans.TopicsDTO;
import com.melzol.services.dao.GroupDAO;
import com.melzol.services.dao.MembersDAO;
import com.melzol.services.dao.TopicsDAO;
import com.melzol.services.model.Group;
import com.melzol.services.model.Members;
import com.melzol.services.util.Utils;

@Repository
@Service("groupService")
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class GroupServiceImpl implements GroupService {
	private GroupDAO groupDAO;
	private MembersDAO membersDAO;
	@Autowired
	private TopicsDAO topicsDAO;
	
	public GroupDAO getGroupDAO() {
		return groupDAO;
	}
	@Autowired
	public void setGroupDAO(GroupDAO groupDAO) {
		this.groupDAO = groupDAO;
	}

	public MembersDAO getMembersDAO() {
		return membersDAO;
	}
	@Autowired
	public void setMembersDAO(MembersDAO membersDAO) {
		this.membersDAO = membersDAO;
	}
	public void addGroup(Group group) {
		groupDAO.save(group);
		
	}

	public Group searchById(Integer groupId) {
		return groupDAO.findById(groupId);
	}

	public GroupDTO searchGroupView(int id, int memid) {
	
		GroupDTO groupDTO= groupDAO.findGroupView(id,memid);
		return groupDTO;
	}
	public void modifyGroup(Group group) {
		groupDAO.update(group);
		
	}
	
	public void deleteGroup(Group group) {
		groupDAO.delete(group);
	
		
	}
	
	public List<GroupDTO> searchGroupsByCat(String city, int cat, int start) {
		return groupDAO.findGroupsByCat(city,cat,start);
	}
	public List<GroupDTO> searchMyGroups(int memId, int start) {
		return groupDAO.findMyGroups(memId,start);
	}

	public List<GroupDTO> searchMemberCreatedGroups(int memId, int start) {
		return groupDAO.findMemberCreatedGroups(memId,start);
	}

}
