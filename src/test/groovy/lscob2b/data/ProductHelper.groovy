package lscob2b.data


class ProductHelper  {
	
	def static String BRAND_LEVIS = "levis"
	def static String BRAND_DOCKERS = "dockers"
	
	
	/**
	 * Product for pdp check
	 */
	def static String getProduct(brand) {
		if(brand == BRAND_LEVIS) return "00501-0039"
		if(brand == BRAND_DOCKERS) return ""
		null
	}			
	
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
