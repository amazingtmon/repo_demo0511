package kosmo.board;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.vo.UserVO;

@Controller
@RequestMapping("/board/*")
public class Board71Controller {
	Logger logger = LogManager.getLogger(Board71Controller.class);
	/* 
	 * @Autowired 생성자, 메소드, 멤버변수 사용가능 
	 * : NoSuchBeanDefinitionException을 발생시킬 수 있음. 
	 * */
	@Autowired
	private Board71Logic board71Logic = null;
		
		@GetMapping(value="login.do")
		public String loginView(@ModelAttribute("user") UserVO vo) {
			return "redirect:login.jsp";
		}
		
		public String getBoardList(Model model, @RequestParam Map<String,Object> pmap) {
			logger.info("getBoardList 호출 성공");
			List<Map<String, Object>> boardList = null;
			boardList = board71Logic.getBoardList(pmap);
			//boardLogic.getBoardList(target); where bm_no=? and bm_title LIKE '%'||?||'%'
			model.addAttribute("boardList", boardList);
			logger.info("boardList: "+boardList);
			return "board/getBoardList";
		}
		
		public ModelAndView getBoardDetail(Model model, @RequestParam Map<String,Object> pmap) {
			logger.info("getBoardList 호출 성공");
			pmap.put("gubun", "detail");
			logger.info("bm_no : "+pmap.get("bm_no"));
			List<Map<String, Object>> boardDetail= null;
			boardDetail = board71Logic.getBoardList(pmap);
			//boardLogic.getBoardList(target); where bm_no=? and bm_title LIKE '%'||?||'%'
			model.addAttribute("boardDetail", boardDetail);
			logger.info("boardDetail: "+boardDetail);

			return "board/read";
		}
		
		// json으로 내보내주기.- @RestController:String, @Controller:void, ModelAndView, String
		public void jsonGetBoardList(HttpServletRequest req, HttpServletResponse res) throws Exception {
			logger.info("jsonGetBoardList 호출 성공");
			List<Map<String, Object>> boardList = null;
			boardList = board71Logic.getBoardList(null);
			Gson g = new Gson();
			String imsi = g.toJson(boardList);
			res.setContentType("application/json;charset=utf-8");//mime type 결정.
			PrintWriter out = res.getWriter();
			out.print(imsi);
		}
		
		
		public void boardUpdate(HttpServletRequest req, HttpServletResponse res) 
				throws Exception
		{
			logger.info("boardUpdate 호출 성공");
			HashMapBinder hmb = new HashMapBinder(req);
			Map<String, Object> pmap = new HashMap<>();
			hmb.bindPost(pmap);
			int result = 0;
			result = board71Logic.boardUpdate(pmap);
			if(result == 1) {
				res.sendRedirect("./getBoardList.sp4");
			} else {
				res.sendRedirect("./getFail.sp4");
			}
		}
		
		public ModelAndView updateForm(HttpServletRequest req, HttpServletResponse res) 
				throws Exception
		{
			logger.info("updateForm 호출 성공");
			HashMapBinder hmb = new HashMapBinder(req);
			Map<String,Object> target = new HashMap<>();
			hmb.bindPost(target);
			logger.info("bm_no : "+target.get("bm_no"));
			ModelAndView mav = new ModelAndView();
			mav.setViewName("board/updateForm");
			mav.addObject("target", target);
			return mav;
		}	

		public void boardInsert(HttpServletRequest req, HttpServletResponse res) throws Exception {
			logger.info("boardInsert 호출 성공");
			HashMapBinder hmb = new HashMapBinder(req);
			Map<String, Object> pmap = new HashMap<>();
			hmb.multiBind(pmap);
			int result = 0;
			result = board71Logic.boardInsert(pmap);
			if(result == 1) {
				res.sendRedirect("./getBoardList.sp4");
			} else {
				res.sendRedirect("./getFail.sp4");
			}
		}
		
		public void boardDelete(HttpServletRequest req, HttpServletResponse res) throws Exception {
			logger.info("boardDelete 호출 성공");
			HashMapBinder hmb = new HashMapBinder(req);
			Map<String, Object> pmap = new HashMap<>();
			hmb.bindPost(pmap);
			int result = 0;
			result = board71Logic.boardDelete(pmap);
			if(result == 1) {
				res.sendRedirect("./getBoardList.sp4");
			} else {
				res.sendRedirect("./getFail.sp4");
			}
		}
}
