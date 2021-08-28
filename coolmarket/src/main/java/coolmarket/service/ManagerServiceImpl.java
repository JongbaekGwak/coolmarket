package coolmarket.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coolmarket.dto.CommuDto;
import coolmarket.dto.MarketDto;
import coolmarket.dto.PreWaitUserDto;
import coolmarket.dto.UserDto;
import coolmarket.mapper.ManagerMapper;

@Service
public class ManagerServiceImpl implements ManagerService {
	@Autowired
	private ManagerMapper map;

	@Override
	public Map<String, Object> managerDataList() throws Exception {
		Map<String, Object> dataList = new HashMap<String, Object>();
		List<MarketDto> marDec = new ArrayList<MarketDto>();
		List<CommuDto> comDec = new ArrayList<CommuDto>();
		List<PreWaitUserDto> PreWaitUser = map.PreWaitUser();
		List<UserDto> premiumUser = map.premiumUser();
		List<String> decMarNo = map.decMarNo();
		List<String> decComNo = map.decComNo();

		for (int i = 0; i < decMarNo.size(); i++) {
			marDec.add(map.marDec(decMarNo.get(i)));
		}
		for (int i = 0; i < decComNo.size(); i++) {
			comDec.add(map.comDec(decComNo.get(i)));
		}
		for (int i = 0; i < marDec.size(); i++) {
			marDec.get(i).setImgList(map.marImgList(marDec.get(i).getMarNo()));
		}
		for (int i = 0; i < comDec.size(); i++) {
			comDec.get(i).setImgList(map.comImgList(comDec.get(i).getComNo()));
		}
		dataList.put("marDec", marDec);
		dataList.put("comDec", comDec);
		dataList.put("premiumWait", PreWaitUser);
		dataList.put("premiumUser", premiumUser);
		return dataList;
	}

	@Override
	public void marAllDel() throws Exception {
		List<String> decMarNo = map.decMarNo();
		for (int i = 0; i < decMarNo.size(); i++) {
			map.marDel(decMarNo.get(i));
		}
		map.marAllDel();
	}

	@Override
	public void comAllDel() throws Exception {
		List<String> decComNo = map.decComNo();
		for (int i = 0; i < decComNo.size(); i++) {
			map.comDel(decComNo.get(i));
		}
		map.comAllDel();
	}

	@Override
	public void preAllUp(List<String> userNoList) throws Exception {
		List<String> userNo = userNoList;
		for (int i = 0; i < userNo.size(); i++) {
			map.userUp(userNo.get(i));
			map.preUp(userNo.get(i));
			map.preDt(userNo.get(i));
			map.preDt2(userNo.get(i));
		}
	}

	@Override
	public void preUp(String userNo) throws Exception {
		map.userUp(userNo);
		map.preUp(userNo);
		map.preDt(userNo);
		map.preDt2(userNo);
	}

}
