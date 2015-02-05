package lscob2b.test.MultibrandUser

import static lscob2b.TestConstants.*
import geb.spock.GebReportingSpec
import lscob2b.data.UserHelper
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.test.data.TestDataCatalog
import lscob2b.test.data.TestHelper
import spock.lang.Ignore
import spock.lang.IgnoreRest
import spock.lang.Unroll

class MultibrandUserTest extends GebReportingSpec {

	def setupSpec() {
		browser.go(baseUrl + TestHelper.PAGE_LOGOUT)
	}
	
	def setup() {
		to LoginPage
	}

	def cleanup() {
		masterTemplate.doLogout()
	}
	
	def "Check switch to dockers is present"() {
		setup:
			login (multibrandUser)

		when: "At HomePage"
			at HomePage

		then: "Check SwitchTo Link"
			waitFor {
				masterTemplate.levisLogo.empty
				!masterTemplate.dockersLogo.empty
			}
	}

	def "Switch to Dockers theme"(){
		setup:
			login (multibrandUser)

		when: "At HomePage"
			at HomePage
			
		then: "Check SwitchTo Dockers"
			waitFor {
				masterTemplate.levisLogo.empty
				!masterTemplate.dockersLogo.empty
			}
		
		and: "Switch to dockers brand"	
			masterTemplate.switchBrand()
			
		when: "at HomePage"
			at HomePage
			
		then: "Check SwitchTo Levis"
			waitFor {
				!masterTemplate.levisLogo.empty
				masterTemplate.dockersLogo.empty
			}
		
	}

	def "Swtich to Levis theme"(){
		setup:
			login (multibrandUser)
			
		when: "At HomePage"
			at HomePage
		
		then: "Switch to dockers brand"
			masterTemplate.switchBrand()
			
		when: "At Homepage"
			at HomePage
		
		then: "Check SwitchTo Levis"
			waitFor {
				!masterTemplate.levisLogo.empty
				masterTemplate.dockersLogo.empty
			}

		and: "Switch to levis brand"
			masterTemplate.switchBrand()
			
		when: "At Homepage"
			at HomePage
		
		then: "Check SwitchTo Dockers"
			waitFor {
				masterTemplate.levisLogo.empty
				!masterTemplate.dockersLogo.empty
			}
	}
	
	def "Check if switch to dockers is present using Levis customer"(){
		setup:
			login (TestDataCatalog.getALevisUser())
		
		when: "at home"
			at HomePage
				
		then: "Switch to dockers should not be present"
			waitFor {
				masterTemplate.levisLogo.empty
				masterTemplate.dockersLogo.empty
			}
	}
	
	def "Check if switch to dockers is present using Dockers customer"(){
		setup:
			login (TestDataCatalog.getADockersUser())
		
		when: "at home"
			at HomePage
				
		then: "Switch to dockers should not be present"
			waitFor {
				masterTemplate.levisLogo.empty
				masterTemplate.dockersLogo.empty
			}
	}
	
	def "Check if correct products/catalogs are displayed on Levis Theme"(){
		setup:
			login (user)
		
		when: "At Homepage"
			at HomePage
			
		then: "Check SwitchTo Docker"
			waitFor {
				masterTemplate.levisLogo.empty
				!masterTemplate.dockersLogo.empty
			}
			
		where:
			user = UserHelper.getUser(UserHelper.B2BUNIT_MULTIBRAND, UserHelper.ROLE_CUSTOMER)	

		//FIXME use more robust check (ex. product detail)
//		and: "Search for Levis products"
//			masterTemplate.doSearch('501 Levis Original Fit Homestead')		
//			at OrderSearchPage
//																			
//		then: "Search for Dockers products"
//			masterTemplate.doSearch('dockers')								
//			at OrderSearchPage
//			waitFor { checkMessageTextExists() }
	}
	
	def "Check if correct products/catalogs are displayed on Dockers Theme"(){
		setup:
			login (user)
		
		when: "At Homepage"
			at HomePage
			
		then: "Switch Brand"
			masterTemplate.switchBrand()
		
		when: "At Homepage"
			at HomePage
		
		then: "Check SwitchTo Levis"	
			waitFor {
				!masterTemplate.levisLogo.empty
				masterTemplate.dockersLogo.empty
			}

		where:
			user = UserHelper.getUser(UserHelper.B2BUNIT_MULTIBRAND, UserHelper.ROLE_CUSTOMER)
			
		//FIXME use more robust check (ex. product detail)
//		and: "Search for Dockers products"
//			masterTemplate.doSearch('dockers')								
//			at OrderSearchPage
//																			
//		then: "Search for Levis products"
//			masterTemplate.doSearch('501 Levis Original Fit Homestead')		
//			at OrderSearchPage
//			waitFor { checkMessageTextExists() }
	}
}
