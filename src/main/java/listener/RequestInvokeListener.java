package listener;

import java.sql.Date;

import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import model.dao.KeepTicketDao;
import model.dao.UserDao;
import model.vo.KeepTicket;
import model.vo.User;


@WebListener
public class RequestInvokeListener implements ServletRequestListener {
	
	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		HttpServletRequest req=(HttpServletRequest)sre.getServletRequest();
		Cookie found = null;
		Cookie[] cookies = req.getCookies();
		if (cookies != null && cookies.length > 0) {
			for (Cookie one : cookies) {
				if (one.getName().equals("ticketCode")) {
					found = one;
					break;
				}
			}
		}
		// ========================================================
		// 있으면, 값 찾아서 유저 찾고 세션에 올려서 통과
		if (found != null || req.getSession().getAttribute("logonUser") == null) {
			String code = found.getValue();
			KeepTicketDao keepTicketDao = new KeepTicketDao();
			try {
				KeepTicket foundTicket = keepTicketDao.findByCode(code);
				Date now = new Date(System.currentTimeMillis());
				
				if(foundTicket != null && foundTicket.getExpiredAt().after(now)) {
					String userId = foundTicket.getUserId();
					UserDao userDao = new UserDao();
					User foundUser = userDao.findUserWithAvatarById(userId);
					req.getSession().setAttribute("logonUser", foundUser);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
