package filter;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter({ "/private/*" })
public class AccessFilter extends HttpFilter {

	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpSession session = request.getSession();
		if (session.getAttribute("logonUser") == null) {

			response.sendRedirect(request.getServletContext().getContextPath() + "/user/login");
		} else {
			chain.doFilter(request, response); // 그 다음 필터를 작동 시킴.
		}

	}
}
