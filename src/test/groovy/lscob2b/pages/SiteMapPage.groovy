package lscob2b.pages

import geb.Page;

class SiteMapPage extends Page {

	static url = "/sitemap"

	static at = { waitFor { title == "Site Map | LSCO B2B Site" } }

	static content = {
		
		menLink {('div#main-container a[href*="Men/c/Levis_151_Male"]')}
		
		womenLink {('div#main-container a[href*="Women/c/Levis_151_Female"]')}
		
		myAccountLink {('div#main-container a[href*="/my-account"]')}		
		
		subCategoryAccountLink { $('.sitemap-column>ul li') }
		
		subCategoryLink { $('.sitemap-column>ul>li>a',it) }
		
		subCategoryLinksize { $('.sitemap-column>ul>li>a') }
		
	}
	
	def boolean hasPageLink(String link) 
	{
		waitFor { !subCategoryAccountLink.empty }
		subCategoryAccountLink.find("a", href: endsWith(link))
	}

}
