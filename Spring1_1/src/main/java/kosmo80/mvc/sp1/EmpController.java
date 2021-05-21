package kosmo80.mvc.sp1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;


public class EmpController extends MultiActionController {
	private EmpLogic empLogic = null;
	public EmpLogic getEmpLogic() {
		return empLogic;
	}
	//setter 객채주입.
	public void setEmpLogic(EmpLogic empLogic) {
		this.empLogic = empLogic;
	}

	public ModelAndView getEmpList(HttpServletRequest req, HttpServletResponse res) {
		ModelAndView mav = new ModelAndView();
		/*
		 * <property name="prefix" value="/WEB-INF/views/" /> <property name="suffix"
		 * value=".jsp" /> /WEB-inf/views/getEmpList.jsp
		 */
		List<Map<String,Object>> empList = new ArrayList<>();
		Map<String,Object> rmap = new HashMap<>();
		rmap.put("mem_name", "이순신장군");
		empList.add(rmap);
		empList = empLogic.getEmpList();
		
		//ModelAndView는 scope속성이 request이다.
		mav.addObject("empList", empList);
		mav.setViewName("di/getEmpList");
		return mav;
	}
	
	public void getEmpList2(HttpServletRequest req, HttpServletResponse res) {
		List<Map<String,Object>> empList = new ArrayList<>();
		Map<String,Object> rmap = new HashMap<>();
		rmap.put("mem_name", "이순신장군");
		empList.add(rmap);
		empList = empLogic.getEmpList();
		req.setAttribute("empList", empList);
		try {
			res.sendRedirect("getEmpList2.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ModelAndView empInsert(HttpServletRequest req, HttpServletResponse res) {
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("di/empInsert");
		return mav;
	}
}
