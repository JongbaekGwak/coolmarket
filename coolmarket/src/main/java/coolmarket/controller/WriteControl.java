package coolmarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import coolmarket.dto.AdDto;
import coolmarket.dto.CommuDto;
import coolmarket.dto.MarketDto;
import coolmarket.service.WriteService;

@RestController
public class WriteControl {
	@Autowired
	private WriteService serv;

//	마켓
	@RequestMapping(value = "/marketWrite", method = RequestMethod.GET)
	public Object marketWrite(MarketDto market) throws Exception {
		return serv.marketWrite(market);
	}

	@RequestMapping(value = "/marketUpdate", method = RequestMethod.PUT)
	public void marketUpdate(@RequestBody MarketDto market) throws Exception {
		serv.marketUpdate(market);
	}

	@RequestMapping(value = "/marImgDelete", method = RequestMethod.GET)
	public Object marImgDelete(@RequestParam("marNo") int marNo, @RequestParam("imgNo") int imgNo) throws Exception {
		return serv.marImgDelete(marNo, imgNo);
	}

//	커뮤
	@RequestMapping(value = "/commuWrite", method = RequestMethod.GET)
	public Object marketWrite(CommuDto commu) throws Exception {
		return serv.commuWrite(commu);
	}

	@RequestMapping(value = "/commuUpdate", method = RequestMethod.PUT)
	public void commuUpdate(@RequestBody CommuDto commu) throws Exception {
		serv.commuUpdate(commu);
	}

	@RequestMapping(value = "/comImgDelete", method = RequestMethod.GET)
	public Object comImgDelete2(@RequestParam("comNo") int comNo, @RequestParam("imgNo") int imgNo) throws Exception {
		return serv.comImgDelete(comNo, imgNo);
	}

//	광고
	@RequestMapping(value = "/adWrite", method = RequestMethod.GET)
	public Object adWrite(AdDto ad) throws Exception {
		return serv.adWrite(ad);
	}

	@RequestMapping(value = "/adUpdate", method = RequestMethod.PUT)
	public void adUpdate(@RequestBody AdDto ad) throws Exception {
		serv.adUpdate(ad);
	}

	@RequestMapping(value = "/imgInsert", method = RequestMethod.POST)
	public void datatest(@RequestParam("adNo") String adNo, @RequestParam("marNo") String marNo,
			@RequestParam("comNo") String comNo, MultipartHttpServletRequest images) throws Exception {
		serv.imgInsert(adNo, marNo, comNo, images);
	}

	@RequestMapping(value = "/adImgDelete", method = RequestMethod.GET)
	public Object adImgDelete(@RequestParam("adNo") int adNo, @RequestParam("imgNo") int imgNo) throws Exception {
		return serv.adImgDelete(adNo, imgNo);
	}

}
