package cn.payzero.quickpaysdk.web;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.payzero.quickpaysdk.exception.AesException;
import cn.payzero.quickpaysdk.model.NotificationWrapper;
import cn.payzero.quickpaysdk.model.OrderResultDto;
import cn.payzero.quickpaysdk.util.SHA1;
import cn.payzero.quickpaysdk.util.TestConstant;


/**
 * 请实现以下接口用于接收Payzero发送的各类通知
 * 
 *
 */
@RestController
public class NotificationsController {
	
	@Autowired
	ObjectMapper om;
	
	@Autowired
	TestConstant testConst;
	
	AtomicInteger receiveCount = new AtomicInteger(0);
	
	@RequestMapping(path="quickpay_notify")
	public String processQuickpayNotify(
			@RequestBody NotificationWrapper request){
		
		try {
			
			System.out.println("Receive msg count: ****** " + receiveCount.incrementAndGet());
			
			String sign = SHA1.getSHA1(testConst.TEST_SIGN_TOKEN, request.getTimestamp(), request.getMsgBody());
			System.out.println("自身计算签名:" + sign);
			System.out.println("请求返回签名:" + request.getSign());
			
			if(sign.equalsIgnoreCase(request.getSign())) {
				String msgType = request.getMsgType();
				String msgBody = request.getMsgBody();
				
				System.out.println("msgType: " + msgType);
				System.out.println("msgBody: " + msgBody);
				// 根据msgType以及msgBody处理业务逻辑

				if("PAY_STATUS_NOTIFY".equalsIgnoreCase(msgType) || "DECLARE_STATUS_NOTIFY".equalsIgnoreCase(msgType)) {
					OrderResultDto o = om.readValue(msgBody, OrderResultDto.class);
					System.out.println("successfully get data: " + o.getMchtOrderNo());
				
				}else {
					//....
				}
				
			}else {
				return "FAIL";
			}
			
		}catch(AesException|IOException e) {
			e.printStackTrace();
		}
		return "OK";
	}
}
