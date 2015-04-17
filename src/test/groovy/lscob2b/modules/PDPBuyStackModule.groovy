package lscob2b.modules

import geb.Module
import geb.navigator.Navigator

class PDPBuyStackModule extends Module{

	static content = { 
		
		title { $("h1.title") }
		
		colorStyle { $("div.style-no span") }
		
		colorName { $("div.color-name span") }
		
		colorsItems { $("ul.color-swatches li.color-swatch") }
		
		colorItem { index -> colorsItems[index].find("a") }
		
		labelWholesalePrice { $('.wholesale-price span') }
		
		labelRecommendedPrice { $('.recommended-retail-price span') }
		
		buyStackAttributes { $('.buystack-attributes>ul>li', it) }		
		
	}
	
}
