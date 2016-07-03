package fr.model;

import java.util.List;

public interface ICommentManager {
	public boolean insertComment(Integer id, String date, String comment, String user);
	public List<Comment> allCommentByImage(Integer id);
}