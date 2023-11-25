package model.vo;

import java.sql.Date;

public class KeepTicket {
	private String code;
	private String userId;
	private Date expiredAt;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getExpiredAt() {
		return expiredAt;
	}

	public void setExpiredAt(Date expiredAt) {
		this.expiredAt = expiredAt;
	}

	public KeepTicket(String code, String userId, Date expiredAt) {
		super();
		this.code = code;
		this.userId = userId;
		this.expiredAt = expiredAt;
	}

	public KeepTicket() {
		super();
	}

}
