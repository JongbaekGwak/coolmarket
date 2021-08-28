package coolmarket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import coolmarket.common.FileUtils;
import coolmarket.dto.ImgDto;
import coolmarket.dto.MarketDto;
import coolmarket.mapper.WriteMapper;

@Service
public class WriteServiceImpl implements WriteService {
	@Autowired
	private WriteMapper map;

	@Autowired
	private FileUtils fileutils;

	@Override
	public String marketWrite(MarketDto market) throws Exception {
		map.marketWrite(market);
		return map.marketWriteRe(market.getMarUserNo());
	}

	@Override
	public void imgInsert(String marNo, String comNo, MultipartHttpServletRequest images) throws Exception {
		List<ImgDto> list = fileutils.parseFileInfo(marNo, comNo, images);
		if (CollectionUtils.isEmpty(list) == false) {
			map.imgInsert(list);
		}
	}
}
