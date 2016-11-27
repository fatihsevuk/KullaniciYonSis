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
	@WebServlet("/editUser")
	public class EditUser extends HttpServlet {
		private static final long serialVersionUID = 1L;
	       
	    /**
	     * @see HttpServlet#HttpServlet()
	     */
	    public EditUser() {
	        super();
	        // TODO Auto-generated constructor stub
	    }
	
		/**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			
			String id=request.getParameter("id");
			
			if(id==null || "".equals(id)){
				throw new ServletException("id missing for edit operation.");
			}
			
			System.out.println("user edit requested wit id: "+id);
			
			MongoClient mongoClient=(MongoClient) request.getServletContext().getAttribute("MONGO_CLIENT");
			
			UserDAO userDAO=new UserDAO(mongoClient);
			
			User user=new User();
			
			user.setId(id);
			
			user=userDAO.readUser(user);
			
			request.setAttribute("user", user);
			
			List<User> users=userDAO.readAllUser();
			request.setAttribute("users", users);
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher(
					"/users.jsp");
			rd.forward(request, response);
		}
	
		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	
			
			String id=request.getParameter("id");
			
			if(	id==null || "".equals(id)){
				throw new ServletException("id missing for edit operation");
			}
			
			String name=request.getParameter("name");
			String email=request.getParameter("emailId");
			String password=request.getParameter("password");
			
			System.out.println("name from req is: "+name);
			System.out.println("password from req is: "+password);
			
			if((name==null || name.equals(""))||
					(email==null || email.equals(""))||
					(password==null || password.equals(""))){
								
				request.setAttribute("error", "Name ,email and password cannot be empty");
				
				MongoClient mongoClient=(MongoClient) request.getServletContext().getAttribute("MONGO_CLIENT");
				
				UserDAO userDAO=new UserDAO(mongoClient);
				
				User user=new User();
				
				user.setId(id);
				user.setName(name);
				user.setEmail(email);
				user.setPassword(password);
				
				request.setAttribute("user", user);
				List<User> users=userDAO.readAllUser();
				request.setAttribute("users", users);
				
				
				
				RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/users.jsp");
				dispatcher.forward(request, response);
				
			}else{
				
				
				
				MongoClient mongoClient=(MongoClient) request.getServletContext().getAttribute("MONGO_CLIENT");
				
				UserDAO userDAO=new UserDAO(mongoClient);
				
				User user=new User();
				
				user.setId(id);
				user.setName(name);
				user.setEmail(email);
				user.setPassword(password);
				
				
				
				userDAO.updateUser(user);
				
				System.out.println("User Edited Successfully with id=" + user.getId());
														
				request.setAttribute("success", "User edited succesfully");
				
				List<User> users=userDAO.readAllUser();
				request.setAttribute("users", users);
				
				
				RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/users.jsp");
				
				dispatcher.forward(request, response);
				
				
			}
			
			System.out.println("name: "+name+"\nemail: "+email+"\npassword: "+password);
			
		}
	
	}
