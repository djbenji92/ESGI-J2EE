package fr.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.model.IPostManager;
import fr.model.PostManagerSQL;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet(
		name="mes-images-servlet",
		description = "Accueil login",
		urlPatterns={"/mes-images"}
		
		)
public class mesImagesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private IPostManager postManager = new PostManagerSQL();
       
    public mesImagesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//request.getRequestDispatcher("/WEB-INF/html/home.jsp").forward(request, response);
		final String uri = request.getRequestURI();
		if(uri.contains("/mes-images")) {
			this.list(request, response);
		} 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void list(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String user = (String) request.getSession().getAttribute("userSession");
		request.setAttribute("postListImage", this.postManager.allPostsProfil(user));
		request.getRequestDispatcher("/WEB-INF/html/mes-images.jsp").forward(request, response);
	}

}
