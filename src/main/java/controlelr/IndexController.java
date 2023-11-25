package controlelr;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/index")
public class IndexController extends HttpServlet {
 
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		if(req.getSession().getAttribute("logonUser") == null) {
			req.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(req, resp);
		}else {
			req.getRequestDispatcher("/WEB-INF/view/private/index.jsp").forward(req, resp);
		}
	}
}
