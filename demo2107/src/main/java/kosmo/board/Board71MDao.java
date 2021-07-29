package kosmo.board;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

@Service
public class Board71MDao {
	Logger logger = LogManager.getLogger(Board71MDao.class);
	private SqlSessionTemplate sqlSessionTemplate = null;
	
	public List<Map<String, Object>> getBoardList(Map<String, Object> pmap) {
		List<Map<String, Object>> boardList = null;
		//List<BoardMVO> boardList2 = null;
		boardList = sqlSessionTemplate.selectList("getBoardList",pmap);
		/*
		boardList2 = sqlSessionTemplate.selectList("getBoardMap",pmap);
		for(BoardMVO bmvo:boardList2) {
			logger.info("bmvo : "+bmvo);
			logger.info("bmvo : "+bmvo.getBm_title());
			logger.info("bmvo : "+bmvo.getBsVO().getBs_seq());
			logger.info("bmvo : "+bmvo.getBs_file());
		}
		*/
		return boardList;
	}
	
	public void hitCount(int bm_no) {
		logger.info("hitCount 호출 성공");
		sqlSessionTemplate.update("hitCount",bm_no);
	}
}
