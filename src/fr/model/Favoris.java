package fr.model;


public class Favoris {
	private Integer id;
	private String idUser;
	private Integer idPost;
	
	public Favoris(Integer id, String idUser, Integer idPost) {
		this.id = id;
		this.idUser = idUser;
		this.idPost = idPost;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}
	
	public Integer getIdPost() {
		return idPost;
	}

	public void setIdPost(Integer idPost) {
		this.idPost = idPost;
	}

	
}
