package controlelr.menu;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.SpendLogDao;
import model.vo.SpendLog;
import model.vo.User;

@WebServlet("/private/spend/delete")
public class SpendLogDeleteController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String[] nos = req.getParameterValues("no");
		User user = (User)req.getSession().getAttribute("logonUser");
		SpendLogDao spendlogDao = new SpendLogDao();
		if(nos != null) {
			for(String no : nos) {
				int n = Integer.parseInt(no);
				try {
					SpendLog log = spendlogDao.findByNo(n);
					if(user.getId().equals(log.getUserId())) {
						spendlogDao.deleteByNo(n);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		resp.sendRedirect(req.getServletContext().getContextPath()+"/private/spend/log");
		
	}
}
