package lscob2b.test.localization.myaccount

import lscob2b.data.PageHelper
import lscob2b.data.UserHelper;
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.pages.myaccount.MyAccountPage
import lscob2b.pages.myaccount.admin.CreateUserPage
import lscob2b.pages.myaccount.admin.EditUserDetailsPage;
import lscob2b.pages.myaccount.admin.ManageUsersPage;
import lscob2b.pages.myaccount.admin.ViewUserDetailsPage;
import lscob2b.test.data.PropertProviderTest

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
		
		when: "at ManageUsersPage"
		at ManageUsersPage
		
		then: "Verify fields in ManageUsersPage"
		//assert masterTemplate.breadCrumbActive.text() == expectedValue("text.account.profile")	
		
		where:
		user=UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER)
	}

	def "Verify CreateUser Page Fields"(){
		setup:
		to LoginPage
		at LoginPage
		login(user)
		at HomePage
		masterTemplate.clickMyAccount()
		
		when: "at MyAccountPage"
		at MyAccountPage
		
		then: "click on ManageUsers Link "
		
		when: "at ManageUsersPage"
		at ManageUsersPage
		
		then: "click on Add new Users Link"
		
		when: "at CreateUserPage"
		at CreateUserPage
		
		then: "Verify fields in CreateUserPage"
		//assert masterTemplate.breadCrumbActive.text() == expectedValue("text.account.profile")	
		
		where:
		user=UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER)
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
		
		when: "at ManageUsersPage"
		at ManageUsersPage
		
		then: "click on any existing Users Link"
		
		when: "at ViewUserDetailsPage"
		at ViewUserDetailsPage
		
		then: "Verify fields in ViewUserDetailsPage"
		//assert masterTemplate.breadCrumbActive.text() == expectedValue("text.account.profile")	
		
		where:
		user=UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER)
	}

	def "Verify EditUserDetails Page Fields"(){
		setup:
		to LoginPage
		at LoginPage
		login(user)
		at HomePage
		masterTemplate.clickMyAccount()
		
		when: "at MyAccountPage"
		at MyAccountPage
		
		then: "click on ManageUsers Link "
		
		when: "at ManageUsersPage"
		at ManageUsersPage
		
		then: "click on any existing Users Link"
		
		when: "at ViewUserDetailsPage"
		at ViewUserDetailsPage
		
		then:"click on EDIT button"
		
		when: "at EditUserDetailsPage"
		at EditUserDetailsPage
		
		then: "Verify fields in EditUserDetailsPage"
		//assert masterTemplate.breadCrumbActive.text() == expectedValue("text.account.profile")	
		
		where:
		user=UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER)
	}

}
