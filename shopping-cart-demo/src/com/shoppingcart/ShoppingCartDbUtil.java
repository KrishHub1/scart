package com.shoppingcart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class ShoppingCartDbUtil {
	private DataSource dataSource;
	

	public ShoppingCartDbUtil(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public List<ShoppingCart> getProducts(String sessionId) throws Exception{
		List<ShoppingCart> products = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		PreparedStatement myStmt1 = null;
		ResultSet myRs1 = null;
		
		String userId=null;
		
		try{
			
			myConn = dataSource.getConnection();
			
			String sql1 = "select user_id from session where session_id=?";
			
			myStmt1 = myConn.prepareStatement(sql1);
			
			myStmt1.setString(1, sessionId);

			
			myRs1 = myStmt1.executeQuery();
			
			while(myRs1.next()){
				userId = myRs1.getString("user_id");
			}
			
			String sql = "select * from product order by product_id";
			
			myStmt = myConn.createStatement();
			
			myRs = myStmt.executeQuery(sql);
			
			while(myRs.next()){
				int productId = myRs.getInt("product_id");
				String productName = myRs.getString("product_name");
				int productPrice = myRs.getInt("price");
				int productQuantity = myRs.getInt("quantity");
				
				ShoppingCart tempProduct = new ShoppingCart(productId, productName, productPrice, productQuantity, userId);
				
				products.add(tempProduct);
			}
			
			return products;
		}
		finally{
			close(null, myStmt1, myRs1);
			close(myConn, myStmt, myRs);
		}
	}

	
	public List<ShoppingCart> getCart(String sessionId) throws Exception{
		List<ShoppingCart> cart = new ArrayList<>();
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		PreparedStatement myStmt1 = null;
		PreparedStatement myStmt2 = null;
		ResultSet myRs = null;
		ResultSet myRs1 = null;
		ResultSet myRs2 = null;
		String userId = null;
		String userid = null;
		int tPrice=0;
		
		try{
			
			myConn = dataSource.getConnection();
			
			
			
			String sql1 = "select user_id from session where session_id=?";
			
			myStmt1 = myConn.prepareStatement(sql1);
			
			myStmt1.setString(1, sessionId);

			
			myRs1 = myStmt1.executeQuery();
			
			while(myRs1.next()){
				userId = myRs1.getString("user_id");
			}
			
			String sql = "select c.productid, c.product_name, c.quantity, c.user_id, c.total_price from cart c where c.user_id=? order by c.productid";
			myStmt = myConn.prepareStatement(sql);
			myStmt2 = myConn.prepareStatement(sql);
			
			myStmt.setString(1, userId);
			myStmt2.setString(1, userId);
			
			myRs = myStmt.executeQuery();
			myRs2 = myStmt2.executeQuery();
			
			while(myRs2.next()){
				int totalPrice = myRs2.getInt("total_price");
				tPrice = tPrice+totalPrice;
			}
			
			while(myRs.next()){
				userid = myRs.getString("user_id");
				int productId = myRs.getInt("productid");
				String productName = myRs.getString("product_name");
				int desiredQuantity = myRs.getInt("quantity");
				int totalPrice = myRs.getInt("total_price");
				
				ShoppingCart tempProduct = new ShoppingCart(productId, productName, desiredQuantity, userid, totalPrice, tPrice);
				
				cart.add(tempProduct);
			}
			
			return cart;
		}
		finally{
			
			close(null, myStmt1, myRs1);
			close(null, myStmt2, myRs2);
			close(myConn, myStmt, myRs);
		}
	}
	
	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
		// TODO Auto-generated method stub
		try{
			if(myRs != null) myRs.close();
			if(myStmt != null) myStmt.close();
			if(myConn != null) myConn.close();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}

	public void addProduct(int id, int quantity, String sessionId) throws Exception {
		// TODO Auto-generated method stub
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		PreparedStatement myStmt1 = null;
		PreparedStatement myStmt2 = null;
		ResultSet myRs = null;
		ResultSet myRs1 = null;
		String userId=null;
		String pname=null;
		int price = 1;
		
		
		try{
			myConn=dataSource.getConnection();
			
			String sql1 = "select user_id from session where session_id=?";
			myStmt1 = myConn.prepareStatement(sql1);
			myStmt1.setString(1, sessionId);
			myRs = myStmt1.executeQuery();
			
			while(myRs.next()){
				userId = myRs.getString("user_id");
			}
			
			
			String sql2 = "select product_name, price from product where product_id=?";
			myStmt2 = myConn.prepareStatement(sql2);
			myStmt2.setInt(1, id);
			myRs1 = myStmt2.executeQuery();
			
			while(myRs1.next()){
				pname = myRs1.getString("product_name");
				price = myRs1.getInt("price");
			}
			
			String sql = "insert into cart "
					+ "(user_id, productid, product_name, quantity, total_price) "
					+ "values(?,?,?,?,?)";
			
			myStmt = myConn.prepareStatement(sql);
			
			myStmt.setString(1, userId);
			myStmt.setInt(2, id);
			myStmt.setString(3, pname);
			myStmt.setInt(4, quantity);
			myStmt.setInt(5, price*quantity);
			
			myStmt.execute();	
		}
		finally{
			close(null, myStmt1, myRs);
			close(null, myStmt2, myRs1);
			close(myConn, myStmt, null);
		}
	}

	public Boolean login(String userId, String password, String sessionId) throws Exception {
		// TODO Auto-generated method stub
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		PreparedStatement myStmt1 = null;
		ResultSet myRs = null;
		
		try{
			String userName=null;
			String pass=null;
			myConn = dataSource.getConnection();
			
			String sql = "select * from user where userid = ?";
			
			myStmt = myConn.prepareStatement(sql);
			
			myStmt.setString(1, userId);
			
			myRs = myStmt.executeQuery();
			
			while(myRs.next()){
				userName = myRs.getString("userid");
				pass = myRs.getString("password");
				
			}
			
			if((userId.equals(userName)) && (password.equals(pass))){
				
				try{
					String sql1 = "insert into session "
								+ "(session_id, user_id) "
								+ "values(?, ?)";
					
					myStmt1 = myConn.prepareStatement(sql1);
					
					myStmt1.setString(1, sessionId);
					myStmt1.setString(2, userName);
					
					myStmt1.execute();
				}
				finally{
					close(null,myStmt1,null);
				}
				
				return true;
			}
			else{
				return false;
			}
		}
		finally{
			close(myConn, myStmt, myRs);
		}
		
	}

	public Boolean checkLogin(String id) throws Exception {
		// TODO Auto-generated method stub
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		String sessionId = null;
		
		try{
			myConn = dataSource.getConnection();
			
			String sql = "select session_id from session where session_id = ?";
			
			myStmt = myConn.prepareStatement(sql);
			
			myStmt.setString(1, id);
			
			myRs = myStmt.executeQuery();
			
			while(myRs.next()){
				sessionId = myRs.getString("session_id");
			}
			
			if(id.equals(sessionId)){
				return true;
			}
			else
				return false;
		}
		finally{
			close(myConn, myStmt, myRs);
		}
		
	}

	public void logOff(String id) throws Exception{
		// TODO Auto-generated method stub
		Connection myConn=null;
		PreparedStatement myStmt=null;
		PreparedStatement myStmt1=null;
		ResultSet myRs = null;
		String userId = null;
		
		try{
			myConn = dataSource.getConnection();
			
			String sql1 = "select user_id from session where session_id=?";
			myStmt1 = myConn.prepareStatement(sql1);
			
			myStmt1.setString(1, id);
			
			myRs = myStmt1.executeQuery();
			
			while(myRs.next()){
				userId = myRs.getString("user_id");
			}
			
			String sql = "delete from session where user_id=?";
			myStmt = myConn.prepareStatement(sql);
			
			myStmt.setString(1, userId);
			
			myStmt.execute();
		}
		finally{
			close(null, myStmt1, myRs);
			close(myConn, myStmt, null);
		}
	}

	public void addUser(User user) throws Exception{
		// TODO Auto-generated method stub
		Connection myConn=null;
		PreparedStatement myStmt=null;
		
		try{
			myConn = dataSource.getConnection();
			
			String sql = "insert into user "
					+ "(userid, password) "
					+ "values(?, ?)";
			
			myStmt = myConn.prepareStatement(sql);
			
			myStmt.setString(1, user.getUserName() );
			myStmt.setString(2, user.getPassword());
			
			myStmt.execute();
			
		}
		finally{
			close(myConn, myStmt, null);
		}
	}

	public void increment(Cart cart, String id) throws Exception {
		// TODO Auto-generated method stub
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		PreparedStatement myStmt1 = null;
		PreparedStatement myStmt2 = null;
		ResultSet myRs = null;
		ResultSet myRs1 = null;
		ResultSet myRs2 = null;
		String userId = null;
		
		int price = 1;
		
		try{
			myConn = dataSource.getConnection();
			String sql1 = "select user_id from session where session_id=?";
			
			myStmt1=myConn.prepareStatement(sql1);
			
			myStmt1.setString(1, id);
			
			myRs1 = myStmt1.executeQuery();
			
			while(myRs1.next()){
				userId = myRs1.getString("user_id");
			}
			
			String sql2 = "select price from product where product_id=?";
			
			myStmt2=myConn.prepareStatement(sql2);
			
			myStmt2.setInt(1,cart.getProductId());
			
			myRs2 = myStmt2.executeQuery();
			
			while(myRs2.next()){
				price = myRs2.getInt("price");
			}
			
			String sql = "update cart "
					+ "set quantity=?, total_price=? "
					+ "where user_id=? and productid=?";
			
			myStmt = myConn.prepareStatement(sql);
			
			myStmt.setInt(1, (cart.getQuantity())+1);
			myStmt.setInt(2, price*((cart.getQuantity())+1));
			myStmt.setString(3, userId);
			myStmt.setInt(4, cart.getProductId());
			
			myStmt.execute();
			
		}
		finally{
			close(null, myStmt1, myRs1);
			close(null, myStmt2, myRs2);
			close(myConn, myStmt, myRs);
		}
		
	}

	public void decrement(Cart cart, String id) throws Exception {
		// TODO Auto-generated method stub
		Connection myConn = null;
		PreparedStatement myStmt = null;
		PreparedStatement myStmt1 = null;
		PreparedStatement myStmt2 = null;
		ResultSet myRs = null;
		ResultSet myRs1 = null;
		ResultSet myRs2 = null;
		String userId = null;
		int price = 1;
		
		try{
			myConn = dataSource.getConnection();
			String sql1 = "select user_id from session where session_id=?";
			
			myStmt1=myConn.prepareStatement(sql1);
			
			myStmt1.setString(1, id);
			
			myRs1 = myStmt1.executeQuery();
			
			while(myRs1.next()){
				userId = myRs1.getString("user_id");
			}
			
			String sql2 = "select price from product where product_id=?";
			
			myStmt2=myConn.prepareStatement(sql2);
			
			myStmt2.setInt(1,cart.getProductId());
			
			myRs2 = myStmt2.executeQuery();
			
			while(myRs2.next()){
				price = myRs2.getInt("price");
			}
			
			String sql = "update cart "
					+ "set quantity=?, total_price=? "
					+ "where user_id=? and productid=?";
			
			myStmt = myConn.prepareStatement(sql);
			
			myStmt.setInt(1, (cart.getQuantity())-1);
			myStmt.setInt(2, price*((cart.getQuantity())-1));
			myStmt.setString(3, userId);
			myStmt.setInt(4, cart.getProductId());
			
			myStmt.execute();
			
		}
		finally{
			close(null, myStmt1, myRs1);
			close(myConn, myStmt, myRs);
		}
	}

	public void deleteProduct(String pId, String id) throws Exception {
		// TODO Auto-generated method stub
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		PreparedStatement myStmt1 = null;
		ResultSet myRs1 = null;
		String userId = null;
		
		int productId = Integer.parseInt(pId);

		try{
			myConn = dataSource.getConnection();
			String sql1 = "select user_id from session where session_id=?";
			
			myStmt1=myConn.prepareStatement(sql1);
			
			myStmt1.setString(1, id);
			
			myRs1 = myStmt1.executeQuery();
			
			while(myRs1.next()){
				userId = myRs1.getString("user_id");
			}
			
			String sql = "delete from cart where user_id=? and productid=?";
			
			myStmt = myConn.prepareStatement(sql);
			
			myStmt.setString(1, userId);
			myStmt.setInt(2, productId);
			
			myStmt.execute();
			
		}
		finally{
			close(null, myStmt1, myRs1);
			close(myConn, myStmt, null);
		}
		
		
	}

	public String forgotPassword(String userName) throws Exception {
		// TODO Auto-generated method stub
		String password = null;
		Connection myConn = null;
		PreparedStatement myStmt = null;
		PreparedStatement myStmt1 = null;
		ResultSet myRs = null;
		String userId = null;
		
		try{
			myConn = dataSource.getConnection();
			
			String sql = "select password from user where userid=?";
			
			myStmt = myConn.prepareStatement(sql);
			
			myStmt.setString(1, userName);
			
			myRs = myStmt.executeQuery();
			
			while(myRs.next()){
				password = myRs.getString("password");
			}
			return password;
		}
		finally{
			close(myConn, myStmt, myRs);
		}
		
	}

	public void addProductToDb(Products product) throws Exception {
		// TODO Auto-generated method stub
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try{
			myConn = dataSource.getConnection();
			
			String sql = "insert into product "
					+ "(product_id, product_name, price, quantity) "
					+ "values(?,?,?,?)";
			
			myStmt = myConn.prepareStatement(sql);
			
			myStmt.setInt(1, product.getProductId());
			myStmt.setString(2, product.getProductName());
			myStmt.setInt(3, product.getPrice());
			myStmt.setInt(4, product.getQuantity());
			
			myStmt.execute();
		}
		finally{
			close(myConn, myStmt, null);
		}
	}

	public void checkout(String id) throws Exception {
		// TODO Auto-generated method stub
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		PreparedStatement myStmt1 = null;
		ResultSet myRs = null;
		ResultSet myRs1 = null;
		String userId = null;
		
		try{
			myConn = dataSource.getConnection();
			
			String sql1 = "select user_id from session where session_id=?";
			
			myStmt1=myConn.prepareStatement(sql1);
			
			myStmt1.setString(1, id);
			
			myRs1 = myStmt1.executeQuery();
			
			while(myRs1.next()){
				userId = myRs1.getString("user_id");
			}
			
			String sql2 = "delete from cart where user_id=?";
			
			myStmt = myConn.prepareStatement(sql2);
			
			myStmt.setString(1, userId);
			
			myStmt.execute();
		}
		finally{
			close(myConn, myStmt, myRs);
		}
		
	}
	
	

}
