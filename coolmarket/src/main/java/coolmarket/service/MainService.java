package coolmarket.service;

import java.util.List;
import java.util.Map;

import coolmarket.dto.CommuDto;
import coolmarket.dto.MarketDto;

public interface MainService {
	List<String> addr1() throws Exception;

	List<String> addr2(String addr1) throws Exception;

	List<String> addr3(Map<String, String> addr) throws Exception;

	List<String> marCate() throws Exception;

	List<MarketDto> newMarketList() throws Exception;

	List<CommuDto> newCommuList() throws Exception;

}
