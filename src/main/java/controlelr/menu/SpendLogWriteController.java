package controlelr.menu;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.CategoryDao;
import model.dao.SpendLogDao;
import model.vo.Category;
import model.vo.SpendLog;
import model.vo.User;

@WebServlet("/private/spend/write")
public class SpendLogWriteController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 작성폼을 보여줄껀데..
		Date now = new Date(System.currentTimeMillis());
		// System.out.println(now);
		req.setAttribute("now", now);
		
		CategoryDao categoryDao = new CategoryDao();
		try {
			List<Category> list =categoryDao.findAll();
			req.setAttribute("categories", list);
		}catch(Exception e) {
			e.printStackTrace();
		}
		req.getRequestDispatcher("/WEB-INF/view/private/spend/write.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 데이터 등록
		Date spendAt = Date.valueOf(req.getParameter("spendAt"));
		int categoryId = Integer.parseInt(req.getParameter("categoryId"));
		int amt = Integer.parseInt(req.getParameter("amt"));
		String useDesc = req.getParameter("useDesc");
		
		// userId는 어떻게? 세션 활용
		User user = (User)req.getSession().getAttribute("logonUser");
		String userId = user.getId();
		// SpendLog VO 에 데이터 옮겨담아서 (DAO로 넘길 VO에는 5개만 세팅시키면 됨. why?);
		SpendLog log = new SpendLog(0,userId, spendAt, amt, useDesc, categoryId, null);
		// DAO로 던져서 save 시키고 그 결과값에 따른 사후 처리
		SpendLogDao spendLogDao = new SpendLogDao();
		try {
			boolean result = spendLogDao.save(log);
			if(result)  {
				// 지출 목록보는 곳으로
				resp.sendRedirect(req.getServletContext().getContextPath()+"/private/spend/log");
			}else {
				// 그게 아니면 다시 작성할 수 있게 뷰를 재활용
				// 내용 찍어줄수 있게
				resp.sendRedirect(req.getServletContext().getContextPath()+"/private/spend/write?error=true");
			}
		}catch(Exception e) {
			e.printStackTrace();	// 에러로그는 보고
			resp.sendRedirect(req.getServletContext().getContextPath()+"/private/spend/write?error=true");
		}
		
		
	}

}
