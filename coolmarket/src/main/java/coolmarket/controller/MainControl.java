package coolmarket.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import coolmarket.service.MainService;

@RestController
public class MainControl {
	@Autowired
	private MainService serv;

	@RequestMapping(value = "/addr1", method = RequestMethod.GET)
	public Object addr1() throws Exception {
		List<String> addr = serv.addr1();
		List<Map<String, String>> returnAddr = new ArrayList<Map<String, String>>();
		Map<String, String> a = new HashMap<String, String>();
		a.put("value", "");
		a.put("text", "시/도");
		returnAddr.add(a);
		for (int i = 0; i < addr.size(); i++) {
			Map<String, String> b = new HashMap<String, String>();
			b.put("value", addr.get(i));
			b.put("text", addr.get(i));
			returnAddr.add(b);
		}
		return returnAddr;
	}

	@RequestMapping(value = "/addr2", method = RequestMethod.GET)
	public Object addr2(@RequestParam("addr1") String addr1) throws Exception {
		List<String> addr = serv.addr2(addr1);
		List<Map<String, String>> returnAddr = new ArrayList<Map<String, String>>();
		Map<String, String> a = new HashMap<String, String>();
		a.put("value", "");
		a.put("text", "시/군/구");
		returnAddr.add(a);
		for (int i = 0; i < addr.size(); i++) {
			Map<String, String> b = new HashMap<String, String>();
			b.put("value", addr.get(i));
			b.put("text", addr.get(i));
			returnAddr.add(b);
		}
		return returnAddr;
	}

	@RequestMapping(value = "/addr3", method = RequestMethod.GET)
	public Object addr3(@RequestParam("addr1") String addr1, @RequestParam("addr2") String addr2) throws Exception {
		Map<String, String> addre = new HashMap<String, String>();
		addre.put("addr1", addr1);
		addre.put("addr2", addr2);
		List<String> addr = serv.addr3(addre);
		List<Map<String, String>> returnAddr = new ArrayList<Map<String, String>>();
		Map<String, String> a = new HashMap<String, String>();
		a.put("value", "");
		a.put("text", "동");
		returnAddr.add(a);
		for (int i = 0; i < addr.size(); i++) {
			Map<String, String> b = new HashMap<String, String>();
			b.put("value", addr.get(i));
			b.put("text", addr.get(i));
			returnAddr.add(b);
		}
		return returnAddr;
	}

	@RequestMapping(value = "/marCate", method = RequestMethod.GET)
	public Object marCate() throws Exception {
		List<String> cate = serv.marCate();
		List<Map<String, String>> returnCate = new ArrayList<Map<String, String>>();
		Map<String, String> a = new HashMap<String, String>();
		a.put("value", "");
		a.put("text", "분류");
		returnCate.add(a);
		for (int i = 0; i < cate.size(); i++) {
			Map<String, String> b = new HashMap<String, String>();
			b.put("value", cate.get(i));
			b.put("text", cate.get(i));
			returnCate.add(b);
		}
		return returnCate;
	}
	
	@RequestMapping(value = "/comCate", method = RequestMethod.GET)
	public Object comCate() throws Exception {
		List<String> cate = serv.commuCate();
		List<Map<String, String>> returnCate = new ArrayList<Map<String, String>>();
		Map<String, String> a = new HashMap<String, String>();
		a.put("value", null);
		a.put("text", "분류");
		returnCate.add(a);
		for (int i = 0; i < cate.size(); i++) {
			Map<String, String> b = new HashMap<String, String>();
			b.put("value", cate.get(i));
			b.put("text", cate.get(i));
			returnCate.add(b);
		}
		return returnCate;
	}

	@RequestMapping(value="/newAdList",method=RequestMethod.GET)
	public Object newAdList() throws Exception {
		return serv.newAdList();
	}

	@RequestMapping(value = "/newMarketList", method = RequestMethod.GET)
	public Object newMarketList() throws Exception {
		return serv.newMarketList();
	}

	@RequestMapping(value = "/newCommuList", method = RequestMethod.GET)
	public Object newCommuList() throws Exception {
		return serv.newCommuList();
	}
}
