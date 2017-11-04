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
	private static ProductDataBean instance = new ProductDataBean(); //싱글턴

	private ProductDAO() {
		connection = DbUtil.getConnection();
	}
	private static ProductDataBean getInstance() {
		return instance;
	}
	public ArrayList<ProductDataBean> selectProductName(String searchName) throws SQLException{
		ResultSet rset = null;
		
		ArrayList<ProductDataBean> searchNameProductList = new ArrayList<ProductDataBean>();
		PreparedStatement preparedStatement = connection.prepareStatement("select * from product where name like '%'||?||'%'");
		preparedStatement.setString(1,  searchName);
		rset = preparedStatement.executeQuery();
		while(rset.next()) {
			//각 dto 변수 저장
			ProductDataBean product = new ProductDataBean();
			product.setProductID(rset.getInt(0));
			product.setProductName(rset.getString(1));
			//더 추가해야함
			searchNameProductList.add(product);
		}
		DbUtil.close(connection, preparedStatement, rset);
		return searchNameProductList;
	}

}
