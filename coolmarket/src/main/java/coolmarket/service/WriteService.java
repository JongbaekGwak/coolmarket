package coolmarket.service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import coolmarket.dto.AdDto;
import coolmarket.dto.CommuDto;
import coolmarket.dto.MarketDto;

public interface WriteService {
//	마켓
	String marketWrite(MarketDto market) throws Exception;

	void marketUpdate(MarketDto market) throws Exception;

	List<AdDto> marImgDelete(int marNo, int imgNo) throws Exception;

//	커뮤
	String commuWrite(CommuDto commu) throws Exception;

	void commuUpdate(CommuDto commu) throws Exception;
	
	List<AdDto> comImgDelete(int comNo, int imgNo) throws Exception;

//	광고
	String adWrite(AdDto ad) throws Exception;

	void adUpdate(AdDto ad) throws Exception;

	void imgInsert(String adNo, String marNo, String comNo, MultipartHttpServletRequest images) throws Exception;

	List<AdDto> adImgDelete(int adNo, int imgNo) throws Exception;

}
