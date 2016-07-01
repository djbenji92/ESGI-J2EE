package fr.servlet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import fr.model.IPostManager;
import fr.model.PostManagerSQL;

/**
 * Servlet implementation class FileUploader
 */
@WebServlet(
		name = "uploader-servlet",
		description = "Servlet uploader",
		urlPatterns={"/uploader"}
)
@MultipartConfig(fileSizeThreshold=1024*1024*10, // 10MB
maxFileSize=1024*1024*50,      // 50MB
maxRequestSize=1024*1024*100) 
public class FileUploader extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private File file;
	private static final String SAVE_DIR = "uploadImage";
	
	private IPostManager postManager = new PostManagerSQL();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileUploader() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		final String uri = request.getRequestURI();
		System.out.println(uri);
		if(uri.contains("/publierImage")) {
			this.endUpload(request, response);
		} 
		if(uri.contains("/image")) {
			this.getPageImage(request, response);
		} 
		
	}
	
	private void endUpload(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.getRequestDispatcher("/WEB-INF/html/poster.jsp").forward(request, response);
	}
	private void getPageImage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Integer idPost = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("getPost2", this.postManager.getPost(idPost));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
			System.out.println("etape 1");
			//
			String source = getServletContext().getRealPath("/");
			System.out.println(source);
			//
	
			/*if(!ServletFileUpload.isMultipartContent(request)){
				   out.println("Nothing to upload");
				   return; 
				  }
				  FileItemFactory itemfactory = new DiskFileItemFactory(); 
				  ServletFileUpload upload = new ServletFileUpload(itemfactory);
				  try{
				   List<fileitem>  items = upload.parseRequest(request);
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
				  }*/
				///
			  /*try {
				  List items = upload.parseRequest(request);
			      Iterator iterator = items.iterator();
			      while (iterator.hasNext()) {
			    	  FileItem item = (FileItem) iterator.next();
			          if (!item.isFormField()) {
			        	  String fileName = item.getName();
			              String root = getServletContext().getRealPath("/");
			              File path = new File(root + "/WebContent");
			              if (!path.exists()) {
			            	  boolean status = path.mkdirs();
			              }
	
			              File uploadedFile = new File(path + "/" + fileName);
			              item.write(uploadedFile);
			          }
			      }
			    } 
			    catch (Exception e) {
			    	out.println("error save");
			    }*/
			
			//V3
			/*boolean isMultipart;
			String filePath;
			int maxFileSize = 5000*1024;
			int maxMemSize = 5 * 1024;
			filePath = getServletContext().getInitParameter("file_upload");
			response.setContentType("text/html");
			
			
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(maxFileSize);
			factory.setRepository(new File(source));
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setSizeMax(maxFileSize);
			try{
				//List fileItems = upload.parseRequest(request);
				//List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
				//List fileItems = request.getParameterValues("file");
				Iterator i = fileItems.iterator();
				while(i.hasNext()){
					FileItem fi = (FileItem) i.next();
					if(!fi.isFormField()){
						String fieldName = fi.getFieldName();
						String fileName = fi.getName();
						String contentType = fi.getContentType();
						boolean inMemory = fi.isInMemory();
						long sizeInBytes = fi.getSize();
						if(fileName.lastIndexOf("\\") >= 0){
							file = new File(filePath+fileName.substring(fileName.lastIndexOf("\\")));
						}
						else{
							file = new File(filePath+fileName.substring(fileName.lastIndexOf("\\")+1));
						}
						fi.write(file);
						out.println("File Upload : " + fileName);
					}
				}			
			}
			catch(Exception e){
				out.println(e);
			}*/
		  
		  //V4
			// gets absolute path of the web application
	        String appPath = request.getServletContext().getRealPath("/");
	        // constructs path of the directory to save uploaded file
	        String savePath = appPath + SAVE_DIR;
	        
	        String chemin = "/Users/bro/javaJEE/projet-ESGI-J2EE/WebContent/";
	         
	        // creates the save directory if it does not exists
	        File fileSaveDir = new File(request.getServletContext().getRealPath("/") + SAVE_DIR);
	        //File fileSaveDir = new File(savePath);
	        if (!fileSaveDir.exists()) {
	            fileSaveDir.mkdir();
	        }
	        else{
	        	System.out.println("dossier existe");
	        }
	         
	        for (Part part : request.getParts()) {
	           String fileName = extractFileName(part);
	            //part.write(savePath + File.separator + fileName);
	           if(fileName != ""){
	        	   part.write(chemin + File.separator + fileName);
	           }
	           // part.write(request.getServletContext().getRealPath("/") + SAVE_DIR + File.separator + fileName);
	          // part.write(getServletContext().getRealPath("/") + "WebContent" + File.separator + fileName);
	           //part.write("/Users/bro/javaJEE/projet-ESGI-J2EE/WebContent/" + fileName);
	        	System.out.println(fileName);
	        	System.out.println(savePath + File.separator + fileName);
	        }
	 
	        request.setAttribute("message", "Upload has been done successfully!");
	        /*getServletContext().getRequestDispatcher("/message.jsp").forward(
	                request, response);*/
		    
		    
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
	}

