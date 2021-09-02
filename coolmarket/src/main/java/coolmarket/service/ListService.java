package coolmarket.service;

import java.util.List;

import coolmarket.dto.CommuCateDto;
import coolmarket.dto.CommuDto;
import coolmarket.dto.MarCateDto;
import coolmarket.dto.MarketPagingDto;
import coolmarket.dto.SelectOptionDto;

public interface ListService {
	List<Object> getObjectList(MarketPagingDto marketPageDto) throws Exception;

	List<Object> getSelectBoardList(MarketPagingDto marketPageDto) throws Exception;

	List<CommuDto> getCommuList(MarketPagingDto marketPageDto) throws Exception;

	List<MarCateDto> getMarOption(String marCateName) throws Exception;

	List<CommuCateDto> getCommuOption(String CommuCateName) throws Exception;

	List<SelectOptionDto> getSelectOption1(String addr1) throws Exception;

	List<SelectOptionDto> getSelectOption2(String addr2) throws Exception;

}
