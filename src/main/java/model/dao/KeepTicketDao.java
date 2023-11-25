package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.vo.KeepTicket;
import model.vo.User;

public class KeepTicketDao {
	public boolean save(KeepTicket ticket) throws ClassNotFoundException {
		// 1. 데이터 베이스 연결
		Class.forName("oracle.jdbc.driver.OracleDriver");
		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@3.34.136.55:1521:xe", "cashflow",
				"oracle")) {
			boolean result = false;
			// 2. 필요한 작업요청을 전송하고 응답을 받으면 됨.
			String sql = "INSERT INTO KEEP_TICKETS VALUES(?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ticket.getCode());
			pstmt.setString(2, ticket.getUserId());
			pstmt.setDate(3, ticket.getExpiredAt());

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

	public KeepTicket findByCode(String ticketCode) throws ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@3.34.136.55:1521:xe", "cashflow",
				"oracle")) {
			String sql = "SELECT * FROM KEEP_TICKETS WHERE CODE=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ticketCode);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				KeepTicket ticket = new KeepTicket();
				ticket.setCode(rs.getString("code"));
				ticket.setUserId(rs.getString("user_id"));
				ticket.setExpiredAt(rs.getDate("expired_at"));
				return ticket;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
