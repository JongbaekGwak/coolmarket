package coolmarket.service;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import coolmarket.dto.AdDto;
import coolmarket.dto.CommuDto;
import coolmarket.dto.MarketDto;

public interface WriteService {
//	마켓
	String marketWrite(MarketDto market) throws Exception;
	
	void marketUpdate(MarketDto market) throws Exception;
//	커뮤
	String commuWrite(CommuDto commu) throws Exception;
	
	void commuUpdate(CommuDto commu) throws Exception;
//	광고
	String adWrite(AdDto ad) throws Exception;
	
	void adUpdate(AdDto ad) throws Exception;

	void imgInsert(String adNo, String marNo, String comNo, MultipartHttpServletRequest images) throws Exception;
}
