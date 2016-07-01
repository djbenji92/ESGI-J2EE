package fr.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import fr.model.User;
import fr.model.Post;
import fr.model.UserManagerSQL;

/*import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;*/
/**
 * Servlet implementation class PostServlet
 */
@WebServlet(
		name = "post-servlet",
		description = "Servlet handling user post",
		urlPatterns={"/publier", "/publierImage"}
)
public class PostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static final String CHAMP_DESCRIPTION = "titre";
	public static final String CHAMP_FICHIER     = "fichier";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		final String uri = request.getRequestURI();
		if(uri.contains("/publierImage")) {
			this.publierImage(request, response);
		} 
		else if(uri.contains("/publier")) {
			this.publier(request, response);
		} 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void publier(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.getRequestDispatcher("/WEB-INF/html/poster.jsp").forward(request, response);
	}
	private void publierImage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {	
		request.getRequestDispatcher("/WEB-INF/html/publierImage.jsp").forward(request, response);
	}
	
	private static String getNomFichier( Part part ) {
	    /* Boucle sur chacun des paramètres de l'en-tête "content-disposition". */
	    for ( String contentDisposition : part.getHeader( "content-disposition" ).split( ";" ) ) {
	        /* Recherche de l'éventuelle présence du paramètre "filename". */
	        if ( contentDisposition.trim().startsWith( "filename" ) ) {
	            /*
	             * Si "filename" est présent, alors renvoi de sa valeur,
	             * c'est-à-dire du nom de fichier sans guillemets.
	             */
	            return contentDisposition.substring( contentDisposition.indexOf( '=' ) + 1 ).trim().replace( "\"", "" );
	        }
	    }
	    /* Et pour terminer, si rien n'a été trouvé... */
	    return null;
	}

}
