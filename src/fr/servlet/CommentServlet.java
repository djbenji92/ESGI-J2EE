package fr.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import fr.model.User;
import fr.model.IPostManager;
import fr.model.Post;
import fr.model.PostManagerSQL;
import fr.model.CommentManagerSQL;
import fr.model.ICommentManager;
import fr.model.UserManagerSQL;

/*import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;*/
/**
 * Servlet implementation class PostServlet
 */
@WebServlet(
		name = "comment-servlet",
		description = "Servlet handling user post",
		urlPatterns={"/publierCommentaire"}
)
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ICommentManager commentManager = new CommentManagerSQL();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		final String uri = request.getRequestURI();
		
		Date aujourdhui = new Date();
		System.out.println(mediumDateFormat.format(aujourdhui));
		
		System.out.println("methode get");
		final String comment = request.getParameter("comment");
        final Integer id = Integer.parseInt(request.getParameter("id"));
        String user = (String) request.getSession().getAttribute("userSession");
        if(this.commentManager.insertComment(id, comment, user, mediumDateFormat.format(aujourdhui))){
          System.out.println("commentaire sauvegarder");
          request.setAttribute("message", "Le commentaire a été posté");
        }
        else{
          System.out.println("erreur lors du commentaire");
          request.setAttribute("errorMessage", "Erreur lors de l'enregistrement du commentaire");
        }
        
        response.sendRedirect("image?id=" +  id);
		return;
   
		
	}
	
	DateFormat mediumDateFormat = DateFormat.getDateTimeInstance(
			DateFormat.MEDIUM,
			DateFormat.MEDIUM);

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		
		System.out.println("Méthode post");
		final String uri = request.getRequestURI();
		if(uri.contains("/publierCommentaire")) {
			System.out.println("publier commentaire");
			this.publierComment(request, response);
		} 
		
		final String comment = request.getParameter("comment");
        final Integer id = Integer.parseInt(request.getParameter("id"));
        String user = (String) request.getSession().getAttribute("userSession");
        
        System.out.println("id : " + id);
        
        /*if(this.commentManager.insertComment(id, comment, user)){
           	System.out.println("commentaire sauvegarder");
           	request.setAttribute("message", "Le commentaire a été posté");
           }
           else{
           	System.out.println("erreur lors du commentaire");
           	request.setAttribute("errorMessage", "Erreur lors de l'enregistrement du commentaire");
           }*/
		
		//response.sendRedirect("image?id=");
		//return;
	}
	
	private void publierComment(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.getRequestDispatcher("/WEB-INF/html/uploadFini.jsp").forward(request, response);
	}

}
