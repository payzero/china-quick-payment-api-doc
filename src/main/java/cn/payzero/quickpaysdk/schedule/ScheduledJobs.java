package cn.payzero.quickpaysdk.schedule;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.payzero.quickpaysdk.model.AuthenticationDto;
import cn.payzero.quickpaysdk.model.GeneralQuickpayResponse;
import cn.payzero.quickpaysdk.util.TestConstant;

@Service
public class ScheduledJobs {
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	ObjectMapper om;
	
	@Autowired
	TestConstant testConst;
	
	@Scheduled(fixedDelay=4*60*60*1000 - 2*60*1000)
	public void fetchQuickpayBearerToken() {
		try {
			AuthenticationDto authDto = new AuthenticationDto();
			authDto.setUsername(testConst.TEST_USER_NAME);
			authDto.setPassword(testConst.TEST_PASSWORD);
			
			URI uri = new URI(testConst.TEST_QUICKPAY_API_URL + "auth/login");
			
			ResponseEntity<GeneralQuickpayResponse> resp = restTemplate.postForEntity(uri, authDto, GeneralQuickpayResponse.class);
			if(HttpStatus.OK.equals(resp.getStatusCode())) {
				 
				Map<String, Object> map = resp.getBody().getData();
				testConst.bearerToken = (String)map.get("token");
				System.out.println("获取token: " + testConst.bearerToken);
			}
			
		}catch(URISyntaxException | RestClientException e) {
			e.printStackTrace();
		}
		
	}
	
}
