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
		
		String[] nos = req.getParameterValues("no");//체크했던데이터가 넘어온거 파싱으로.
		User user = (User)req.getSession().getAttribute("logonUser");
		SpendLogDao spendlogDao = new SpendLogDao();//딜리트 메서드를 이용하기 위해서.
		if(nos != null) {
			for(String no : nos) {
				int n = Integer.parseInt(no);
				try {
					SpendLog log = spendlogDao.findByNo(n);
					if(user.getId().equals(log.getUserId())) {//보안을 위해서 정말 아이디 사용자가 삭제하려는게 맞는지 보기 위함이다.
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
