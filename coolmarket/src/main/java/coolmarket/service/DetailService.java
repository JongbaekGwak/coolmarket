package coolmarket.service;

import java.util.List;
import java.util.Map;

import coolmarket.dto.AdComentDto;
import coolmarket.dto.AdDto;
import coolmarket.dto.ComentDto;
import coolmarket.dto.WishDto;

public interface DetailService {
//	광고
	AdDto adDetail(String adNo) throws Exception;

	List<AdComentDto> adComent(int adComentAdNo) throws Exception;

	List<AdComentDto> adComentInsert(AdComentDto coment) throws Exception;

	List<AdComentDto> adComentDel(int adComentNo, int adComentAdNo) throws Exception;

	void adDelete(int adNo) throws Exception;

	boolean adLike(int adNo, int userNo) throws Exception;

	void adLikeUp(int adNo, int userNo) throws Exception;

	void adLikeDown(int adNo, int userNo) throws Exception;

//	마켓
	Map<String, Object> marketDetail(String marNo) throws Exception;

	void wish(WishDto wish) throws Exception;

	void wishCancel(WishDto wish) throws Exception;

	boolean myWishSearch(WishDto wish) throws Exception;

	void marDelete(int marNo) throws Exception;

	boolean setSale(String marNo, String nickName) throws Exception;

//	커뮤니티
	Map<String, Object> commuDetail(String comNo) throws Exception;

	List<ComentDto> coment(int comentComNo) throws Exception;

	List<ComentDto> comentInsert(ComentDto coment) throws Exception;

	List<ComentDto> comentDel(int comentNo, int comentComNo) throws Exception;

	void likeUp(int comNo) throws Exception;

	void comDelete(int comNo) throws Exception;

//	공용
	void decInsert(int marNo, int comNo) throws Exception;
}
