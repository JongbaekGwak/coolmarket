package coolmarket.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coolmarket.dto.CommuDto;
import coolmarket.dto.ImgDto;
import coolmarket.dto.MarketDto;
import coolmarket.mapper.MainMapper;

@Service
public class MainServiceImpl implements MainService {
	@Autowired
	private MainMapper map;

	@Override
	public List<String> addr1() throws Exception {
		List<String> addr = map.addr1();
		List<String> addr1 = addr.stream().distinct().collect(Collectors.toList());
		return addr1;
	}

	@Override
	public List<String> addr2(String addr1) throws Exception {
		return map.addr2(addr1);
	}

	@Override
	public List<String> addr3(Map<String, String> addr) throws Exception {
		return map.addr3(addr);
	}

	@Override
	public List<String> marCate() throws Exception {
		return map.marCate();
	}

	@Override
	public List<MarketDto> newMarketList() throws Exception {
		List<MarketDto> data = map.newMarketList();
		for (int i = 0; i < data.size(); i++) {
			List<ImgDto> imgList = map.marImgList(data.get(i).getMarNo());
			data.get(i).setImgList(imgList);
		}
		return data;
	}

	@Override
	public List<CommuDto> newCommuList() throws Exception {
		List<CommuDto> data = map.newCommuList();
		for (int i = 0; i < data.size(); i++) {
			List<ImgDto> imgList = map.comImgList(data.get(i).getComNo());
			data.get(i).setImgList(imgList);
		}
		return data;
	}

}
