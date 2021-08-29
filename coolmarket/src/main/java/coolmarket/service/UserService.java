package coolmarket.service;

import java.util.List;

import com.github.pagehelper.Page;

import coolmarket.dto.AdDto;
import coolmarket.dto.CommuDto;
import coolmarket.dto.MarketDto;
import coolmarket.dto.UserDto;

public interface UserService {
	boolean overlabCheck(String email) throws Exception;

	boolean overlabCheck2(String nickName) throws Exception;

	void signUp(UserDto user) throws Exception;

	String find(UserDto user) throws Exception;

	UserDto login(String email) throws Exception;

	UserDto me(int userNo) throws Exception;

	List<MarketDto> saleList(String userNo) throws Exception;

	List<MarketDto> buyList(String userNo) throws Exception;

	List<MarketDto> wishList(String userNo) throws Exception;

	Page<CommuDto> commuList(int pageNo, String userNo) throws Exception;

	boolean creaPremium(String userNo) throws Exception;

	List<AdDto> myAdList(int adUserNo) throws Exception;
}
