package cn.payzero.quickpaysdk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
	
	//启动本应用后 （命令行启动命令为 mvn spring-boot:run)
	//可通过HTTP请求此地址 http://127.0.0.1:9030/test?testAPIName= 并携带不通参数，来测试不同接口
	//例如请求 http://127.0.0.1:9030/test?testAPIName=createAndPay 可用于测试创建并支付订单接口
	
	public static void main(String args[]) throws Exception {
		SpringApplication.run(Application.class, args);
	}
}

