package controlelr.menu;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.CategoryDao;
import model.dao.SpendLogDao;
import model.vo.SpendLog;
import model.vo.User;

@WebServlet("/private/spend/log")
public class SpendLogController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		SpendLogDao spendLogDao = new SpendLogDao();
		CategoryDao categoryDao = new CategoryDao();
		

		String begin = req.getParameter("begin");
		String end = req.getParameter("end");
		String sort = req.getParameter("sort");
		String[] categoryIds = req.getParameterValues("categoryId"); //로그 제이에스피에서 넘어오는 데이터.
		
		User user = (User) req.getSession().getAttribute("logonUser");
		String userId = user.getId();
		try {
			sort =  (sort==null || sort.equals("")) ? "spendAt" : sort;//셀렉트 필터 값 소트로. 널인 경우 날짜를기준값으로.
			
			LocalDate now = LocalDate.now();
			
			Date endDate = (end == null || end.equals("")) ? 
									Date.valueOf(now) :	Date.valueOf(end); 
			Date beginDate = (begin == null || begin.equals(""))?
									Date.valueOf(now.minusYears(1)) : Date.valueOf(begin);
			
			categoryIds = categoryIds == null ? new String[0] : categoryIds;
			
			int[] iCategoryIds = new int[categoryIds.length];//배열은 항상 빈배열을만들어야한다. 카테고리 아이디즈만큼.
			for(int i=0; i<categoryIds.length; i++) {
				iCategoryIds[i] = Integer.parseInt(categoryIds[i]);
			}//스트링으로 넘어오는 데이터를 인트파싱해서 새배열에다 넣어준다.
			
			List<SpendLog> list = spendLogDao.findByUserIdAndConditions(userId, sort, beginDate, endDate, iCategoryIds);// 이걸 리스트로 만드는데,
			
			req.setAttribute("logs", list);//종착
			
			int total = 0;
			for (SpendLog one : list) {
				total += one.getAmt();
			}
			req.setAttribute("total", total);//총합
			
			
			req.setAttribute("cates", categoryDao.findAll());
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		req.getRequestDispatcher("/WEB-INF/view/private/spend/log.jsp").forward(req, resp);

	}

}
