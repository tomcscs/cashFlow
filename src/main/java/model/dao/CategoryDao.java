package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.vo.Category;

public class CategoryDao {
	// 전체 찾기
	public List<Category> findAll() throws ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@3.34.136.55:1521:xe", "cashflow",
				"oracle")) {
			String sql = "SELECT * FROM CATEGORYS ORDER BY ID ASC";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();
			List<Category> list = new ArrayList<>();
			while (rs.next()) {
				int id = rs.getInt("id"); // rs.getInt("code")
				String name = rs.getString("name"); // rs.getString("name");
				
				Category one = new Category(id, name);
				list.add(one);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
