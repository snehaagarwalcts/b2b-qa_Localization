package lscob2b.test.contactus

import geb.spock.GebReportingSpec
import lscob2b.data.PageHelper
import lscob2b.data.UserHelper
import lscob2b.pages.ContactUsPage
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage

class ContactUsAfterLogin extends GebReportingSpec 
{
	def setup() {
		PageHelper.gotoPageLogout(browser, baseUrl)
	}

	/**
	 * US BB-585 Contact us after login
	 * TC BB-784 Contact us after login
	 */
	def "Make sure requried fields are pre"(){
		setup:
		to LoginPage
		at LoginPage
		login(user)
		
		when: "At HomePage"
		at HomePage
		
		then: "Click on contact us"
		masterTemplate.doContactUs()
		
		and: "at Contact Us page"
		at ContactUsPage
		
		//TODO make title, first name, last name, etc come from user helper
		then: "Compare user data"
		//titleText == "Ms." //user.title
		firstName == "Levis"//user.name
		lastName == "SuperUser"//user.name
		emailText == user.email
		companyName == "Automated-Unit-1 Co.Ltd"//user.companyName
		customerNumber == "automated-unit-1"//user.customerNumber
		//country == "United Kingdom"//user.country

		where:
		user | _
		UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER) | _
	}
}
