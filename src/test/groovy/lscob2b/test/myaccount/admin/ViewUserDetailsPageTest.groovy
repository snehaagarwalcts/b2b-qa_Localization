package lscob2b.test.myaccount.admin

import lscob2b.data.PageHelper
import lscob2b.data.UserHelper
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.pages.myaccount.MyAccountPage
import lscob2b.pages.myaccount.admin.ManageUsersPage
import lscob2b.pages.myaccount.admin.ViewUserDetailsPage
import lscob2b.test.data.PropertProviderTest

class ViewUserDetailsPageTest extends PropertProviderTest {
	
	def setup() {
		PageHelper.gotoPageLogout(browser, baseUrl)
	}
	
	def "Verify ViewUserDetails Page Fields"(){
		setup:
		to LoginPage
		at LoginPage
		login(user)
		at HomePage
		masterTemplate.clickMyAccount()
		
		when: "at MyAccountPage"
		at MyAccountPage
		
		then: "click on ManageUsers Link "
		clickOnManageUsersLink()
		
		when: "at ManageUsersPage"
		at ManageUsersPage
		
		then: "click on any existing Users Link"
		//Add
		
		when: "at ViewUserDetailsPage"
		at ViewUserDetailsPage
		
		then: "Verify translations in ViewUserDetailsPage"
		//assert masterTemplate.breadCrumbActive.text() == expectedValue("text.account.profile")
		
		where:
		user=UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER)
	}

}
