package coolmarket.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import coolmarket.dto.CommuDto;
import coolmarket.dto.ImgDto;
import coolmarket.dto.MarketDto;
import coolmarket.dto.PreWaitUserDto;
import coolmarket.dto.UserDto;

@Mapper
public interface ManagerMapper {
	List<String> decMarNo() throws Exception;

	List<String> decComNo() throws Exception;

	MarketDto marDec(String marNo) throws Exception;

	CommuDto comDec(String comNo) throws Exception;

	void marAllDel() throws Exception;

	void marDel(String marNo) throws Exception;

	void comAllDel() throws Exception;

	void comDel(String comNo) throws Exception;

	List<PreWaitUserDto> PreWaitUser() throws Exception;

	void userUp(int userNo) throws Exception;

	void preUp(int userNo) throws Exception;

	void preDt(int userNo) throws Exception;

	void preDt2(int userNo) throws Exception;

	List<UserDto> premiumUser() throws Exception;

	List<ImgDto> marImgList(int marNo) throws Exception;

	List<ImgDto> comImgList(int comNo) throws Exception;
}
