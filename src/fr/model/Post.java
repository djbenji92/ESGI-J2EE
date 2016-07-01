package fr.model;

public class Post {
	private Integer id;
	private String titre;
	private String image;
	private String hashtag;
	private String login;
	
	public Post(Integer id, String titre, String image, String hashtag, String idUser) {
		this.id = id;
		this.titre = titre;
		this.image = image;
		this.hashtag = hashtag;
		this.login = idUser;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	public String getHashtag() {
		return hashtag;
	}

	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	
}
