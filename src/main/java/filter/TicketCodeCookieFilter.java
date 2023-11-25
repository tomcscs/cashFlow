package filter;

import java.io.IOException;
import java.sql.Date;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.AvatarDao;
import model.dao.KeepTicketDao;
import model.dao.UserDao;
import model.vo.Avatar;
import model.vo.KeepTicket;
import model.vo.User;

@WebFilter("/*")
public class TicketCodeCookieFilter extends HttpFilter {
	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 쿠키가 있다면 전처리 후
		// 이 요청에 ticketCode 쿠키가 있는지를 확인.
		
		// 100% 통과를 시키는 필터
		chain.doFilter(request, response);
	}
}
