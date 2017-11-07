package com.wedeal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.wedeal.model.ProductDataBean;
import com.wedeal.util.DbUtil;

public class ProductDAO {
	
	private Connection connection;
	private static ProductDAO instance = new ProductDAO(); //싱글톤

	private ProductDAO() {}
	public static ProductDAO getInstance() {
		return instance;
	}
	/**
	 * 
	 * @param category
	 * @return
	 * @throws SQLException
	 * 상품명과 카테고리로 검색
	 */
	public ArrayList<ProductDataBean> selectProductByCategory(String searchName, String category) throws SQLException {
		connection = DbUtil.getConnection();  //connection 연결
		ResultSet rset = null;
		PreparedStatement preparedStatement = null;
		
		ArrayList<ProductDataBean> searchCategoryProductList = new ArrayList<ProductDataBean>();  //반환할 결과 리스트
		preparedStatement = connection.prepareStatement("select * from product where name like '%'||?||'%' and category = ?");  //해당 카테고리의 해당 이름을 포함하는 물품 검색
		preparedStatement.setString(1, searchName);
		preparedStatement.setString(2,  category);
		rset = preparedStatement.executeQuery();
		while(rset.next()) {
			//각 dto 변수 저장
			ProductDataBean product = new ProductDataBean();
			product.setProductID(rset.getInt(0));
			product.setProductName(rset.getString(1));
			//TO-DO : 더 추가해야함
			searchCategoryProductList.add(product);  //리스트에 추가
		}
		DbUtil.close(connection, preparedStatement, rset);  //연결 해지
		return searchCategoryProductList;
	}
	/**
	 * 
	 * @param searchName
	 * @return 결과 list 반환
	 * @throws SQLException
	 * 이름으로 물품을 검색하는 메소드
	 * TO-DO : dto에 맞게 변수 추가
	 */
	public ArrayList<ProductDataBean> selectProductByName(String searchName) throws SQLException{
		connection = DbUtil.getConnection();  //connection 연결
		ResultSet rset = null;
		PreparedStatement preparedStatement = null;
		
		ArrayList<ProductDataBean> searchNameProductList = new ArrayList<ProductDataBean>();  //반환할 결과 리스트
		preparedStatement = connection.prepareStatement("select * from product where name like '%'||?||'%'");  //해당 이름을 포함하는 물품 검색
		preparedStatement.setString(1, searchName);
		rset = preparedStatement.executeQuery();
		while(rset.next()) {
			//각 dto 변수 저장
			ProductDataBean product = new ProductDataBean();
			product.setProductID(rset.getInt(0));
			product.setProductName(rset.getString(1));
			//TO-DO : 더 추가해야함
			searchNameProductList.add(product);  //리스트에 추가
		}
		DbUtil.close(connection, preparedStatement, rset);  //연결 해지
		return searchNameProductList;
	}
}
