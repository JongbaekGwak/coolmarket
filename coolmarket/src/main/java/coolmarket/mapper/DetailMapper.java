package coolmarket.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import coolmarket.dto.AdComentDto;
import coolmarket.dto.AdDto;
import coolmarket.dto.ComentDto;
import coolmarket.dto.CommuDto;
import coolmarket.dto.ImgDto;
import coolmarket.dto.MarketDto;
import coolmarket.dto.WishDto;

@Mapper
public interface DetailMapper {

//	광고
	AdDto adDetail(String adNo) throws Exception;

	List<ImgDto> adImgList(String marNo) throws Exception;

	void adViewUp(String adNo) throws Exception;

	List<AdComentDto> adComent(int adComentAdNo) throws Exception;

	void adComentInsert(AdComentDto coment) throws Exception;

	void adComentDel(int adComentNo) throws Exception;

	void adDelete(int adNo) throws Exception;

//	마켓
	MarketDto marketDetail(String marNo) throws Exception;

	List<ImgDto> marImgList(String marNo) throws Exception;

	void marViewUp(String marNo) throws Exception;

	List<MarketDto> hotMarket() throws Exception;

	void wish(WishDto wish) throws Exception;

	void wishCancel(WishDto wish) throws Exception;

	void wishUp(String marNo) throws Exception;

	void wishDown(String marNo) throws Exception;

	String myWishSearch(WishDto wish) throws Exception;

	void marDelete(int marNo) throws Exception;

	void mardecInsert(int marNo) throws Exception;

	String userNo(String nickName) throws Exception;

	void setSale(@Param("marNo") String marNo, @Param("userNo") String userNo) throws Exception;

//	커뮤니티
	CommuDto commuDetail(String comNo) throws Exception;

	List<ImgDto> comImgList(String comNo) throws Exception;

	void comViewUp(String comNo) throws Exception;

	List<ComentDto> coment(int comentComNo) throws Exception;

	void comentInsert(ComentDto coment) throws Exception;

	void comentDel(int comentNo) throws Exception;

	void comdecInsert(int comNo) throws Exception;

	void likeUp(int comNo) throws Exception;

	void comDelete(int comNo) throws Exception;
}
