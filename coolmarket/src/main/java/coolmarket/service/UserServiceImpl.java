package coolmarket.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import coolmarket.dto.AdDto;
import coolmarket.dto.CommuDto;
import coolmarket.dto.MarketDto;
import coolmarket.dto.UserDto;
import coolmarket.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper map;

	@Override
	public boolean overlabCheck(String email) throws Exception {
		String user = map.overlabCheck(email);
		if (user == null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean overlabCheck2(String nickName) throws Exception {
		String user = map.overlabCheck2(nickName);
		if (user == null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void signUp(UserDto user) throws Exception {
		map.signUp(user);
	}

	@Override
	public String find(UserDto user) throws Exception {
		return map.find(user);
	}

	@Override
	public UserDto login(String email) throws Exception {
		return map.login(email);
	}

	@Override
	public UserDto me(int userNo) throws Exception {
		return map.me(userNo);
	}

	@Override
	public List<MarketDto> saleList(String userNo) throws Exception {
		List<MarketDto> saleData = map.saleList(userNo);
		for (int i = 0; i < saleData.size(); i++) {
			saleData.get(i).setImgList(map.marImgList(saleData.get(i).getMarNo()));
		}
		return saleData;
	}

	@Override
	public List<MarketDto> buyList(String userNo) throws Exception {
		List<MarketDto> buyData = map.buyList(userNo);
		for (int i = 0; i < buyData.size(); i++) {
			buyData.get(i).setImgList(map.marImgList(buyData.get(i).getMarNo()));
		}
		return buyData;
	}

	@Override
	public List<MarketDto> wishList(String userNo) throws Exception {
		List<String> no = map.userWish(userNo);
		List<MarketDto> wishList = new ArrayList<MarketDto>();
		for (int i = 0; i < no.size(); i++) {
			wishList.add(map.wishList(no.get(i)));
		}
		for (int i = 0; i < wishList.size(); i++) {
			wishList.get(i).setImgList(map.marImgList(wishList.get(i).getMarBuyUserNo()));
		}
		return wishList;
	}

	@Override
	public Page<CommuDto> commuList(int pageNo, String userNo) throws Exception {
		PageHelper.startPage(pageNo, 5);
		Page<CommuDto> comData = map.commuList(userNo);
		for (int i = 0; i < comData.size(); i++) {
			comData.get(i).setImgList(map.comImgList(comData.get(i).getComNo()));
		}
		return comData;
	}

	@Override
	public boolean creaPremium(String userNo) throws Exception {
		String yn = map.searchPremium(userNo);
		if (yn.equals(null)) {
			map.creaPremium(userNo);
			return true;
		} else {
			return false;
		}

	}

	@Override
	public List<AdDto> myAdList(int adUserNo) throws Exception {
		List<AdDto> adList = map.myAdList(adUserNo);
		if (adList == null) {
			return null;
		} else {
			for (int i = 0; i < adList.size(); i++) {
				adList.get(i).setImgList(map.adImgList(adList.get(i).getAdNo()));
			}
			return adList;
		}
	}
}
