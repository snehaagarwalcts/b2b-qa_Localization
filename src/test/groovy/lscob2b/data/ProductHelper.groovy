package lscob2b.data


class ProductHelper  {
	
	def static String BRAND_LEVIS = "levis"
	def static String BRAND_DOCKERS = "dockers"
	
	
	/**
	 * Product for pdp check
	 */
	def static String getProduct(brand) {
		if(brand == BRAND_LEVIS) return "04511-1472"
		if(brand == BRAND_DOCKERS) return ""
		null
	}			
	
	def static getUpCrossSelling(brand) {
		if(brand == BRAND_LEVIS) {
			return [
				code: '00501-0039',
				upSelling: ["00501-1964", "00501-1711", "00501-1764", "00501-1860"],
				crossSelling:["00501-0101", "00501-0113", "00501-0114", "00501-1307", "00501-1622"]
			]
		}	
		null
	}
	
	def static getWaitlistProduct(brand) {
		if(brand == BRAND_LEVIS) "05527-0458"
		if(brand == BRAND_DOCKERS) ""
		null
	}	
	
	def static getOrderHistoryProduct(brand) {
		if(brand == BRAND_LEVIS) "05527-0458"
		if(brand == BRAND_DOCKERS) ""
		null
	}
	
	
}
