package lscob2b.pages

import lscob2b.modules.MasterTemplate;
import lscob2b.modules.SizingGridModule;
import lscob2b.modules.CheckOutModule
import lscob2b.modules.CartModule
import geb.Page;
import geb.navigator.Navigator;
import groovy.lang.MetaClass;



class OrderSearchPage extends Page {

	static String numberRegex = "\\d+(\\.|,)\\d+((\\.|,)\\d+)?"

	static at = {
//		!$("body.pageType-ContentPage").empty
		waitFor { title.endsWith("LSCO B2B Site") }
	}

	static content = {
		masterTemplate { module MasterTemplate }
		sizingGrid { module SizingGridModule}
		checkOut { module CheckOutModule}
		cartTemplate {module CartModule}
		
		messageText { $('div.title_holder h2') }
	}
	
	def checkMessageTextExists(){
		messageText
	}
}