package com.shoppingcart;

public class Cart {
	private int productId;
	private int quantity;
	private String userId;
	private String productName;
	private int totalPrice;
	
	public Cart(int productId, int quantity) {
		super();
		this.productId = productId;
		this.quantity = quantity;
	}

	

	public Cart(String userId, int productId, String productName, int quantity) {
		super();
		this.productId = productId;
		this.quantity = quantity;
		this.userId = userId;
		this.productName = productName;
	}


	

	public Cart(int productId, int quantity, String userId, String productName, int totalPrice) {
		super();
		this.productId = productId;
		this.quantity = quantity;
		this.userId = userId;
		this.productName = productName;
		this.totalPrice = totalPrice;
	}



	
	
	public int getTotalPrice() {
		return totalPrice;
	}



	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}



	public String getUserId() {
		return userId;
	}



	public void setUserId(String userId) {
		this.userId = userId;
	}



	public String getProductName() {
		return productName;
	}



	public void setProductName(String productName) {
		this.productName = productName;
	}



	public int getProductId() {
		return productId;
	}


	public void setProductId(int productId) {
		this.productId = productId;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}



	@Override
	public String toString() {
		return "Cart [productId=" + productId + ", quantity=" + quantity + ", userId=" + userId + ", productName="
				+ productName + ", totalPrice=" + totalPrice + "]";
	}



	
	
	

}
