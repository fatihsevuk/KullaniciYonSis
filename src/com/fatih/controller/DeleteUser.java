	package com.fatih.controller;
	
	import java.io.IOException;
import java.util.List;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
	import javax.servlet.ServletException;
	import javax.servlet.annotation.WebServlet;
	import javax.servlet.http.HttpServlet;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;
	
	import com.fatih.dao.UserDAO;
	import com.fatih.model.User;
	import com.mongodb.MongoClient;
	
	/**
	 * Servlet implementation class AddUser
	 */
	@WebServlet("/deleteUser")
	public class DeleteUser extends HttpServlet {
		private static final long serialVersionUID = 1L;
	       
	    /**
	     * @see HttpServlet#HttpServlet()
	     */
	    public DeleteUser() {
	        super();
	        // TODO Auto-generated constructor stub
	    }
	
		/**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String id=request.getParameter("id");
			
			if(id==null || "".equals(id)){
				throw new ServletException("id missing for delete operation.");
			}
			
			MongoClient mongoClient=(MongoClient) request.getServletContext().getAttribute("MONGO_CLIENT");
			
			UserDAO userDAO=new UserDAO(mongoClient);
			
			User user=new User();
			
			user.setId(id);
			
			userDAO.deletUser(user);
			
			System.out.println("User delted successfully with id=" +id);
			
			request.setAttribute("success", "User deleted successfully!");
			
			List<User> users=userDAO.readAllUser();
			
			request.setAttribute("users", users);
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher(
					"/users.jsp");
			rd.forward(request, response);
			
			
		}
	
	}
