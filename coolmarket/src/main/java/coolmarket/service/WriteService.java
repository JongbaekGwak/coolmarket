package coolmarket.service;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import coolmarket.dto.MarketDto;

public interface WriteService {
	String marketWrite(MarketDto market) throws Exception;

	void imgInsert(String adNo, String marNo, String comNo, MultipartHttpServletRequest images) throws Exception;
}
