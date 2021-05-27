package com.qa.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.qa.utils.DBconfig;

public class DBConnection {

	private PreparedStatement ps;
	private Connection con;
	private ResultSet rs;

	public DBConnection() throws SQLException {
		con = DriverManager.getConnection(DBconfig.url, DBconfig.user, DBconfig.pw);
	} //set to constructor if it give you error

	// Create

	public void create(String name) throws SQLException {
		String sql = "INSERT INTO customer (name) VALUES (?)";
		ps = con.prepareStatement(sql);
		ps.setString(1, name);
		ps.execute();
		
	}
	

	// Read

	public void read() throws SQLException {
		String sql = "SELECT * FROM customer";
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		
		if (!rs.next()) {
			System.out.println("Nothing was found. Please try again!");
		} else {
			do {
				System.out.println(String.format("ID: %d, Name: %s", rs.getInt("id"), rs.getString("name")));
			} while(rs.next());
		}
	}
    // Read by id
	public void readOne(int id) throws SQLException {
		String sql = "SELECT * FROM customer WHERE id = ?";
		ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		rs = ps.executeQuery();
		
		if (!rs.next()) {
			System.out.println("Nothing was found. Please try again!");
		} else {
			do {
				System.out.println(String.format("ID: %d, Name: %s", rs.getInt("id"), rs.getString("name")));
			} while(rs.next());
		}
	}
	
	// Update

	public void update(int uId, String name) throws SQLException {

		ps = con.prepareStatement("UPDATE customer SET name =? WHERE id = ?");
		ps.setInt(2, uId);
		ps.setString(1, name);
		ps.execute();
	}

	// Delete

	public void delete(int id) throws SQLException {
		ps = con.prepareStatement("DELETE FROM customer WHERE id = ?");
		ps.setInt(1, id);
		ps.execute();
	}

	
	public void tearDown() throws SQLException {
		con.close();
	}

}
