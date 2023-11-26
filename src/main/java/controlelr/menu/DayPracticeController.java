package controlelr.menu;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import model.vo.SpendLog;

public class DayPracticeController {
	String begin = req.getParameter("begin");
	String end = req.getParameter("end");
	
	LocalDate now = LocalDate.now();
	
	Date endDate = (end == null || end.equals("")) ? 
							Date.valueOf(now) :	Date.valueOf(end); 
	Date beginDate = (begin == null || begin.equals(""))?
							Date.valueOf(now.minusYears(1)) : Date.valueOf(begin);



List<SpendLog> list = spendLogDao.findByUserIdAndConditions(userId, sort, beginDate, endDate, iCategoryIds);// 이걸 리스트로 만드는데,

req.setAttribute("logs", list);//종착

int total = 0;
for (SpendLog one : list) {
	total += one.getAmt();
}
req.setAttribute("total", total);//총합

req.getRequestDispatcher("/WEB-INF/view/private/spend/log.jsp").forward(req, resp);

}