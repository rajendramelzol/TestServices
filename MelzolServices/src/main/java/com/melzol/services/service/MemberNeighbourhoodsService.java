package com.melzol.services.service;

import java.util.List;

import com.melzol.services.model.MemberNeighbourhoods;

public interface MemberNeighbourhoodsService {

	public abstract void save(MemberNeighbourhoods memberNeighbourhoods);

	public abstract List<MemberNeighbourhoods> searchMemberNeighbourhoods(int memId);

}
