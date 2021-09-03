package coolmarket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import coolmarket.dto.CommuCateDto;
import coolmarket.dto.CommuDto;
import coolmarket.dto.MarCateDto;
import coolmarket.dto.MarketPagingDto;
import coolmarket.dto.SelectOptionDto;
import coolmarket.service.ListService;

@RestController
public class ListControl {
	@Autowired
	private ListService serv;

	@GetMapping("/getMarketList")
	public Object getBoardList(MarketPagingDto marketPageDto) throws Exception {
		return serv.getObjectList(marketPageDto);
	}

	@GetMapping("/getSelectBoardList")
	public Object getSelectBoardList(MarketPagingDto marketPageDto) throws Exception {
		return serv.getSelectBoardList(marketPageDto);
	}

	@GetMapping("/getCommuList")
	public Object getCommuList(MarketPagingDto marketPageDto) throws Exception {
		List<CommuDto> commuList = serv.getCommuList(marketPageDto);
		return commuList;
	}

	@GetMapping("/getMarOption")
	public Object getMarOption(String marCateName) throws Exception {
		List<MarCateDto> MarOption = serv.getMarOption(marCateName);
		return MarOption;
	}

	@GetMapping("/getCommuOption")
	public Object getCommuOption(String CommuCateName) throws Exception {
		List<CommuCateDto> CommuOption = serv.getCommuOption(CommuCateName);
		return CommuOption;
	}

	@GetMapping("/selectoption1")
	public Object getSelectOption1(String addr1) throws Exception {
		List<SelectOptionDto> SelectOption1 = serv.getSelectOption1(addr1);
		return SelectOption1;
	}

	@GetMapping("/selectoption2")
	public Object getSelectOption2(@RequestParam("addr2") String addr2) throws Exception {
		List<SelectOptionDto> SelectOption2 = serv.getSelectOption2(addr2);
		return SelectOption2;
	}

	@RequestMapping(value = "/userAddr", method = RequestMethod.GET)
	public Object userAddr(@RequestParam("userNo") int userNo) throws Exception {
		return serv.userAddr(userNo);
	}
}
