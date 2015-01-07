package lscob2b.test.data

class Product {
	String code
	List<String> categories
	String catalogVersion
	Map<String,ProductPrice> prices //priceGroup->price
	Map<String, ProductSizeVariant> sizes //productSizeVariantCode->ProductSizeVariant
	List<String> availableAt

	ProductPrice getPriceForUser(User user){
		prices.get(user.defaultB2BUnit.userPriceGroup)
	}

	public class ProductPrice{
		String currency
		BigDecimal retailPrice
		BigDecimal wholesalePrice

		public ProductPrice(String currency, BigDecimal retailPrice, BigDecimal wholesalePrice) {
			super();
			this.currency = currency;
			this.retailPrice = retailPrice;
			this.wholesalePrice = wholesalePrice;
		}
	}

	class ProductSizeVariant{
		String code
		String sizeDim1
		String sizeDim2
		Integer stock
		public ProductSizeVariant(String code, String sizeDim1, String sizeDim2, Integer stock) {
			super();
			this.code = code;
			this.sizeDim1 = sizeDim1;
			this.sizeDim2 = sizeDim2;
			this.stock = stock;
		}
	}
}
