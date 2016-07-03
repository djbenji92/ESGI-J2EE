package fr.servlet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import fr.model.CommentManagerSQL;
import fr.model.ICommentManager;
import fr.model.IPostManager;
import fr.model.IUserManager;
import fr.model.Post;
import fr.model.PostManagerSQL;
import fr.model.UserManagerSQL;

/**
 * Servlet implementation class imageUploader
 */
@WebServlet(
		name = "imageUploader-servlet",
		description = "Servlet uploader image",
		urlPatterns={"/imageUploader", "/image", "/recherche"}
)
@MultipartConfig(fileSizeThreshold=1024*1024*10, // 10MB
maxFileSize=1024*1024*50,      // 50MB
maxRequestSize=1024*1024*100) 

public class imageUploader extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String SAVE_DIR = "uploadImage";
	private IPostManager postManager = new PostManagerSQL();
	private ICommentManager commentManager = new CommentManagerSQL();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public imageUploader() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Methode get image");
		final String uri = request.getRequestURI();
		if(uri.contains("/image")) {
			this.post(request, response);
		}
		if(uri.contains("/recherche")) {
			this.recherche(request, response);
		} 
		
	}
	
	private void getPageImage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.getRequestDispatcher("/WEB-INF/html/image.jsp").forward(request, response);
	}
	
	private void endUpload(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.getRequestDispatcher("/WEB-INF/html/uploadFini.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Methode post image");
		
		PrintWriter out = response.getWriter();
		
		final String uri = request.getRequestURI();
		if(uri.contains("/imageUploader")) {
			this.endUpload(request, response);
			
			//chemin d'enregistrement des fichiers
	        String chemin = "/Users/bro/javaJEE/projet-ESGI-J2EE/WebContent/imgUpload/";
	        String fileName = "";
	                 
	        for (Part part : request.getParts()) {
	         
	           fileName = extractFileName(part);
	           
	           String contentType = part.getContentType();
	           if(this.postManager.imageDontExist(fileName)){
		           if("image/png".equals(contentType) || "image/jpg".equals(contentType) || "image/jpeg".equals(contentType) || "image/gif".equals(contentType)){
			           if(fileName != ""){
			        	   part.write(chemin + File.separator + fileName);
			           }
			           System.out.println("Upload reussi : " + fileName);
			           //inserer BDD
			           final String titre = request.getParameter("titre");
			           final String hashtag = request.getParameter("hashtag");
			           String user = (String) request.getSession().getAttribute("userSession");
			           //enregistre BDD
			           System.out.println("titre : " + titre);
			           System.out.println("hashtag : " + hashtag);
			           System.out.println("filename : " + fileName);
			           System.out.println("user : " + user);
			           if(this.postManager.insertPost(titre, hashtag, fileName, user)){
			           	System.out.println("post sauvegarder");
			           	request.setAttribute("message", "Upload has been done successfully!");
			           }
			           else{
			           	System.out.println("erreur lors du post");
			           	request.setAttribute("errorMessage", "Erreur lors de l'enregistrement de l'image");
			           }
		           }
		           else{
		        	   System.out.println("Mauvais format");
		        	   request.setAttribute("errorMessage", "Veuillez envoyez une image sous le format git, jpg, ou gif");
		           }
		           System.out.println("Image existe pas");
	           }
	           else{
	        	   System.out.println("Image deja existante");
	        	   request.setAttribute("errorMessage", "Image déja existante, veuillez la renommer");
	           }
	   
	        }
		} 
		
        
        
        
	}
	
	private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
    }

	private void post(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Integer idPost = Integer.parseInt(request.getParameter("id"));
		System.out.println("id de la page : " + idPost);
		request.setAttribute("getPost2", this.postManager.getPost2(idPost));
		request.setAttribute("getComments", this.commentManager.allCommentByImage(idPost));
		
		//request.setAttribute("postList", this.postManager.allPosts());
		request.getRequestDispatcher("/WEB-INF/html/image.jsp").forward(request, response);
		
	}
	
	private void recherche(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String search = request.getParameter("search");
		System.out.println("recherche : " + search);
		request.setAttribute("postRecherche", this.postManager.doSearch(search));
		request.getRequestDispatcher("/WEB-INF/html/recherche.jsp").forward(request, response);
		
	}
	
}
