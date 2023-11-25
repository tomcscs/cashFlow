package controlelr.menu;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.AvatarDao;
import model.vo.Avatar;

@WebServlet("/user/join")
public class JoinController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		AvatarDao avatarDao = new AvatarDao();

		try {
			List<Avatar> avatars = avatarDao.findAll();
			req.setAttribute("avatars", avatars);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		req.getRequestDispatcher("/WEB-INF/view/user/join_form.jsp").forward(req, resp);
	}

}
