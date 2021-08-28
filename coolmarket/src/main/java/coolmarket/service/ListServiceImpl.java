package coolmarket.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coolmarket.dto.AdDto;
import coolmarket.dto.CommuCateDto;
import coolmarket.dto.CommuDto;
import coolmarket.dto.MarCateDto;
import coolmarket.dto.MarketDto;
import coolmarket.dto.MarketPagingDto;
import coolmarket.dto.SelectOptionDto;
import coolmarket.mapper.ListMapper;

@Service
public class ListServiceImpl implements ListService {
	@Autowired
	private ListMapper map;

	@Override
	public List<Object> getObjectList(MarketPagingDto marketPageDto) throws Exception {
		List<AdDto> adList = map.getAdBoardList(marketPageDto);
		List<MarketDto> mdList = map.getMarBoardList(marketPageDto);
		List<Object> dataList = new ArrayList<Object>();
		int count = 0;
		for (int i = 0; i < mdList.size(); i++) {
			if (i % 3 == 0) {
				if (count < adList.size()) {
					dataList.add(adList.get(count));
					count++;
				}
			}
			dataList.add(mdList.get(i));
		}
		return dataList;
	}

	@Override
	public List<Object> getSelectBoardList(MarketPagingDto marketPageDto) throws Exception {
		List<AdDto> adList = map.getSelectAdBoardList(marketPageDto);
		List<MarketDto> smdList = map.getSelectMarBoardList(marketPageDto);
		List<Object> sdataList = new ArrayList<Object>();
		int count = 0;
		for (int i = 0; i < smdList.size(); i++) {
			if (i % 3 == 0) {
				if (count < adList.size()) {
					sdataList.add(adList.get(count));
					count++;
				}
			}
			sdataList.add(smdList.get(i));
		}
		return sdataList;
	}

	@Override
	public List<CommuDto> getCommuList(MarketPagingDto marketPageDto) throws Exception {
		return map.getCommuList(marketPageDto);
	}

	@Override
	public List<MarCateDto> getMarOption(String marCateName) throws Exception {
		return map.getMarOption(marCateName);
	}

	@Override
	public List<CommuCateDto> getCommuOption(String CommuCateName) throws Exception {
		return map.getCommuOption(CommuCateName);
	}

	@Override
	public List<SelectOptionDto> getSelectOption1(String addr1) throws Exception {
		return map.getSelectOption1(addr1);
	}

	@Override
	public List<SelectOptionDto> getSelectOption2(String addr2) throws Exception {
		return map.getSelectOption2(addr2);
	}
}
