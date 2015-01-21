package lscob2b.test.MultibrandUser

import static lscob2b.TestConstants.*
import geb.spock.GebReportingSpec
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.pages.OrderSearchPage
import lscob2b.test.data.TestDataCatalog
import spock.lang.Ignore
import spock.lang.IgnoreRest
import lscob2b.pages.productdetails.ProductDetailsPage

class MultibrandUserTest extends GebReportingSpec {

	def setup() {
		to LoginPage
	}

	def cleanup() {
		masterTemplate.doLogout()
	}

	def "Check switch to dockers is present"() {
		setup:
		login (multibrandUser)

		when: "at home"

		at HomePage

		then: "Levis theme and switch to dockers is present"

		checkSwitchTo()
		dockersLogo
	}

	def "Switch to Dockers theme"(){
		setup:
		login (multibrandUser)
		at HomePage
		dockersLogo

		when: "click switch to"

		clickSwitchTo()

		then: "Levis logo is present"
		
		levisLogo
		
	}
	
	def "Swtich to Levis theme"(){
		setup:
		login (multibrandUser)
		at HomePage
		clickSwitchTo()
		levisLogo

		when: "click switch to"
		clickSwitchTo()
		

		then: "Levis logo is present"
		dockersLogo		
		
	}
	
	def "Check if switch to dockers is present using Levis customer"(){
		setup:
		login (TestDataCatalog.getALevisUser())
		
		when: "at home"
		
		at HomePage
				
		then: "Switch to dockers should not be present"
		
		!switchToLink.displayed
		
	}
	
	def "Check if switch to dockers is present using Dockers customer"(){
		setup:
		login (TestDataCatalog.getADockersUser())
		
		when: "at home"
		
		at HomePage
				
		then: "Switch to dockers should not be present"
		
		!switchToLink.displayed
		
	}
	
	def "Check if correct products/catalogs are displayed on Levis Theme"(){
		setup:
		login (multibrandUser)
		at HomePage
		dockersLogo

		when: "At homepage search for Levis products"

		masterTemplate.doSearch('501 Levis Original Fit Homestead')
		at OrderSearchPage
		
		then: "Search for Dockers products"
		masterTemplate.doSearch('dockers') //TODO change to dockers products
		at OrderSearchPage
		checkMessageTextExists()
	}
	

	def "Check if correct products/catalogs are displayed on Dockers Theme"(){
		setup:
		login (multibrandUser)
		at HomePage
		dockersLogo
		clickSwitchTo()
		levisLogo
		
		when: "At homepage search for Dockers products"
		masterTemplate.doSearch('dockers') //TODO change to dockers products
		at OrderSearchPage
		checkMessageTextExists()//TODO Delete once dockers products added
				
		then: "Search for Levis products and we should get a message"
		masterTemplate.doSearch('501 Levis Original Fit Homestead')
		at OrderSearchPage
		checkMessageTextExists()
	}
}
