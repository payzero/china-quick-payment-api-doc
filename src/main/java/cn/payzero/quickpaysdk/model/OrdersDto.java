package cn.payzero.quickpaysdk.model;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Positive;


public class OrdersDto {
	
	private Date orderDatetime; //订单下单完成实践
	
	private String paymentDatetime; //支付完成时间
	
	private String mchtOrderNo;
	
	@Positive
	private Long paymentAmount; //单位为分
	
	private String subject; //商品内容
	
	private String currency; //default CNY
	
	private String verDept; //验核机构 1-银联 2-网联 3-其他
	private String payType; //用户支付类型 1-APP 2-PC 3-扫码 4-其他
	
	private String payerName;  //支付人姓名
	private String payerType;  //支付人证件类型 01 身份证 
	private String payerNumber; //支付人证件号码		
	private String payerPhone; //支付人手机号			
	
	private String payStatus; //单笔的支付状态
	private String declareStatus; //单笔的申报状态
	
	private String customsCode; //申报哪一个海关
	private String customsAreaCode; //海关关区代码 （广州海关总需要的参数，各关区代码参见: “https://baike.baidu.com/item/%E5%85%B3%E5%8C%BA%E6%8A%A5%E5%85%B3%E4%BB%A3%E7%A0%81%E8%A1%A8/12803479”
	private String customsInType; //进口业务类型 参见CustomsInType
	private String customsJyOrg; //检验检疫机构代码 （广州海关需要的参数)
	
	private List<OrderItemDto> items;
	
	private Boolean isBookedBalance; //是否已安排在支付任务中

	public Date getOrderDatetime() {
		return orderDatetime;
	}

	public void setOrderDatetime(Date orderDatetime) {
		this.orderDatetime = orderDatetime;
	}

	public String getPaymentDatetime() {
		return paymentDatetime;
	}

	public void setPaymentDatetime(String paymentDatetime) {
		this.paymentDatetime = paymentDatetime;
	}

	public String getMchtOrderNo() {
		return mchtOrderNo;
	}

	public void setMchtOrderNo(String mchtOrderNo) {
		this.mchtOrderNo = mchtOrderNo;
	}

	public Long getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(Long paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getVerDept() {
		return verDept;
	}

	public void setVerDept(String verDept) {
		this.verDept = verDept;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getPayerName() {
		return payerName;
	}

	public void setPayerName(String payerName) {
		this.payerName = payerName;
	}

	public String getPayerType() {
		return payerType;
	}

	public void setPayerType(String payerType) {
		this.payerType = payerType;
	}

	public String getPayerNumber() {
		return payerNumber;
	}

	public void setPayerNumber(String payerNumber) {
		this.payerNumber = payerNumber;
	}

	public String getPayerPhone() {
		return payerPhone;
	}

	public void setPayerPhone(String payerPhone) {
		this.payerPhone = payerPhone;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public String getDeclareStatus() {
		return declareStatus;
	}

	public void setDeclareStatus(String declareStatus) {
		this.declareStatus = declareStatus;
	}

	public String getCustomsCode() {
		return customsCode;
	}

	public void setCustomsCode(String customsCode) {
		this.customsCode = customsCode;
	}

	public String getCustomsAreaCode() {
		return customsAreaCode;
	}

	public void setCustomsAreaCode(String customsAreaCode) {
		this.customsAreaCode = customsAreaCode;
	}

	public String getCustomsInType() {
		return customsInType;
	}

	public void setCustomsInType(String customsInType) {
		this.customsInType = customsInType;
	}

	public String getCustomsJyOrg() {
		return customsJyOrg;
	}

	public void setCustomsJyOrg(String customsJyOrg) {
		this.customsJyOrg = customsJyOrg;
	}

	public List<OrderItemDto> getItems() {
		return items;
	}

	public void setItems(List<OrderItemDto> items) {
		this.items = items;
	}

	public Boolean getIsBookedBalance() {
		return isBookedBalance;
	}

	public void setIsBookedBalance(Boolean isBookedBalance) {
		this.isBookedBalance = isBookedBalance;
	}

	
}
