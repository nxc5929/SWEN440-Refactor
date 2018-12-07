package org.rit.swen440.dataLayer;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Repository {

	private Statement query;

	public Repository(){
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			System.err.print("Couldn't load JDBC");
		}

		//create Connection
		Connection connection;
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:inventory.db");
			System.out.println("Connection Successful");
			query = connection.createStatement();
			query.setQueryTimeout(30);
			query.executeUpdate("drop table if exists category");
			query.executeUpdate("create table category (id integer, name string, desc string)");
			query.executeUpdate("drop table if exists product");
			query.executeUpdate("create table product (id integer, productId integer, skuCode integer, itemCount integer, title string, desc string, cost string)");
			
			query.executeUpdate("insert into category values(1, 'food', 'edible yummy food items')");
			query.executeUpdate("insert into product values(1, 1, 1111, 75, 'apples', 'red/green circle that grows on trees', '2.57')");
			query.executeUpdate("insert into product values(2, 1, 1222, 15, 'bananas', 'yellow fruit that grows on trees', '2.10')");
			
			query.executeUpdate("insert into category values(2, 'toys', 'Occupy the kids for hours')");
			query.executeUpdate("insert into product values(3, 2, 2333, 5, 'Rubics Cube', 'Difficult puzzle', '6.79')");
			query.executeUpdate("insert into product values(4, 2, 2444, 19, 'Frisbee', 'Magical spinning disc', '10.99')");
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.print("Connection to database failed");
		}

	}

	public List<String> getAllCategories() {
		List<String> list = new ArrayList<String>();
		try {
			ResultSet rs = query.executeQuery("select name from category");
			while(rs.next()){
				list.add(rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public String getCategoryDesc(String category) {
		try {
			ResultSet rs = query.executeQuery("select desc from category where name = " + category);
			if(rs.next()){
				return rs.getString("desc");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "Category not found";
	}

	public List<String> getAllProducts(String categoryName) {
		List<String> list = new ArrayList<String>();
		try {
			ResultSet rs = query.executeQuery("SELECT * FROM product p JOIN category c ON c.id = p.productId AND c.name = '" + categoryName +"'");
			while (rs.next()) {
			    list.add(rs.getString("title"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public Product getProductInfo(String category, String productName) {
		Product product = new Product();
		try {
			ResultSet rs = query.executeQuery("SELECT * FROM product p JOIN category c ON c.id = p.productId AND c.name = '" + category +"' AND p.title = '" + productName + "'");
			if(rs.next()) {
			    product.setTitle(rs.getString("title"));
			    product.setDescription(rs.getString("desc"));
			    product.setCost(new BigDecimal(rs.getString("cost")));
			    product.setItemCount(rs.getInt("itemCount"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return product;
	}

}
