package com.melzol.services.service;

import com.melzol.services.model.GroupMembers;

public interface GroupMembersService {

	public void addGroupMembers(GroupMembers groupMembers);

	public	void deleteGroupDetails(int id);

	public void deleteGroupMembers(int groupid, int memid);

}
