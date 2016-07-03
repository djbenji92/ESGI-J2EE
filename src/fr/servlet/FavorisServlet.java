package fr.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

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
import fr.model.FavorisManagerSQL;
import fr.model.IFavorisManager;
import fr.model.UserManagerSQL;

/*import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;*/
/**
 * Servlet implementation class PostServlet
 */
@WebServlet(
		name = "favoris-servlet",
		description = "Servlet handling user post",
		urlPatterns={"/service-favoris", "/mes-favoris"}
)
public class FavorisServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private IFavorisManager commentManager = new FavorisManagerSQL();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FavorisServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		final String uri = request.getRequestURI();
		
		
		if(uri.contains("/service-favoris")) {
			final Integer id = Integer.parseInt(request.getParameter("id"));
	        final String meth= request.getParameter("meth");
	        String user = (String) request.getSession().getAttribute("userSession");
	        
	        
	        if(meth.equals("add")){
	        	if(this.commentManager.insertFavoris(id, user)){
	                System.out.println("Favoris sauvegarder");
	                request.setAttribute("message", "Le favoris a été ajouté");
	            }
	            else{
	              System.out.println("erreur lors de l'ajout du favoris");
	              request.setAttribute("errorMessage", "Erreur lors de l'enregistrement du favoris");
	            }
	        }
	        else if(meth.equals("del")){
	        	if(this.commentManager.deleteFavoris(id, user)){
	                System.out.println("Favoris supprimé");
	                request.setAttribute("message", "Le favoris a été supprimé");
	            }
	            else{
	              System.out.println("erreur lors de la suppression du favoris");
	              request.setAttribute("errorMessage", "Erreur lors de la suppresion du favoris");
	            }
	        }
	        
	        
	        response.sendRedirect("image?id=" +  id);
			return;
		}
		
		if(uri.contains("/mes-favoris")){
			this.afficherMesFavoris(request, response);
		}
        
   
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
	}
	
	private void afficherMesFavoris(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String user = (String) request.getSession().getAttribute("userSession");
		request.setAttribute("postListImage", this.commentManager.allPostsFavoris(user));
		request.getRequestDispatcher("/WEB-INF/html/mesFavoris.jsp").forward(request, response);
	}

}
