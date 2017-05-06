package com.melzol.services.service;

import java.util.List;

import com.melzol.services.beans.GossipsDTO;
import com.melzol.services.model.Gossips;

public interface GossipsService {

	public abstract void addGossips(Gossips gossips);

	public abstract Gossips searchById(int id);

	public abstract List<GossipsDTO> searchByCat(int memId,int cat, int start);

	public abstract void incrementCount(Integer gossipId);

	public abstract List<GossipsDTO> searchMyGossips(int memId, int start);

	public abstract List<GossipsDTO> searchByTitle(int memId,String title);

	public abstract List<GossipsDTO> searchAll(int memId, int start);

	public abstract List<GossipsDTO> searchInMyGossips(int memId, String title);

	public abstract void updateGossips(Gossips gossips);

	public abstract void deleteGossip(Gossips gossip);

	public abstract List<GossipsDTO> searchPopularGossips(int memId, int start);

	public abstract GossipsDTO searchGossipView(int id, int memId);

	public abstract List<GossipsDTO> searchMemberCreatedGossips(int memId, int start);

}
