package kr.or.connect.booking.dto;

import java.math.BigDecimal;

public class ProductPrice {
	private int prdPriceId;
	private int productId;
	
	private String priceTypeName;
	private int price;
	private BigDecimal discountRate;
	private String createDate;
	private String modifyDate;
	
	
	public int getPrdPriceId() {
		return prdPriceId;
	}
	public void setPrdPriceId(int prdPriceId) {
		this.prdPriceId = prdPriceId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getPriceTypeName() {
		return priceTypeName;
	}
	public void setPriceTypeName(String priceTypeName) {
		this.priceTypeName = priceTypeName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public BigDecimal getDiscountRate() {
		return discountRate;
	}
	public void setDiscountRate(BigDecimal discountRate) {
		this.discountRate = discountRate;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	
	@Override
	public String toString() {
		return "ProductPrice [prdPriceId=" + prdPriceId + ", productId=" + productId + ", priceTypeName="
				+ priceTypeName + ", price=" + price + ", discountRate=" + discountRate + ", createDate=" + createDate
				+ ", modifiyDate=" + modifyDate + "]";
	}
	
}
