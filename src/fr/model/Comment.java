package fr.model;

import java.sql.Date;

public class Comment {
	private Integer id;
	private String text;
	private String date;
	private String idUser;
	private Integer idPost;
	
	public Comment(Integer id, String text, String date, String idUser, Integer idPost) {
		this.id = id;
		this.text = text;
		this.date = date;
		this.idUser = idUser;
		this.idPost = idPost;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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
