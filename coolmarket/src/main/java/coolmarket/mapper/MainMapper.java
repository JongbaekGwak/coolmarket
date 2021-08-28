package coolmarket.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import coolmarket.dto.CommuDto;
import coolmarket.dto.ImgDto;
import coolmarket.dto.MarketDto;

@Mapper
public interface MainMapper {
	List<String> addr1() throws Exception;

	List<String> addr2(String addr1) throws Exception;

	List<String> addr3(Map<String, String> addr) throws Exception;

	List<String> marCate() throws Exception;

	List<MarketDto> newMarketList() throws Exception;

	List<ImgDto> marImgList(int marNo) throws Exception;

	List<CommuDto> newCommuList() throws Exception;
	
	List<ImgDto> comImgList(int comNo) throws Exception;
}
