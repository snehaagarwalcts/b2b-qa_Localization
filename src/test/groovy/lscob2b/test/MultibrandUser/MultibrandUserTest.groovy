package lscob2b.test.MultibrandUser

import geb.spock.GebReportingSpec
import lscob2b.TestConstants
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import spock.lang.Stepwise
import lscob2b.test.data.TestDataCatalog;
import static lscob2b.TestConstants.*

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
}
