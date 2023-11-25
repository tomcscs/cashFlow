package controlelr.menu;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.AvatarDao;
import model.dao.UserDao;
import model.vo.Avatar;
import model.vo.User;

@WebServlet("/user/join/handle")
public class JoinHandleController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String id = req.getParameter("id");
			String password = req.getParameter("password");
			String nickname = req.getParameter("nickname");
			String gender = req.getParameter("gender");
			String avatarId = req.getParameter("avatarId");
			int birth = Integer.parseInt(req.getParameter("birth"));
			
			if (id == null || password == null || nickname == null || gender == null || avatarId == null) {
				// 파라미터 유효성 검사
			}

			User one = new User(id, password, birth, gender, nickname, avatarId);

			UserDao userDao = new UserDao();
			User found = userDao.findById(id);
			if (found == null) {	// 이 아이디로 등록된 유저가 없다는 상황
				userDao.save(one);
				req.setAttribute("result", 1);
				req.getRequestDispatcher("/WEB-INF/view/user/join_result.jsp").forward(req, resp);
			} else {	// 이미 이 아이디가 있다면..?
				req.setAttribute("error", true);
				req.setAttribute("tempUser", one);
//				
				AvatarDao avatarDao = new AvatarDao();
				List<Avatar> avatars = avatarDao.findAll();
				req.setAttribute("avatars", avatars);
				req.getRequestDispatcher("/WEB-INF/view/user/join_form.jsp").forward(req, resp);
			}

		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("result", -1);
			req.getRequestDispatcher("/WEB-INF/view/user/join_result.jsp").forward(req, resp);
		}

	}

}
