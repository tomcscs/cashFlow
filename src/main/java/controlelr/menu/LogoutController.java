package controlelr.menu;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/user/logout/handle")
public class LogoutController extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getSession().invalidate();
		
		Cookie cookie = new Cookie("ticketCode", null);
		cookie.setPath(req.getServletContext().getContextPath());
		cookie.setMaxAge(0);
		resp.addCookie(cookie);
		
		
		resp.sendRedirect(req.getServletContext().getContextPath()+"/index");
		
	}
	
}
