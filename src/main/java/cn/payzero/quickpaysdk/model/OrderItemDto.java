package cn.payzero.quickpaysdk.model;

import java.io.Serializable;

public class OrderItemDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String subject; //商品名
	private String itemLink; //商品链接
	private String articleNum; //货号
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getItemLink() {
		return itemLink;
	}
	public void setItemLink(String itemLink) {
		this.itemLink = itemLink;
	}
	public String getArticleNum() {
		return articleNum;
	}
	public void setArticleNum(String articleNum) {
		this.articleNum = articleNum;
	}
	
}
