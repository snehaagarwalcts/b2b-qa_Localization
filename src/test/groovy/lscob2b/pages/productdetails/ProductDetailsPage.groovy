

package lscob2b.pages.productdetails

import geb.Page
import geb.navigator.Navigator
import lscob2b.modules.CartModule
import lscob2b.modules.CheckOutModule
import lscob2b.modules.MasterTemplate
import lscob2b.modules.PDPBuyStackModule
import lscob2b.modules.SellModule
import lscob2b.modules.SizingGridModule

class ProductDetailsPage extends Page {

	static String numberRegex = "\\d+(\\.|,)\\d+((\\.|,)\\d+)?"

	static at = {
		waitFor { browser.currentUrl ==~ /^.*\/p\/[\-\w]+$/ }
	}

	static content = {
		
		masterTemplate { module MasterTemplate }
		
		sizingGrid { module SizingGridModule}
		
		checkOut { module CheckOutModule}
		
		cartTemplate {module CartModule}
		
		upSelling(required: false) { module SellModule, $("div.up-sell") }
		
		crossSelling(required: false) { module SellModule, $("div.cross-sell") }
		
		buyStack { module PDPBuyStackModule, $("div.pdp-buystack") }
		
		wholesalePriceText { $("div.wholesale-price > span").text()}
		recommendedRetailPriceText { $("div.recommended-retail-price > span").text()}
		orderQuantity{ $(".grid_three_dimensions").find("tr > td > .sku-quantity",0) }
		recommendedRetailPriceValue {
			parsePrice(recommendedRetailPriceText)
		}
		wholesalePriceValue {
			parsePrice(wholesalePriceText)
		}
	}

	def recommendedRetailPriceExist(){
		containsPositiveDigit(recommendedRetailPriceText);
	}

	def wholesalePriceExist(){
		containsPositiveDigit(wholesalePriceText);
	}

	def containsPositiveDigit(text){
		text ==~ /.*[1..9]+.*/ //check price text actually contains some non zero numbers
	}

	def parsePrice(String priceText) {
		String s = priceText.find(numberRegex).replace(",","").replace(".","")
		Double.parseDouble(s.substring(0, s.length()-2) + "." + s.substring(s.length()-2))
	}
	
	def addOrderQuantity(String quantity){
		orderQuantity.value(quantity)
	}
}
