package com.melzol.services.service;

import java.util.Date;
import java.util.List;

import com.melzol.services.beans.CommentsDTO;
import com.melzol.services.model.Comments;

public interface CommentsService {

	public abstract void addEvent(Comments comments);

	public abstract Comments searchById(Date id);

	public abstract List<CommentsDTO> searchAll(int refid,int reftype, int start);

	public abstract void deleteComments(Comments comments);

}
