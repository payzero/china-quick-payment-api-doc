package cn.payzero.quickpaysdk.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TestConstant {
	
	@Value("${quickpay.url}")
	public String TEST_QUICKPAY_API_URL;
	
	@Value("${quickpay.signToken}")
	public String TEST_SIGN_TOKEN;
	
	@Value("${quickpay.username}")
	public String TEST_USER_NAME;
	
	@Value("${quickpay.password}")
	public String TEST_PASSWORD;
	
	public String bearerToken = "";
}
