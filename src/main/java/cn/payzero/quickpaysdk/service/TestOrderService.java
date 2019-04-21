package cn.payzero.quickpaysdk.service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import cn.payzero.quickpaysdk.model.GeneralQuickpayResponse;
import cn.payzero.quickpaysdk.model.OrderItemDto;
import cn.payzero.quickpaysdk.model.OrdersDto;
import cn.payzero.quickpaysdk.util.TestConstant;

@Service
public class TestOrderService {
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	TestConstant testConst;
	
	public void testOrderCreateAndPay() {
		try {
			OrdersDto o = new OrdersDto();
			
			o.setCurrency("CNY");
			o.setCustomsCode("HG016");
			o.setMchtOrderNo(System.currentTimeMillis()+"");
			o.setOrderDatetime(new Date());
			o.setPayerName("李白");
			o.setPayerNumber("310110198010101234");
			o.setPayerPhone("13800138000");
			o.setPaymentAmount(1L);
			o.setSubject("测试商品订单");
			List<OrderItemDto> items = new LinkedList<>();
			OrderItemDto oi1 = new OrderItemDto();
			oi1.setSubject("测试商品1");
			oi1.setItemLink("http://www.baidu.com/123");
			items.add(oi1);
			
			o.setItems(items);
			
			String url = testConst.TEST_QUICKPAY_API_URL + "order/createAndPay";
			
			HttpHeaders headers = new HttpHeaders();
			headers.set("Authorization", "Bearer " + testConst.bearerToken);
			
			HttpEntity<OrdersDto> entity = new HttpEntity<>(o, headers);
			
			ResponseEntity<GeneralQuickpayResponse> resp = restTemplate.postForEntity(url, entity, GeneralQuickpayResponse.class);
			
			if(HttpStatus.OK.equals(resp.getStatusCode())) {
				if(resp.getBody().getSuccess()) {
					System.out.println("订单创建并触发支付任务成功，等待服务器异步通知");
				}else {
					System.out.println("接口调用成功但业务调用失败: " + resp.getBody().getErrorCode() + "-" + resp.getBody().getErrorMsg());
				}
			}else {
				System.out.println("调用失败，返回http code: " + resp.getStatusCodeValue());
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
