package kosmo.board;

import java.io.File;
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
	private Board71MDao board71MDao = null;
	@Autowired
	private Board71SDao board71SDao = null;
	
	
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
			board71MDao.hitCount(bm_no);
		}
		boardList = board71MDao.getBoardList(pmap);
		return boardList;
	}


	public int boardUpdate(Map<String, Object> pmap) {
		logger.info("boardUpdate 호출 성공");
		int result = 0;
		
		result = board71MDao.boardMUpdate(pmap);
		return result;
	}


	public int boardInsert(Map<String, Object> pmap) {
		logger.info("boardInsert 호출 성공");
		int result = 0;
		int bm_no = 0;
		bm_no = board71MDao.getBmNo();
		pmap.put("bm_no", bm_no);
		int bm_group = 0;
		if(pmap.get("bm_group")!=null) {//read.jsp눌렀다
			bm_group = Integer.parseInt(pmap.get("bm_group").toString());
		}
		//댓글이야?
		if(bm_group > 0) {
			board71MDao.bmStepUpdate(pmap);//조건에 맞지 않으면 처리가 생략될 수 있다.
			pmap.put("bm_pos", Integer.parseInt(pmap.get("bm_pos").toString())+1);
			pmap.put("bm_step", Integer.parseInt(pmap.get("bm_step").toString())+1);
		}
		//너 새글이구나
		else {
			bm_group = board71MDao.getBmGroup();
			pmap.put("bm_group", bm_group);
			pmap.put("bm_pos",0);
			pmap.put("bm_step",0);
		}
		//첨부파일이 있어?
		if((pmap.get("bs_file")!=null)&&((pmap.get("bs_file").toString().length()) > 0)) {
			logger.info("첨부파일 처리 로직 경유");
			pmap.put("bm_seq", 1);
			board71SDao.boardSInsert(pmap);			
		}
		board71MDao.boardMInsert(pmap);
		logger.info("pmap:"+pmap);
		result = 1;
		return result;
	}


	public int boardDelete(Map<String, Object> pmap) {
		logger.info("boardDelete 호출 성공");
		int result = 0;
		try {
			result = board71MDao.boardMDelte(pmap);
			if(result == 1) {
				String filePath = "D:\\cosmo_ysc_80th\\spring4_1_1\\WebContent\\pds\\";
				String fileName = pmap.get("bs_file").toString();
				String fullName = filePath+fileName;
				//실제로 존재하는 파일이름을 객체로 생성해주는 클래스
				File file = new File(fullName);
				if(file !=null) {
					if(file.exists()) {
						boolean isOk = file.delete();
						logger.info(isOk);
						pmap.put("bs_sep", 1);
						board71SDao.boardSDelete(pmap);
					}
				}
			}
		}catch(Exception e) {
			logger.info("Exception: "+e.toString());
		}
		return result;
	}
}
