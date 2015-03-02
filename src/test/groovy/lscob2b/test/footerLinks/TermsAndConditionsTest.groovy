package lscob2b.test.footerLinks

import lscob2b.data.PageHelper;
import lscob2b.data.UserHelper;
import lscob2b.pages.HomePage;
import lscob2b.pages.LoginPage;
import lscob2b.pages.TermsAndConditionPage;
import geb.spock.GebReportingSpec;

class TermsAndConditionsTest extends GebReportingSpec{
		
		def setup(){
			PageHelper.gotoPageLogout(browser, baseUrl)
			to LoginPage
			at LoginPage
		}
		
		/**
		 * TC BB-857 Automated Test Case: Verify Terms and Conditions Link in Footer section
		 */
		def "Make sure the TermsAndConditions Link is present for all the users"(){
			setup:
			login(user)
			
			when:"at Home Page"
			at HomePage
			
			then:"check TermsAndConditions Link is present or not"
			waitFor{ masterTemplate.termsAndConditionsLink.displayed }
					
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
		 * TC BB-858 Automated Test Case: Verify the URL of Terms and Conditions Page
		 */
		def "Check user navigates to correct TermsAndConditions URL"(){
			setup:
			login(user)
			
			when:"at Home Page"
			at HomePage
			
			and: "click on TermsAndConditions Link"
			waitFor{ masterTemplate.termsAndConditionsLink.displayed }
			masterTemplate.termsAndConditionsLink.click()
			
			then:"check that user navigates to TermsAndConditions page"
			at TermsAndConditionPage
			
			where:
			user | _
			UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER) | _
			UserHelper.getUser(UserHelper.B2BUNIT_DOCKERS, UserHelper.ROLE_ADMIN) | _
			UserHelper.getUser(UserHelper.B2BUNIT_MULTIBRAND, UserHelper.ROLE_CUSTOMER) | _
		}

}
