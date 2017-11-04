package com.wedeal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
		ArrayList<ProductDTO> selectNameProductList = new ArrayList<ProductDTO>();
		Connection conn = null;
		
		PreparedStatement preparedStatement = connection.prepareStatement("insert into users(user_name,user_age,user_phone,user_pw,user_hope_1,user_hope_2,user_hope_3) values (?, ?, ?, ?, ?, ?, ?, ? )");
	}

}
