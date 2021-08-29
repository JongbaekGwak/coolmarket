package coolmarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import coolmarket.dto.AdComentDto;
import coolmarket.dto.ComentDto;
import coolmarket.dto.WishDto;
import coolmarket.service.DetailService;

@RestController
public class DetailControl {
	@Autowired
	private DetailService serv;

//	광고
	@RequestMapping(value = "/adDetail", method = RequestMethod.GET)
	public Object adDetail(@RequestParam("adNo") String adNo) throws Exception {
		return serv.adDetail(adNo);
	}

	@RequestMapping(value = "/adComent", method = RequestMethod.GET)
	public Object adComent(@RequestParam("adComentAdNo") int adComentAdNo) throws Exception {
		return serv.adComent(adComentAdNo);
	}

	@RequestMapping(value = "/adComentInsert", method = RequestMethod.GET)
	public Object adComentInsert(AdComentDto coment) throws Exception {
		return serv.adComentInsert(coment);
	}

	@RequestMapping(value = "/adComentDelete", method = RequestMethod.GET)
	public Object adComentDelete(@RequestParam("adComentNo") int adComentNo,
			@RequestParam("adComentAdNo") int adComentAdNo) throws Exception {
		return serv.adComentDel(adComentNo, adComentAdNo);
	}

	@RequestMapping(value = "/adDelete", method = RequestMethod.GET)
	public void adDelete(@RequestParam("adNo") int adNo) throws Exception {
		serv.adDelete(adNo);
	}

//	마켓
	@RequestMapping(value = "/marketDetail", method = RequestMethod.GET)
	public Object marketDetail(@RequestParam("marNo") String marNo) throws Exception {
		return serv.marketDetail(marNo);
	}

	@RequestMapping(value = "/wish", method = RequestMethod.GET)
	public void wish(@RequestParam("wishUserNo") String wishUserNo, @RequestParam("wishMarNo") String wishMarNo)
			throws Exception {
		WishDto wish = new WishDto();
		wish.setWishUserNo(wishUserNo);
		wish.setWishMarNo(wishMarNo);
		serv.wish(wish);
	}

	@RequestMapping(value = "/wishCancel", method = RequestMethod.GET)
	public void wishCancel(@RequestParam("wishUserNo") String wishUserNo, @RequestParam("wishMarNo") String wishMarNo)
			throws Exception {
		WishDto wish = new WishDto();
		wish.setWishUserNo(wishUserNo);
		wish.setWishMarNo(wishMarNo);
		serv.wishCancel(wish);
	}

	@RequestMapping(value = "/myWishSearch", method = RequestMethod.GET)
	public Object myWishSearch(@RequestParam("wishUserNo") String wishUserNo,
			@RequestParam("wishMarNo") String wishMarNo) throws Exception {
		WishDto wish = new WishDto();
		wish.setWishUserNo(wishUserNo);
		wish.setWishMarNo(wishMarNo);
		return serv.myWishSearch(wish);
	}

	@RequestMapping(value = "/marDelete", method = RequestMethod.GET)
	public void marDelete(@RequestParam("marNo") int marNo) throws Exception {
		serv.marDelete(marNo);
	}

	@RequestMapping(value = "/setSale", method = RequestMethod.GET)
	public Object setSale(@RequestParam("marNo") String marNo, @RequestParam("nickName") String nickName)
			throws Exception {
		return serv.setSale(marNo, nickName);
	}

//	커뮤니티
	@RequestMapping(value = "/commuDetail", method = RequestMethod.GET)
	public Object commuDetail(@RequestParam("comNo") String comNo) throws Exception {
		return serv.commuDetail(comNo);
	}

	@RequestMapping(value = "/coment", method = RequestMethod.GET)
	public Object coment(@RequestParam("comentComNo") int comentComNo) throws Exception {
		return serv.coment(comentComNo);
	}

	@RequestMapping(value = "/comentInsert", method = RequestMethod.GET)
	public Object comentInsert(ComentDto coment) throws Exception {
		return serv.comentInsert(coment);
	}

	@RequestMapping(value = "/likeUp", method = RequestMethod.GET)
	public void likeUp(@RequestParam("comNo") int comNo) throws Exception {
		serv.likeUp(comNo);
	}

	@RequestMapping(value = "/comDelete", method = RequestMethod.GET)
	public void comDelete(@RequestParam("comNo") int comNo) throws Exception {
		serv.comDelete(comNo);
	}

	@RequestMapping(value = "/comentDelete", method = RequestMethod.GET)
	public Object comentDelete(@RequestParam("comentNo") int comentNo, @RequestParam("comentComNo") int comentComNo)
			throws Exception {
		return serv.comentDel(comentNo, comentComNo);
	}

//	공용
	@RequestMapping(value = "/decInsert", method = RequestMethod.GET)
	public void decInsert(@RequestParam("marNo") int marNo, @RequestParam("comNo") int comNo) throws Exception {
		serv.decInsert(marNo, comNo);
	}

}
