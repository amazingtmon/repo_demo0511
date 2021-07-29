package kosmo.board;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

@RestController
@RequestMapping("/board/*")
public class RestBoard71Controller {
	Logger logger = LogManager.getLogger(RestBoard71Controller.class);
	
	@Autowired
	private Board71Logic board71Logic = null;
	
	// json으로 내보내주기.- @RestController:String, @Controller:void, ModelAndView, String
	@RequestMapping(value="jsonGetBoardList", produces="text/plain;charset=UTF-8")
	public String jsonGetBoardList(@RequestParam Map<String, Object>pmap) throws Exception {
		logger.info("jsonGetBoardList 호출 성공");
		List<Map<String, Object>> boardList = null;
		boardList = board71Logic.getBoardList(pmap);
		Gson g = new Gson();
		String imsi = g.toJson(boardList);
		
		return imsi;
	}
}
