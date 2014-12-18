package lscob2b.pages.productdetails

import geb.Page;
import geb.navigator.Navigator;
import groovy.lang.MetaClass;

class ProductDetailsPage extends Page {

	static at = {
		!$("body.pageType-ProductPage").empty
	}
	
	static content = {
		masterTemplate { module MasterTemplate }
		wholesalePriceText { $("div.wholesale-price > span").text()}
		recommendedRetailPriceText { $("div.recommended-retail-price > span").text()}
		
	}
	
	def boolean recommendedRetailPriceExist(){
		containsPositiveDigit(recommendedRetailPriceText);
		//recommendedRetailPriceText ==~/.*[1..9]+.*/ //check price text actually contains some non zero numbers
	}
	
	def boolean wholesalePriceExist(){
		containsPositiveDigit(wholesalePriceText);
	}
	
	def boolean containsPositiveDigit(text){
		text ==~ /.*[1..9]+.*/
	}

}
