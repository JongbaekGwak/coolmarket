package coolmarket.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import coolmarket.dto.AdDto;
import coolmarket.dto.CommuCateDto;
import coolmarket.dto.CommuDto;
import coolmarket.dto.ImgDto;
import coolmarket.dto.MarCateDto;
import coolmarket.dto.MarketDto;
import coolmarket.dto.MarketPagingDto;
import coolmarket.dto.SelectOptionDto;
import coolmarket.dto.UserDto;

@Mapper
public interface ListMapper {
	List<MarCateDto> getMarOption(String marCateName) throws Exception;

	List<CommuCateDto> getCommuOption(String CommuCateName) throws Exception;

	List<SelectOptionDto> getSelectOption1(String addr1) throws Exception;

	List<SelectOptionDto> getSelectOption2(String addr2) throws Exception;

	List<MarketDto> getMarBoardList(MarketPagingDto marketPageDto) throws Exception;

	List<AdDto> getAdBoardList(MarketPagingDto marketPageDto) throws Exception;

	List<MarketDto> getSelectMarBoardList(MarketPagingDto marketPageDto) throws Exception;

	List<AdDto> getSelectAdBoardList(MarketPagingDto marketPageDto) throws Exception;

	List<CommuDto> getCommuList(MarketPagingDto marketPageDto) throws Exception;

	List<ImgDto> adImgList(int adNo) throws Exception;

	List<ImgDto> marImgList(int marNo) throws Exception;

	List<ImgDto> comImgList(int comNo) throws Exception;

	UserDto userAddr(int userNo) throws Exception;

	int getMarTalkCnt(int marNo) throws Exception;

}
