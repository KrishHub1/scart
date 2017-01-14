package com.shoppingcart;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;



/**
 * Servlet implementation class ShoppingCartControllerServlet
 */
@WebServlet("/ShoppingCartControllerServlet")
public class ShoppingCartControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	private ShoppingCartDbUtil shoppingCartDbUtil;
	
	@Resource(name="jdbc/shopping_cart")
	
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		
		try{
			shoppingCartDbUtil = new ShoppingCartDbUtil(dataSource);
		}
		catch(Exception ex){
			throw new ServletException(ex);
		}
	}
	
	
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try{
			String theCommand = request.getParameter("command");
			
			if(theCommand == null){
				theCommand = "LIST";
			}
			
			switch(theCommand){
				case "LIST":
					listProducts(request, response);
					break;
				
				case "ADD":
					addProducts(request, response);
					break;
				case "LOAD":
					loadCart(request, response);
					break;
				case "LOGIN":
					login(request, response);
					break;
				case "ADDUSER":
					addUser(request, response);
					break;
				case "UPDATE":
					updateCart(request, response);
					break;
				case "INCREMENT":
					increment(request, response);
					break;
				case "DECREMENT":
					decrement(request, response);
					break;
				case "DELETE":
					delete(request, response);
					break;
				case "FORGOT":
					forgotPassword(request, response);
					break;
				default:
					login(request, response);
					break;
			}
		}
		catch(Exception ex){
			throw new ServletException(ex);
		}
	}

	
	

	

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		try{
			String theCommand = request.getParameter("command");
			
			if(theCommand == null){
				theCommand = "LIST";
			}
			
			switch(theCommand){
				case "LIST":
					listProducts(request, response);
					break;
				case "ADD":
					addProducts(request, response);
					break;
				case "LOAD":
					loadCart(request, response);
					break;
				case "LOGIN":
					login(request, response);
					break;
				case "LOGOFF":
					logOff(request, response);
					break;
				case "ADDUSER":
					addUser(request, response);
					break;
				case "INCREMENT":
					increment(request, response);
					break;
				case "DECREMENT":
					decrement(request, response);
					break;
				case "DELETE":
					delete(request, response);
					break;
				case "FORGOT":
					forgotPassword(request, response);
					break;
				case "ADDPRODUCT":
					addProducToDb(request, response);
					break;
				case "CHECKOUT":
					checkout(request, response);
					break;
				default:
					login(request, response);
					break;
			}
		}
		catch(Exception ex){
			throw new ServletException(ex);
		}	
	}
	
	
	
	
	private void checkout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		shoppingCartDbUtil.checkout(request.getSession().getId());
		
		loadCart(request, response);
	}

	private void addProducToDb(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		int productId = Integer.parseInt(request.getParameter("productId"));
		String productName = request.getParameter("productName");
		int price = Integer.parseInt(request.getParameter("price"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		
		Products product = new Products(productId, productName, price, quantity);
		
		shoppingCartDbUtil.addProductToDb(product);
		
		login(request, response);
	}

	private void forgotPassword(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String userName=request.getParameter("userName");
		
		String password = shoppingCartDbUtil.forgotPassword(userName);
		
		response.sendRedirect("./jsp/show-password-form.jsp?password=" + URLEncoder.encode(password, "UTF-8"));
		
		
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		
		String productId = request.getParameter("productId");
		
		shoppingCartDbUtil.deleteProduct(productId,request.getSession().getId() );
		
		loadCart(request, response);
	}

	private void increment(HttpServletRequest request, HttpServletResponse response) throws Exception{
		// TODO Auto-generated method stub
		int productId = Integer.parseInt(request.getParameter("productId"));
		int desiredQuantity = Integer.parseInt(request.getParameter("desiredQuantity"));

		Cart cart = new Cart(productId,desiredQuantity);
		
		shoppingCartDbUtil.increment(cart, request.getSession().getId());
		
		loadCart(request, response);
	}
	
	private void decrement(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		int productId = Integer.parseInt(request.getParameter("productId"));
		int desiredQuantity = Integer.parseInt(request.getParameter("desiredQuantity"));
		
		Cart cart = new Cart(productId,desiredQuantity);
		
		shoppingCartDbUtil.decrement(cart, request.getSession().getId());
		
		loadCart(request, response);
	}

	
	private void updateCart(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String productId = request.getParameter("productId");
		String desiredQuantity = request.getParameter("desiredQuantity");
		
		System.out.println(productId +" "+ desiredQuantity);
		
	}

	private void addUser(HttpServletRequest request, HttpServletResponse response) throws Exception{
		// TODO Auto-generated method stub
		
		String userName=request.getParameter("userName");
		String password=request.getParameter("password");
		
		//create new student
		User user = new User(userName, password);
		
		//add the student to the database
		shoppingCartDbUtil.addUser(user);
		
		//send back to main page
		login(request, response);
	}

	private void logOff(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		shoppingCartDbUtil.logOff(request.getSession().getId());
		
		response.sendRedirect("login.jsp");
		
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		
		String userId = request.getParameter("username");
		String password = request.getParameter("password");
		
		if((userId==null) || (password==null)){
			response.sendRedirect("login.jsp");
		}
		
		else{
			Boolean check = shoppingCartDbUtil.checkLogin(request.getSession().getId());
		
			if(check==true){
				listProducts(request, response);
			}
			else if(check==false){
				
				Boolean result = shoppingCartDbUtil.login(userId, password, request.getSession().getId());
		
					if(result==true){
						response.sendRedirect("ShoppingCartControllerServlet");
					}
		
					else{
						String message = "Invalid Username/Password";
						response.sendRedirect("login.jsp?message=" + URLEncoder.encode(message, "UTF-8"));
					}
			}
		}
	}
	

	private void listProducts(HttpServletRequest request, HttpServletResponse response) throws Exception{
		// TODO Auto-generated method stub
		
		List<ShoppingCart> products = shoppingCartDbUtil.getProducts(request.getSession().getId());
		
		request.setAttribute("PRODUCT_LIST", products );
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/list-products.jsp");
		dispatcher.forward(request, response);
		
	}
	
	private void addProducts(HttpServletRequest request, HttpServletResponse response) throws Exception{
		// TODO Auto-generated method stub
		
		String pId=request.getParameter("productId");
		String pQuantity=request.getParameter("desiredQuantity");

		int productId = Integer.parseInt(pId);
		int productQuantity = Integer.parseInt(pQuantity);
		
		
		shoppingCartDbUtil.addProduct(productId, productQuantity,request.getSession().getId());
		
		listProducts(request, response);
	}
	

	private void loadCart(HttpServletRequest request, HttpServletResponse response) throws Exception{
		// TODO Auto-generated method stub
		List<ShoppingCart> cart = shoppingCartDbUtil.getCart(request.getSession().getId());
		
		request.setAttribute("CART_LIST", cart);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("./jsp/cart-form.jsp");
		dispatcher.forward(request, response);
	}

}
