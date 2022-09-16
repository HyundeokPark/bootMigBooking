package kr.or.connect.booking.dto;

public class Promotion {
	private Long id;
	private Long ProductId;
	private String productImageUrl;
	private Long imgId;

	public Long getImgId() {
		return imgId;
	}

	public void setImgId(Long imgId) {
		this.imgId = imgId;
	}

	public Long getProductId() {
		return ProductId;
	}

	public void setProductId(Long productId) {
		ProductId = productId;
	}

	public String getProductImageUrl() {
		return productImageUrl;
	}

	public void setProductImageUrl(String productImageUrl) {
		this.productImageUrl = productImageUrl;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Promotion [id=" + id + ", ProductId=" + ProductId + ", productImageUrl=" + productImageUrl + ", imgId="
				+ imgId + "]";
	}

}
