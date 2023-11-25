package controlelr.menu;

import java.io.IOException;
import java.sql.Date;
import java.util.UUID;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.AvatarDao;
import model.dao.KeepTicketDao;
import model.dao.UserDao;
import model.vo.Avatar;
import model.vo.KeepTicket;
import model.vo.User;

@WebServlet("/user/login/handle")
public class LoginHandleController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String loginId = req.getParameter("loginId");
		String loginPassword = req.getParameter("loginPassword");
		String keep = req.getParameter("keep");

		try {
			UserDao userDao = new UserDao();
			User foundUser = userDao.findUserWithAvatarById(loginId);
			if (foundUser != null && foundUser.getPassword().equals(loginPassword)) {
				req.getSession().setAttribute("logonUser", foundUser);

//				AvatarDao avatarDao = new AvatarDao();
//				Avatar foundAvatar = avatarDao.findById(foundUser.getAvatarId());
//
//				req.getSession().setAttribute("logonUserAvatar", foundAvatar);
				
				// 사용자가 체크박스를 선택해서 요청을 보낸 경우..
				if (keep != null) {
					// 랜덤 코드 만들어서
					String code = UUID.randomUUID().toString();
					String userId = loginId;
					Date expiredAt = new Date(System.currentTimeMillis() + 1000L*60*60*24*30 );
					KeepTicket ticket = new KeepTicket(code, userId, expiredAt);
					
					// 디비에 저장하고,
					KeepTicketDao keepTicketDao = new KeepTicketDao();
					keepTicketDao.save(ticket);

					// 그 코드값을 담은 쿠키를 생성 및 설정하고
					Cookie cookie = new Cookie("ticketCode", code);
					cookie.setPath(req.getServletContext().getContextPath());
					cookie.setMaxAge(60*60*24*30);
					
					// 쿠키를 전송
					resp.addCookie(cookie);
					
				}

				
				resp.sendRedirect(req.getServletContext().getContextPath() + "/index");
			} else {
				req.setAttribute("error", true);
				req.getRequestDispatcher("/WEB-INF/view/user/login_form.jsp").forward(req, resp);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
