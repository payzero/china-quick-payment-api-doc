package cn.payzero.quickpaysdk.web;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.payzero.quickpaysdk.exception.AesException;
import cn.payzero.quickpaysdk.util.SHA1;
import cn.payzero.quickpaysdk.util.TestConstant;


/**
 * 请实现以下接口用于接收Payzero发送的各类通知
 * 
 *
 */
@RestController
public class NotificationsController {
	@RequestMapping(path="quickpay_notify")
	public String processQuickpayNotify(
			@RequestParam Map<String, String> requestMap){
		
		try {
			if(!requestMap.containsKey("timestamp") || !requestMap.containsKey("msgBody") || !requestMap.containsKey("signature")) {
				return "FAIL";
			}
			
			String sign = SHA1.getSHA1(TestConstant.TEST_SIGN_TOKEN, requestMap.get("timestamp"), requestMap.get("msgBody"));
			System.out.println("自身计算签名:" + sign);
			System.out.println("请求返回签名:" + requestMap.get("signature"));
			
			if(sign.equalsIgnoreCase(requestMap.get("signature"))) {
				String msgType = requestMap.get("msgType");
				String msgBody = requestMap.get("msgBody");
				
				System.out.println("msgType: " + msgType);
				System.out.println("msgBody: " + msgBody);
				// 根据msgType以及msgBody处理业务逻辑
				// ......
				// ......
			}else {
				return "FAIL";
			}
			
		}catch(AesException e) {
			e.printStackTrace();
		}
		return "OK";
	}
}
