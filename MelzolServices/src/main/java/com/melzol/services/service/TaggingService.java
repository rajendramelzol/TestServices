package com.melzol.services.service;

import java.util.List;

import com.melzol.services.model.Tagging;

public interface TaggingService {

	public abstract void addTagging(Tagging t);
	
	public abstract void searchUpdateRequest(int senderId,int receiverId,int status);

}
