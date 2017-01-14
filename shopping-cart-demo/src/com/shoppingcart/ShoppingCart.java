package com.shoppingcart;

public class ShoppingCart {

	private int productId;
	private String productName;
	private int productQuantity;
	private int productPrice;
	private int desiredQuantity;
	private String userId;
	private String password;
	private int totalPrice;
	private int tPrice;
	
	
	public ShoppingCart() {
		super();
	}

	public ShoppingCart(String userId, String password) {
		super();
		this.userId = userId;
		this.password = password;
	}
	
	public ShoppingCart(int productId, String productName, int desiredQuantity, String userId) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.desiredQuantity = desiredQuantity;
		this.userId = userId;
	}

	public ShoppingCart(int productId, String productName, int productPrice, int productQuantity, String userId) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productQuantity = productQuantity;
		this.userId = userId;
	}
	
	

	public ShoppingCart(int productId, String productName, int desiredQuantity, String userId, int totalPrice, int tPrice) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.desiredQuantity = desiredQuantity;
		this.userId = userId;
		this.totalPrice = totalPrice;
		this.settPrice(tPrice); 
	}

	public ShoppingCart(int productId, String productName, int desiredQuantity) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.desiredQuantity = desiredQuantity;
	}

	public ShoppingCart(int productId, int desiredQuantity) {
		super();
		this.productId = productId;
		this.desiredQuantity = desiredQuantity;
	}
	
	public ShoppingCart(int productId, String productName, int productPrice ,int productQuantity) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productQuantity = productQuantity;
	}

	public int getDesiredQuantity() {
		return desiredQuantity;
	}

	public void setDesiredQuantity(int desiredQuantity) {
		this.desiredQuantity = desiredQuantity;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

	

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	
	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	

	public int gettPrice() {
		return tPrice;
	}

	public void settPrice(int tPrice) {
		this.tPrice = tPrice;
	}

	@Override
	public String toString() {
		return "ShoppingCart [productId=" + productId + ", productName=" + productName + ", productQuantity="
				+ productQuantity + ", productPrice=" + productPrice + ", desiredQuantity=" + desiredQuantity
				+ ", userId=" + userId + ", password=" + password + ", totalPrice=" + totalPrice + ", tPrice=" + tPrice
				+ "]";
	}

	

	
	
}
