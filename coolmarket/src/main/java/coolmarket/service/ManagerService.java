package coolmarket.service;

import java.util.List;
import java.util.Map;

public interface ManagerService {
	Map<String, Object> managerDataList() throws Exception;

	void marAllDel() throws Exception;

	void comAllDel() throws Exception;

	void preAllUp(List<String> userNoList) throws Exception;

	void preUp(String userNo) throws Exception;
}
