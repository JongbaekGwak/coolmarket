package coolmarket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import coolmarket.common.FileUtils;
import coolmarket.dto.AdDto;
import coolmarket.dto.CommuDto;
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
	public void marketUpdate(MarketDto market) throws Exception {
		map.marketUpdate(market);
	}

	@Override
	public String commuWrite(CommuDto commu) throws Exception {
		map.commuWrite(commu);
		return map.commuWriteRe(commu.getComUserNo());
	}

	@Override
	public void commuUpdate(CommuDto commu) throws Exception {
		map.commuUpdate(commu);
	}

	@Override
	public String adWrite(AdDto ad) throws Exception {
		map.adWrite(ad);
		return map.adWriteRe(ad.getAdUserNo());
	}

	@Override
	public void adUpdate(AdDto ad) throws Exception {
		map.adUpdate(ad);
	}

	@Override
	public void imgInsert(String adNo, String marNo, String comNo, MultipartHttpServletRequest images)
			throws Exception {
		List<ImgDto> list = fileutils.parseFileInfo(adNo, marNo, comNo, images);
		if (CollectionUtils.isEmpty(list) == false) {
			map.imgInsert(list);
		}
	}

	@Override
	public List<AdDto> adImgDelete(int adNo, int imgNo) throws Exception {
		map.imgDelete(imgNo);
		return map.adImgList(adNo);
	}

	@Override
	public List<AdDto> marImgDelete(int marNo, int imgNo) throws Exception {
		map.imgDelete(imgNo);
		return map.marImgList(marNo);
	}

	@Override
	public List<AdDto> comImgDelete(int comNo, int imgNo) throws Exception {
		map.imgDelete(imgNo);
		return map.comImgList(comNo);
	}
}
