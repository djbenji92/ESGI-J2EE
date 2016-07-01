package fr.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.model.IUserManager;
import fr.model.User;
import fr.model.UserManagerSQL;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet(
		name = "user-servlet",
		description = "Servlet handling user login",
		urlPatterns={"/login", "/create", "/list", "/logout"}
)
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String USER_SESSION = "userSession";
	
	private IUserManager userManager = new UserManagerSQL();

    /**
     * Default constructor. 
     */
    public UserServlet() {
        // TODO Auto-generated constructor stub
    	super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		final String uri = request.getRequestURI();
		if(uri.contains("/login")) {
			this.login(request, response);
		} 
		else if(uri.contains("/create")) {
			this.create(request, response);
		}
		else if(uri.contains("/list")) {
			this.list(request, response);
		} 
		else if(uri.contains("/logout")) {
			this.logout(request, response);
		} 
		else{
			this.home(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		final String loginConnexion = request.getParameter("user");
		final String passwordConnexion = request.getParameter("password");
		
		if (loginConnexion == null || passwordConnexion == null) {
			//just display login
		} else if(this.userManager.checkLogin(loginConnexion)) {
			if (this.userManager.checkLoginWithPassword(loginConnexion, passwordConnexion)) {
				//request.setAttribute("success", true);
				request.getSession().setAttribute(this.USER_SESSION, loginConnexion);
				response.sendRedirect("home");
				return;
			} else {
				request.setAttribute("errorMessage", "Bad password");
			}
		} else {
			request.setAttribute("errorMessage", "Erreur de connexion");
		}
		request.setAttribute("action", "login");
		request.getRequestDispatcher("/WEB-INF/html/login.jsp").forward(request, response);
	}
	
	private void create(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		final String loginInscription = request.getParameter("userInscription");
		final String passwordInscription = request.getParameter("passwordInscription");
		
		if (loginInscription != null && passwordInscription != null) {
			if (this.userManager.checkLogin(loginInscription)) {
				request.setAttribute("errorMessage", "User already exists. Please chose another");
			} else {
				if(this.userManager.createUser(loginInscription, passwordInscription)){
					request.getSession().setAttribute(this.USER_SESSION, loginInscription);
					response.sendRedirect("home");
					return;
				}
				else{
					request.setAttribute("errorMessage", "erreur creation utilisateur");
				}
					
			}	
		}
		
		request.setAttribute("action", "create");
		request.getRequestDispatcher("/WEB-INF/html/create.jsp").forward(request, response);
	}
	
	
	private void list(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String pageType = request.getParameter("output");
		
		if (pageType == null || !pageType.equals("json")) {
			pageType = "html";
		} 
		request.setAttribute("userList", this.userManager.allUsers());
		request.getRequestDispatcher("/WEB-INF/html/list.jsp").forward(request, response);
	}
	
	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.getSession().removeAttribute(UserServlet.USER_SESSION);
		response.sendRedirect("login");
	}
	
	private void home(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		User user = (User) request.getSession().getAttribute(UserServlet.USER_SESSION);
		if (user == null) {
			response.sendRedirect("login");
			return;
		}
		request.setAttribute("login", user.getLogin());
		request.getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
	}

}
