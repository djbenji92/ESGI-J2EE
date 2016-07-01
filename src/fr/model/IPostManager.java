package fr.model;

import java.util.List;

public interface IPostManager {
	public boolean insertPost(String titre, String hashtag, String image, String user);
	public boolean imageDontExist(String image);
	public List<Post> allPosts();
	Post getPost(Integer id);
	public List<Post> getPost2(Integer id);
	public List<Post> allPostsProfil(String login);
	List<Post> doSearch(String search);
}