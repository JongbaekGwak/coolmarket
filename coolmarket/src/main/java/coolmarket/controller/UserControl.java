package coolmarket.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

import coolmarket.dto.CommuDto;
import coolmarket.dto.MarketDto;
import coolmarket.dto.UserDto;
import coolmarket.service.UserService;

@RestController
public class UserControl {
	@Autowired
	private UserService serv;

	@RequestMapping(value = "/overlabCheck", method = RequestMethod.GET)
	public Object overlabCheck(@RequestParam("email") String email) throws Exception {
		return serv.overlabCheck(email);
	}

	@RequestMapping(value = "/overlabCheck2", method = RequestMethod.GET)
	public Object overlabCheck2(@RequestParam("nickName") String nickName) throws Exception {
		return serv.overlabCheck2(nickName);
	}

	@RequestMapping(value = "/signUp", method = RequestMethod.POST)
	public void signUp(@RequestBody UserDto user) throws Exception {
		serv.signUp(user);
	}

	@RequestMapping(value = "/find", method = RequestMethod.POST)
	public Object find(@RequestBody UserDto user) throws Exception {
		return serv.find(user);
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Object login(@RequestParam("email") String email, @RequestParam("password") String password)
			throws Exception {
		UserDto user = serv.login(email);
		if (user == null) {
			return false;
		} else {
			if (!user.getPassword().equals(password)) {
				return false;
			} else {
				return user;
			}
		}
	}

	@RequestMapping(value = "/me", method = RequestMethod.GET)
	public Object me(@RequestParam("userNo") int userNo) throws Exception {
		return serv.me(userNo);
	}

	@RequestMapping(value = "/myList", method = RequestMethod.GET)
	public Object myList(@RequestParam("userNo") String userNo) throws Exception {
		List<MarketDto> saleList = serv.saleList(userNo);
		List<MarketDto> buyList = serv.buyList(userNo);
		List<MarketDto> wishList = serv.wishList(userNo);
		Map<String, List<MarketDto>> data = new HashMap<String, List<MarketDto>>();
		data.put("saleList", saleList);
		data.put("buyList", buyList);
		data.put("wishList", wishList);
		return data;
	}

	@RequestMapping(value = "/myCommuList", method = RequestMethod.GET)
	public Object myCommuList(@RequestParam(required = false, defaultValue = "1") int pageNo,
			@RequestParam("userNo") String userNo) throws Exception {
		PageInfo<CommuDto> myCommu = new PageInfo<CommuDto>(serv.commuList(pageNo, userNo), 5);
		return myCommu;
	}

	@RequestMapping(value = "/creaPremium", method = RequestMethod.GET)
	public boolean creaPremium(@RequestParam("userNo") String userNo) throws Exception {
		return serv.creaPremium(userNo);
	}
}
