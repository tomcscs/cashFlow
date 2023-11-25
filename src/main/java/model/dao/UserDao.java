package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.vo.Avatar;
import model.vo.User;
import model.vo.UserWithAvatar;

public class UserDao {
	public boolean save(User user) throws ClassNotFoundException {
		// 1. 데이터 베이스 연결
		Class.forName("oracle.jdbc.driver.OracleDriver");
		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@3.34.136.55:1521:xe", "cashflow",
				"oracle")) {
			boolean result = false;
			// 2. 필요한 작업요청을 전송하고 응답을 받으면 됨.
			String sql = "INSERT INTO USERS VALUES(?, ?, ?, ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getId());
			pstmt.setString(2, user.getPassword());
			pstmt.setInt(3, user.getBirth());
			pstmt.setString(4, user.getGender());
			pstmt.setString(5, user.getNickname());
			pstmt.setString(6, user.getAvatarId());

			int n = pstmt.executeUpdate(); // 요청 전송하고 DB에서 응답을 받아옴.
			if (n == 1) {
				result = true;
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public User findById(String userId) throws ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@3.34.136.55:1521:xe", "cashflow",
				"oracle")) {
			String sql = "SELECT * FROM USERS WHERE ID=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				User user = new User();
				user.setId( rs.getString("id")); // rs.getInt("code")
				user.setPassword(rs.getString("password"));
				user.setNickname(rs.getString("nickname"));
				user.setBirth(rs.getInt("birth"));
				user.setAvatarId(rs.getString("avatar_id"));
				user.setGender(rs.getString("gender"));
				return user;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//======================================================================================
	public User findUserWithAvatarById(String userId) throws ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@3.34.136.55:1521:xe", "cashflow",
				"oracle")) {
			String sql = "SELECT u.*, a.ALT, a.IMAGE_URL FROM USERS u LEFT JOIN AVATARS a ON u.AVATAR_ID = a.ID WHERE u.ID=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				User user = new User();
				user.setId( rs.getString("id")); // rs.getInt("code")
				user.setPassword(rs.getString("password"));
				user.setNickname(rs.getString("nickname"));
				user.setBirth(rs.getInt("birth"));
				user.setAvatarId(rs.getString("avatar_id"));
				user.setGender(rs.getString("gender"));
				
				Avatar a = new Avatar();
					a.setId(rs.getString("avatar_id"));
					a.setAlt(rs.getString("alt"));
					a.setImageUrl(rs.getString("image_url"));
				
				user.setAvatar(a);
				
				return user;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
