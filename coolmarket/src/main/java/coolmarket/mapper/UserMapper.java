package coolmarket.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.github.pagehelper.Page;

import coolmarket.dto.CommuDto;
import coolmarket.dto.ImgDto;
import coolmarket.dto.MarketDto;
import coolmarket.dto.UserDto;

@Mapper
public interface UserMapper {
	String overlabCheck(String email) throws Exception;

	String overlabCheck2(String nickName) throws Exception;

	void signUp(UserDto user) throws Exception;

	String find(UserDto user) throws Exception;

	UserDto login(String email) throws Exception;

	UserDto me(int userNo) throws Exception;

	List<MarketDto> saleList(String userNo) throws Exception;

	List<MarketDto> buyList(String userNo) throws Exception;

	List<String> userWish(String userNo) throws Exception;

	MarketDto wishList(String no) throws Exception;

	Page<CommuDto> commuList(String userNo) throws Exception;

	void creaPremium(String userNo) throws Exception;

	String searchPremium(String userNo) throws Exception;

	List<ImgDto> marImgList(int marNo) throws Exception;

	List<ImgDto> comImgList(int comNo) throws Exception;
}
