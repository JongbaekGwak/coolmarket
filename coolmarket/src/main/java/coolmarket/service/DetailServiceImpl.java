package coolmarket.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coolmarket.dto.AdDto;
import coolmarket.dto.ComentDto;
import coolmarket.dto.CommuDto;
import coolmarket.dto.MarketDto;
import coolmarket.dto.WishDto;
import coolmarket.mapper.DetailMapper;

@Service
public class DetailServiceImpl implements DetailService {
	@Autowired
	private DetailMapper map;

//	광고
	@Override
	public AdDto adDetail(String adNo) throws Exception {
		return map.adDetail(adNo);
	}

//	마켓
	@Override
	public Map<String, Object> marketDetail(String marNo) throws Exception {
		map.marViewUp(marNo);
		MarketDto detail = map.marketDetail(marNo);
		if (detail == null) {
			return null;
		} else {
			Map<String, Object> data = new HashMap<String, Object>();
			List<MarketDto> hotMarket = map.hotMarket();
			detail.setImgList(map.marImgList(marNo));
			for (int i = 0; i < hotMarket.size(); i++) {
				String to = Integer.toString(hotMarket.get(i).getMarNo());
				hotMarket.get(i).setImgList(map.marImgList(to));
			}
			data.put("marketDetail", detail);
			data.put("hotMarket", hotMarket);

			return data;
		}
	}

	@Override
	public void wish(WishDto wish) throws Exception {
		map.wishUp(wish.getWishMarNo());
		map.wish(wish);
	}

	@Override
	public void wishCancel(WishDto wish) throws Exception {
		map.wishDown(wish.getWishMarNo());
		map.wishCancel(wish);
	}

	@Override
	public boolean myWishSearch(WishDto wish) throws Exception {
		String sear = map.myWishSearch(wish);
		if (sear == null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void marDelete(int marNo) throws Exception {
		map.marDelete(marNo);
	}

//	커뮤니티
	@Override
	public Map<String, Object> commuDetail(String comNo) throws Exception {
		map.comViewUp(comNo);
		Map<String, Object> data = new HashMap<String, Object>();
		CommuDto detail = map.commuDetail(comNo);
		detail.setImgList(map.comImgList(comNo));
		data.put("commuDetail", detail);
		return data;
	}

	@Override
	public List<ComentDto> coment(int comentComNo) throws Exception {
		return map.coment(comentComNo);
	}

	@Override
	public List<ComentDto> comentInsert(ComentDto coment) throws Exception {
		map.comentInsert(coment);
		return map.coment(coment.getComentComNo());
	}

	@Override
	public List<ComentDto> comentDel(int comentNo, int comentComNo) throws Exception {
		map.comentDel(comentNo);
		return map.coment(comentComNo);
	}

	@Override
	public void likeUp(int comNo) throws Exception {
		map.likeUp(comNo);
	}

	@Override
	public void comDelete(int comNo) throws Exception {
		map.comDelete(comNo);
	}

//	공용
	@Override
	public void decInsert(int marNo, int comNo) throws Exception {
		if (comNo == 0) {
			map.mardecInsert(marNo);
		} else {
			map.comdecInsert(comNo);
		}
	}

}
