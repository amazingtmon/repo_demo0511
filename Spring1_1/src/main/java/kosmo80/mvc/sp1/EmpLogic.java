package kosmo80.mvc.sp1;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

/*
 * 상속이나 implements를 받으면 안된다.
 * */

public class EmpLogic {
	Logger logger = Logger.getLogger(EmpLogic.class);
	
	private EmpDao empDao = null;
	public void setEmpDao(EmpDao empDao) {
		this.empDao = empDao;
	}

	public List<Map<String,Object>> getEmpList(){
		logger.info("getEmpList 호출성공");
		List<Map<String,Object>> empList = null;
		empList = empDao.getEmpList();
		return empList;
	}
}
