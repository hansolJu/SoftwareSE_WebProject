package com.wedeal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.wedeal.util.DbUtil;

public class ProductDAO {
	
	private Connection connection;
	private static ProductDAO instance = new ProductDAO(); //싱글턴

	private ProductDAO() {
		connection = DbUtil.getConnection();
	}
	private static ProductDAO getInstance() {
		return instance;
	}
	public ArrayList<ProductDTO> selectProductName(String searchName) throws SQLException{
		ResultSet rset = null;
		
		ArrayList<ProductDTO> searchNameProductList = new ArrayList<ProductDTO>();
		PreparedStatement preparedStatement = connection.prepareStatement("select * from where product like '%'||?||'%'");
		preparedStatement.setString(1,  searchName);
		rset = preparedStatement.executeQuery();
		while(rset.next()) {
			searchNameProductList.add(rset.get)
		}
		
	}

}
