package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.vo.Avatar;

public class AvatarDao {
	// 전체 찾기
	public List<Avatar> findAll() throws ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@3.34.136.55:1521:xe", "cashflow",
				"oracle")) {
			String sql = "SELECT * FROM AVATARS";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();
			List<Avatar> list = new ArrayList<>();
			while (rs.next()) {
				String id = rs.getString("id"); // rs.getInt("code")
				String alt = rs.getString("alt"); // rs.getString("name");
				String imageUrl = rs.getString("image_url"); // rs.getString("name");

				Avatar one = new Avatar(id, alt, imageUrl);
				list.add(one);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// id 로 찾기
	public Avatar findById(String avatarId) throws ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@3.34.136.55:1521:xe", "cashflow",
				"oracle")) {
			String sql = "SELECT * FROM AVATARS WHERE ID=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, avatarId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				String id = rs.getString("id"); // rs.getInt("code")
				String alt = rs.getString("alt"); // rs.getString("name");
				String imageUrl = rs.getString("image_url"); // rs.getString("name");

				Avatar one = new Avatar(id, alt, imageUrl);
				return one;
			} else {
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
