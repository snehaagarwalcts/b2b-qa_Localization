package lscob2b.pages.productdetails

import lscob2b.modules.MasterTemplate;
import geb.Page;
import geb.navigator.Navigator;
import groovy.lang.MetaClass;

class ProductDetailsPage extends Page {

	static String numberRegex = "\\d+(\\.|,)\\d+((\\.|,)\\d+)?"

	static at = {
		!$("body.pageType-ProductPage").empty
		browser.currentUrl ==~ /^.*\/p\/[\-\w]+$/
	}

	static content = {
		masterTemplate { module MasterTemplate }
		wholesalePriceText { $("div.wholesale-price > span").text()}
		recommendedRetailPriceText { $("div.recommended-retail-price > span").text()}
		recommendedRetailPriceValue {
			parsePrice(recommendedRetailPriceText)
		}
		wholesalePriceValue {
			parsePrice(wholesalePriceText)
		}
		notifyMeWhenItemsBecomeAvailableLink {$("a.addtowaitlist")}
		addToWaitListButton {$("a.add_to_waitlist_button",1)}
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
	
	def clickNotifyMeWhenItemsBecomeAvailable(){
		notifyMeWhenItemsBecomeAvailableLink.click()
	}
	
	def clickAddToWaitList(){
		addToWaitListButton.click()
	}
	
}
