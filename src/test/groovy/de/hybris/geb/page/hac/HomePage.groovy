package de.hybris.geb.page.hac

import de.hybris.geb.module.hac.MenuModule;
import geb.Page;

class HomePage extends Page {

	static url = "/"
	
	static at = { waitFor { title == "hybris administration console | Home" } }
	
	static content = {
		
		menu { module MenuModule }
		
	} 
	
}
