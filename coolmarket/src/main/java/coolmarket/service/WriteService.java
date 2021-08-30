package coolmarket.service;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import coolmarket.dto.AdDto;
import coolmarket.dto.CommuDto;
import coolmarket.dto.MarketDto;

public interface WriteService {
	String marketWrite(MarketDto market) throws Exception;
	
	void marketUpdate(MarketDto market) throws Exception;
	
	String commuWrite(CommuDto commu) throws Exception;
	
	String adWrite(AdDto ad) throws Exception;

	void imgInsert(String adNo, String marNo, String comNo, MultipartHttpServletRequest images) throws Exception;
}
