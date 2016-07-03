package fr.model;

import java.util.List;

public interface IFavorisManager {
	public boolean insertFavoris(Integer id, String user);
	public boolean deleteFavoris(Integer id, String user);
	public boolean verifImageFavoris(Integer id);
	public List<Post> allPostsFavoris(String login);
}