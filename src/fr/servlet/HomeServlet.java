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
		name="home-servlet",
		description = "Accueil login",
		urlPatterns={"/home"}
		
		)
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private IPostManager postManager = new PostManagerSQL();
       
    public HomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//request.getRequestDispatcher("/WEB-INF/html/home.jsp").forward(request, response);
		final String uri = request.getRequestURI();
		if(uri.contains("/home")) {
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
		request.setAttribute("postList", this.postManager.allPosts());
		request.getRequestDispatcher("/WEB-INF/html/home.jsp").forward(request, response);
	}

}
