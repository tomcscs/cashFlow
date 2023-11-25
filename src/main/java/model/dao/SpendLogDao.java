package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.vo.Category;
import model.vo.SpendLog;

public class SpendLogDao {
	public boolean save(SpendLog spendLog) throws Exception {
		// 1. 데이터 베이스 연결
		Class.forName("oracle.jdbc.driver.OracleDriver");
		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@3.34.136.55:1521:xe", "cashflow",
				"oracle")) {
			boolean result = false;
			// 2. 필요한 작업요청을 전송하고 응답을 받으면 됨.
			String sql = "INSERT INTO SPEND_LOGS VALUES(SPEND_LOGS_SEQ.NEXTVAL, ?, ?, ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, spendLog.getUserId());
			pstmt.setDate(2, spendLog.getSpendAt());
			pstmt.setInt(3, spendLog.getAmt());
			pstmt.setString(4, spendLog.getUseDesc());
			pstmt.setInt(5, spendLog.getCategoryId());

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

	// =========================================
	public List<SpendLog> findByUserId(String userId) throws ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@3.34.136.55:1521:xe", "cashflow",
				"oracle")) {
			String sql = "SELECT s.*, c.NAME FROM SPEND_LOGS s JOIN CATEGORYS c ON s.CATEGORY_ID = c.ID WHERE USER_ID=? ORDER BY SPEND_AT ASC, NO ASC";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);

			ResultSet rs = pstmt.executeQuery();
			List<SpendLog> list = new ArrayList<>();
			while (rs.next()) {
				// SpendLog(int no, String userId, Date spendAt, int amt, String useDesc, int
				// categoryId, Category category) {
				SpendLog log = new SpendLog(rs.getInt("no"), rs.getString("user_id"), rs.getDate("spend_at"),
						rs.getInt("amt"), rs.getString("use_desc"), rs.getInt("category_id"),
						new Category(rs.getInt("category_id"), rs.getString("name")));

				list.add(log);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<SpendLog> findByUserIdAndConditions(String userId, String sort, Date begin, Date end, int[] categoryIds)
			throws ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@3.34.136.55:1521:xe", "cashflow",
				"oracle")) {
			String sql = "SELECT s.*, c.NAME FROM SPEND_LOGS s JOIN CATEGORYS c ON s.CATEGORY_ID = c.ID";
			sql += " WHERE USER_ID=? AND SPEND_AT BETWEEN ? AND ?";

			if ( categoryIds.length > 0) {
				sql += " AND CATEGORY_ID IN (";
				for (int i = 0; i < categoryIds.length; i++) {
					sql += "?";
					if (i != categoryIds.length - 1) {
						sql += ",";
					}
				}
				sql += ")";
			}
			
			switch (sort) {
			case "amt" -> sql += " ORDER BY AMT DESC, SPEND_AT DESC";
			case "spendAt" -> sql += " ORDER BY SPEND_AT DESC, NO DESC";
			}
			// System.out.println(sql);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setDate(2, begin);
			pstmt.setDate(3, end);
			if (categoryIds.length > 0) {
				int idx= 4;
				for(int v : categoryIds) {
					pstmt.setInt(idx++ , v);
				}
			}

			ResultSet rs = pstmt.executeQuery();
			List<SpendLog> list = new ArrayList<>();
			while (rs.next()) {
				// SpendLog(int no, String userId, Date spendAt, int amt, String useDesc, int
				// categoryId, Category category) {
				SpendLog log = new SpendLog(rs.getInt("no"), rs.getString("user_id"), rs.getDate("spend_at"),
						rs.getInt("amt"), rs.getString("use_desc"), rs.getInt("category_id"),
						new Category(rs.getInt("category_id"), rs.getString("name")));

				list.add(log);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public SpendLog findByNo(int no) throws ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@3.34.136.55:1521:xe", "cashflow",
				"oracle")) {
			String sql = "SELECT s.*, c.NAME FROM SPEND_LOGS s JOIN CATEGORYS c ON s.CATEGORY_ID = c.ID WHERE NO=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				// SpendLog(int no, String userId, Date spendAt, int amt, String useDesc, int
				// categoryId, Category category) {
				SpendLog log = new SpendLog(rs.getInt("no"), rs.getString("user_id"), rs.getDate("spend_at"),
						rs.getInt("amt"), rs.getString("use_desc"), rs.getInt("category_id"),
						new Category(rs.getInt("category_id"), rs.getString("name")));

				return log;
			} else {
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean deleteByNo(int no) throws Exception {
		// 1. 데이터 베이스 연결
		Class.forName("oracle.jdbc.driver.OracleDriver");
		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@3.34.136.55:1521:xe", "cashflow",
				"oracle")) {
			boolean result = false;
			// 2. 필요한 작업요청을 전송하고 응답을 받으면 됨.
			String sql = "DELETE FROM SPEND_LOGS WHERE NO=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);

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
}
