package com.melzol.services.service;

import java.util.List;

import com.melzol.services.beans.GroupDTO;
import com.melzol.services.model.Group;

public interface GroupService {

	public abstract void addGroup(Group group);

	public abstract Group searchById(Integer groupId);

	public abstract GroupDTO searchGroupView(int id, int memid);

	public abstract void modifyGroup(Group group);

	public abstract void deleteGroup(Group group);

	public abstract List<GroupDTO> searchGroupsByCat(String city, int cat, int start);

	public abstract List<GroupDTO> searchMyGroups(int memId, int start);

	public abstract List<GroupDTO> searchMemberCreatedGroups(int memId, int start);

}
