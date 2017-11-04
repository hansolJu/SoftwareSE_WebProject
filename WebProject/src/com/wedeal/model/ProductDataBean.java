package com.wedeal.model;

/**
 * 2017.11.05
 * 검색 기능 테스트를 위한 믈품(게시글)dto 초안(?)
 * @author eunjin
 * 물품(게시글)의 dto
 * 
 * productId : PK 
 */
public class ProductDataBean {
	private int productID;
	private String productName;
	
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
}
