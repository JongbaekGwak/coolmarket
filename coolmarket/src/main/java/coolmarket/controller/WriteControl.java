package coolmarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

	@RequestMapping(value = "/marketWrite", method = RequestMethod.GET)
	public Object marketWrite(MarketDto market) throws Exception {
		return serv.marketWrite(market);
	}
	
	@RequestMapping(value = "/commuWrite", method = RequestMethod.GET)
	public Object marketWrite(CommuDto commu) throws Exception {
		return serv.commuWrite(commu);
	}
	
	@RequestMapping(value = "/adWrite", method = RequestMethod.GET)
	public Object adWrite(AdDto ad) throws Exception {
		return serv.adWrite(ad);
	}

	@RequestMapping(value = "/imgInsert", method = RequestMethod.POST)
	public void datatest(@RequestParam("adNo") String adNo, @RequestParam("marNo") String marNo,
			@RequestParam("comNo") String comNo, MultipartHttpServletRequest images) throws Exception {
		serv.imgInsert(adNo, marNo, comNo, images);
	}
}
