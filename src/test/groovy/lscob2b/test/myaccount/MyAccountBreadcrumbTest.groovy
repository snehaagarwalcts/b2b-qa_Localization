package lscob2b.test.myaccount

import geb.spock.GebReportingSpec
import lscob2b.pages.LoginPage
import lscob2b.pages.HomePage
import lscob2b.pages.MyAccountPage
import spock.lang.Stepwise

@Stepwise
/**
 * Created by i303936 on 12/3/14 , added by I065970 on 12/5/14
 */
class MyAccountBreadcrumbTest extends GebReportingSpec {
	
	static String defaultPassword = "12341234"

	static String levisUser = "robert.moris@monsoon.com"
	static String dockersUser = "deno.rota@fashion-world.com"
	static String multibrandUser = "joseph.hall@city-apparel.com"
		
	def setup() {
		to LoginPage
	}
	
   	def login(String username) {
		doLogin(username, defaultPassword)
	}
	
	def cleanup() {
		masterTemplate.doLogout()
	}
	
	def "Login ,go to My account Page and Check breadcrumb"() { // tests the login itself without worrying about rights
		when: "Login as selected user"
		
				login (user)
		
				then: "We should see correct logo "
		
				at HomePage
				
				when: "at my homepage"
				
				to MyAccountPage
				
				then:"check bread crumbs "
				
				masterTemplate.breadcrumbExistsByUrl("/lscob2bstorefront/lscob2b/en/")
				masterTemplate.breadcrumbExistsByUrl("/lscob2bstorefront/lscob2b/en/my-account")
				masterTemplate.breadcrumbIsActiveByUrl("/lscob2bstorefront/lscob2b/en/my-account")
				println masterTemplate.breadcrumbs.size() // added by I065970 on 12/4/14
				assert masterTemplate.breadcrumbs.size() == 2
		
			//	!themeForm
		
			//	logoAltTag == themeSpecificAltTag
		
				where:
		
				user		| themeSpecificAltTag
				levisUser	| "Levis Strauss & Company"
				dockersUser	| "Levis Strauss & Company" // TODO this should be different for Dockers, not yet implemented
				multibrandUser | "Levis Strauss & Company"
				}
	
}
