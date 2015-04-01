package lscob2b.test.myaccount

import lscob2b.data.PageHelper
import lscob2b.data.UserHelper
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.pages.myaccount.MyAccountPage
import lscob2b.pages.myaccount.admin.CreateUserPage
import lscob2b.pages.myaccount.admin.EditUserDetailsPage
import lscob2b.pages.myaccount.admin.ManageUsersPage
import lscob2b.pages.myaccount.admin.ViewUserDetailsPage
import lscob2b.test.data.PropertProviderTest
import spock.lang.IgnoreRest
import lscob2b.modules.MasterTemplate
import lscob2b.modules.EditUserDetailsModule

class ManageUsersPageTest extends PropertProviderTest {
	
	def setup() {
		PageHelper.gotoPageLogout(browser, baseUrl)
	}
	
	def "Verify ManageUser Page Fields"(){
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
	
		then: "Verify translations in ManageUsersPage"
		//assert masterTemplate.breadCrumbActive.text() == expectedValue("text.account.profile")	
	
		where:
		user=UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER)
	}
}
