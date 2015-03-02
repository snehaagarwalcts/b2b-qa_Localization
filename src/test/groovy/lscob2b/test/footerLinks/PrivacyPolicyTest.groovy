package lscob2b.test.footerLinks

import lscob2b.data.PageHelper;
import lscob2b.data.UserHelper;
import lscob2b.pages.HomePage;
import lscob2b.pages.LoginPage;
import lscob2b.pages.PrivacyPolicyPage;
import geb.spock.GebReportingSpec;

class PrivacyPolicyTest extends GebReportingSpec{
		
		def setup(){
			PageHelper.gotoPageLogout(browser, baseUrl)
			to LoginPage
			at LoginPage
		}
		
		/**
		 * TC BB-855 Automated Test Case: Verify Privacy Policy Link in Footer section
		 */
		def "Make sure the PrivacyPolicy Link is present for all the users"(){
			setup:
			login(user)
			
			when:"at Home Page"
			at HomePage
			
			then:"check PrivacyPolicy Link is present or not"
			waitFor{ masterTemplate.privacyPolicyLink.displayed }
					
			where:
			user | _
			UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER) | _
			UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_ADMIN) | _
			UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_CUSTOMER) | _
			UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_FINANCE) | _
			UserHelper.getUser(UserHelper.B2BUNIT_DOCKERS, UserHelper.ROLE_SUPER) | _
			UserHelper.getUser(UserHelper.B2BUNIT_DOCKERS, UserHelper.ROLE_ADMIN) | _
			UserHelper.getUser(UserHelper.B2BUNIT_DOCKERS, UserHelper.ROLE_CUSTOMER) | _
			UserHelper.getUser(UserHelper.B2BUNIT_DOCKERS, UserHelper.ROLE_FINANCE) | _
			UserHelper.getUser(UserHelper.B2BUNIT_MULTIBRAND, UserHelper.ROLE_SUPER) | _
			UserHelper.getUser(UserHelper.B2BUNIT_MULTIBRAND, UserHelper.ROLE_ADMIN) | _
			UserHelper.getUser(UserHelper.B2BUNIT_MULTIBRAND, UserHelper.ROLE_CUSTOMER) | _
			UserHelper.getUser(UserHelper.B2BUNIT_MULTIBRAND, UserHelper.ROLE_FINANCE) | _
		}
		
		/**
		 * TC BB-856 Automated Test Case: Verify the URL of Privacy Policy Page
		 */
		def "Check user navigates to correct PrivacyPolicy URL"(){
			setup:
			login(user)
			
			when:"at Home Page"
			at HomePage
			
			and: "click on PrivacyPolicy Link"
			waitFor{ masterTemplate.privacyPolicyLink.displayed }
			masterTemplate.privacyPolicyLink.click()
			
			then:"check that user navigates to PrivacyPolicy page"
			at PrivacyPolicyPage
			
			where:
			user | _
			UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER) | _
			UserHelper.getUser(UserHelper.B2BUNIT_DOCKERS, UserHelper.ROLE_ADMIN) | _
			UserHelper.getUser(UserHelper.B2BUNIT_MULTIBRAND, UserHelper.ROLE_CUSTOMER) | _
		}

}
