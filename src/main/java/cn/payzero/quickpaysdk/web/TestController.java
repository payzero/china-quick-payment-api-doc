package cn.payzero.quickpaysdk.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.payzero.quickpaysdk.service.TestOrderService;

@RestController
public class TestController {
	
	@Autowired
	TestOrderService testOrderService;
	
	/**
	 * 使用不通的testAPIName来进行测试,
	 * 可使用的值包括: "createAndPay"
	 * @param testAPIName
	 * @return
	 */
	@RequestMapping("test")
	public String testQuickpayAPI(@RequestParam String testAPIName) {
		if("createAndPay".equalsIgnoreCase(testAPIName)) {
			testOrderService.testOrderCreateAndPay();
		}
		
		return "OK";
	}
}
