package lscob2b.data

import groovy.json.JsonSlurper


class ProductHelper  {
	
	public static final DATA_PRODUCT				= "src/test/resources/data/Product.json"
	
	def static String BRAND_LEVIS = "levis"
	def static String BRAND_DOCKERS = "dockers"
	
	def private static Map<String, Map> products;
	
	static {
		products = (Map<String, Map>) new JsonSlurper().parseText(new File(DATA_PRODUCT).text)
	}
	
	static void main(String[] args) {
		println "code: " + getProduct(BRAND_LEVIS)
		println "cross: " + getCrossSelling(BRAND_LEVIS)
	}
	
	/**
	 * Product for pdp check
	 */
	def static String getProduct(brand) {
		products.get(brand).get("baseProduct").get("code")
	}			
	
	def static List<String> getCrossSelling(brand) {
		products.get(brand).get("crossSelling")
	}
	
	def static List<String> getUpSelling(brand) {
		products.get(brand).get("upSelling")
	}
	
	//TODO remove me and use getCrossSelling/getUpSelling
	def static getUpCrossSelling(brand) {
		if(brand == BRAND_LEVIS) {
			return [
				code: '00501-0039',
				upSelling: ["00501-0089","00501-0114", "00501-0101", "00501-0113", "00501-0162", "00501-0165"],
				crossSelling:["00501-0113", "00501-0165", "00501-1114", "00501-0162"]
			]
		}	
		null
	}
	
	def static getProductColor(brand) {
		if(brand == BRAND_LEVIS) {
			return [
				code: '00501-0039',
				related: ["00501-0039","00501-0101"]
			]
		}
		null
	}
	
	def static getWaitlistProduct(brand) {
		if(brand == BRAND_LEVIS) return "00501-0039"
		if(brand == BRAND_DOCKERS) ""
		null
	}	
	
	def static getOrderHistoryProduct(brand) {
		if(brand == BRAND_LEVIS) "00501-0039"
		if(brand == BRAND_DOCKERS) ""
		null
	}
	
	def static String[] getQuickOrderProduct(brand) {
		if(brand == BRAND_LEVIS) return [ "00501-0039", "00501-0113" ]
		if(brand == BRAND_DOCKERS) ""
		null
	}
	
	
	
}
