package fr.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class FileUploaderServlet
 */
@WebServlet(
		name = "file-upload-servlet",
		description = "Servlet handling user post",
		urlPatterns={"/poster"}
)
public class FileUploaderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileUploaderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(RequestContext request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		   
		  if(!ServletFileUpload.isMultipartContent(request)){
		   out.println("Nothing to upload");
		   return; 
		  }
		  FileItemFactory itemfactory = new DiskFileItemFactory(); 
		  ServletFileUpload upload = new ServletFileUpload(itemfactory);
		  try{
		   List<FileItem>  items = upload.parseRequest(request);
		   for(FileItem item:items){
		     
		    String contentType = item.getContentType();
		    if(!contentType.equals("image/png")){
		     out.println("only png format image files supported");
		     continue;
		    }
		    File uploadDir = new File("F:\\UploadedFiles");
		    File file = File.createTempFile("img",".png",uploadDir);
		    item.write(file);
		 
		    out.println("File Saved Successfully");
		   }
		  }
		  catch(FileUploadException e){
		    
		   out.println("upload fail");
		  }
		  catch(Exception ex){
		    
		   out.println("can't save");
		  }
	}
	
	private void poster(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		request.getRequestDispatcher("/WEB-INF/html/poster.jsp").forward(request, response);
	}

}
