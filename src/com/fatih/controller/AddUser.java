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
	@WebServlet("/addUser")
	public class AddUser extends HttpServlet {
		private static final long serialVersionUID = 1L;
	       
	    /**
	     * @see HttpServlet#HttpServlet()
	     */
	    public AddUser() {
	        super();
	        // TODO Auto-generated constructor stub
	    }
	
		/**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			response.getWriter().append("Served at: ").append(request.getContextPath());
		}
	
		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	
			String name=request.getParameter("name");
			String email=request.getParameter("emailId");
			String password=request.getParameter("password");
			
			if(	(name==null || name.equals(""))||
				(email==null || email.equals(""))||
				(password==null || password.equals(""))){
				
				request.setAttribute("error", "All fields are required!");
				RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/users.jsp");
				dispatcher.forward(request, response);
				
			}else{
				
				User user=new User();
				
				user.setName(name);
				user.setEmail(email);
				user.setPassword(password);
				
				MongoClient mongoClient=(MongoClient) request.getServletContext().getAttribute("MONGO_CLIENT");
				
				UserDAO userDAO=new UserDAO(mongoClient);
				
				userDAO.createUser(user);
				
				System.out.println("Person Added Successfully with id=" + user.getId());
														
				request.setAttribute("success", "User added");
				
				List<User> users=userDAO.readAllUser();
				request.setAttribute("users", users);
				
				
				RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/users.jsp");
				
				dispatcher.forward(request, response);
				
				
			}
			
			System.out.println("name: "+name+"\nemail: "+email+"\npassword: "+password);
			
		}
	
	}
