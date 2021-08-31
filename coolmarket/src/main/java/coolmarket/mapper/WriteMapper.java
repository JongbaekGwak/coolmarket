package coolmarket.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import coolmarket.dto.AdDto;
import coolmarket.dto.CommuDto;
import coolmarket.dto.ImgDto;
import coolmarket.dto.MarketDto;

@Mapper
public interface WriteMapper {
	
//	마켓
	void marketWrite(MarketDto market) throws Exception;

	String marketWriteRe(int marUserNo) throws Exception;
	
	void marketUpdate(MarketDto market) throws Exception;
	
//	커뮤
	void commuWrite(CommuDto commu) throws Exception;
	
	String commuWriteRe(int comUserNo) throws Exception;
	
	void commuUpdate(CommuDto commu) throws Exception;
	
//	광고
	void adWrite(AdDto ad) throws Exception;
	
	String adWriteRe(int adUserNo) throws Exception;
	
	void adUpdate(AdDto ad) throws Exception;

	void imgInsert(List<ImgDto> list) throws Exception;
}
