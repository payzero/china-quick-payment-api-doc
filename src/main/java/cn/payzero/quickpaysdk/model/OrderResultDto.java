package cn.payzero.quickpaysdk.model;


public class OrderResultDto {
	private String mchtOrderNo;
	private Long paymentAmount;
	
	private String payerName;  //支付人姓名
	private String payerNumber; //支付人证件号码		==> 通联接口有typo，为PAYPER_NUMBER
	private String payerPhone; //支付人手机号			==> 通联接口有typo，为PAYPER_PHONE
	
	private String subject; //交易内容
	
	private String payStatus; //单笔的支付状态
	private String declareStatus; //单笔的申报状态
	private String declareFailReason;
	
	private String paymentOrderNo; //
	
	private String verDept; //验核机构 1-银联 2-网联 3-其他
	private String payType; //用户支付类型 1-APP 2-PC 3-扫码 4-其他
	
	private String initRequest; //支付公司原始支付请求
	
	private String initResponse; //支付公司的原始返回
	
	private String paymentDatetime; //支付完成时间
	
	private String customsPayCompanyName;
	private String customsPayCompanyCode;
	
	
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
	public String getPayerName() {
		return payerName;
	}
	public void setPayerName(String payerName) {
		this.payerName = payerName;
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
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
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
	public String getDeclareFailReason() {
		return declareFailReason;
	}
	public void setDeclareFailReason(String declareFailReason) {
		this.declareFailReason = declareFailReason;
	}
	public String getPaymentOrderNo() {
		return paymentOrderNo;
	}
	public void setPaymentOrderNo(String paymentOrderNo) {
		this.paymentOrderNo = paymentOrderNo;
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
	public String getInitRequest() {
		return initRequest;
	}
	public void setInitRequest(String initRequest) {
		this.initRequest = initRequest;
	}
	public String getInitResponse() {
		return initResponse;
	}
	public void setInitResponse(String initResponse) {
		this.initResponse = initResponse;
	}
	public String getPaymentDatetime() {
		return paymentDatetime;
	}
	public void setPaymentDatetime(String paymentDatetime) {
		this.paymentDatetime = paymentDatetime;
	}
	public String getCustomsPayCompanyName() {
		return customsPayCompanyName;
	}
	public void setCustomsPayCompanyName(String customsPayCompanyName) {
		this.customsPayCompanyName = customsPayCompanyName;
	}
	public String getCustomsPayCompanyCode() {
		return customsPayCompanyCode;
	}
	public void setCustomsPayCompanyCode(String customsPayCompanyCode) {
		this.customsPayCompanyCode = customsPayCompanyCode;
	}
	
	
	
}
