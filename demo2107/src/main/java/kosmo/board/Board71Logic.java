package kosmo.board;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class Board71Logic {
	Logger logger = LogManager.getLogger(Board71Logic.class);
	@Autowired
	private Board71MDao boardMDao = null;
	@Autowired
	private Board71SDao boardSDao = null;
	
	
	public List<Map<String, Object>> getBoardList(Map<String, Object> pmap) {
		logger.info("getBoardList 호출 성공"+pmap.containsKey("gubun"));
		List<Map<String,Object>> boardList = null;
		String gubun = null;
		if(pmap.get("gubun")!=null) {
			gubun = pmap.get("gubun").toString();			
		}
		if(gubun!=null && "detail".equals(gubun)) {
			int bm_no = 0;
			bm_no = Integer.parseInt(pmap.get("bm_no").toString());
			boardMDao.hitCount(bm_no);
		}
		boardList = boardMDao.getBoardList(pmap);
		return boardList;
	}
}
