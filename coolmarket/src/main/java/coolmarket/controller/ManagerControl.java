package coolmarket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import coolmarket.service.ManagerService;

@RestController
public class ManagerControl {
	@Autowired
	private ManagerService serv;

	@RequestMapping(value = "/managerDataList", method = RequestMethod.GET)
	public Object marDec() throws Exception {
		return serv.managerDataList();
	}

	@RequestMapping(value = "/marAllDel", method = RequestMethod.GET)
	public void marAllDel() throws Exception {
		serv.marAllDel();
	}

	@RequestMapping(value = "/comAllDel", method = RequestMethod.GET)
	public void comAllDel() throws Exception {
		serv.comAllDel();
	}

	@RequestMapping(value = "/preAllUp", method = RequestMethod.GET)
	public void preAllUp(List<String> userNoList) throws Exception {
		serv.preAllUp(userNoList);
	}

	@RequestMapping(value = "/preUp", method = RequestMethod.GET)
	public void preUp(@RequestParam("userNo") String userNo) throws Exception {
		serv.preUp(userNo);
	}
}
