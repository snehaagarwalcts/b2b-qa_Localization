package lscob2b.modules

import geb.Module;

class AddToCartModule extends Module{

	static content = {
		
		enterSizeLabel { $('.product-grid-header>h2') }
		
		sizeGuideLabel { $('.product-grid-header>p>a') }		
		
		inStockLabel { $('.available') }
		
		limitedStockLabel { $('.limited') }
		
		outOfStockLabel { $('.outofstock') }
		
		buttonAddToCart { $('.button-large.add_to_cart_button') }	
		
		buttonNotifyMe { $('.button.btn-white.addtowaitlist') }	
		
	}
}
