package lscob2b.test.myaccount
//package lscob2b.test.localization.myaccount
//
//import lscob2b.data.PageHelper
//import lscob2b.data.UserHelper
//import lscob2b.pages.HomePage
//import lscob2b.pages.LoginPage
//import lscob2b.pages.myaccount.MyAccountPage
//import lscob2b.pages.myaccount.admin.CreateUserPage
//import lscob2b.pages.myaccount.admin.EditUserDetailsPage
//import lscob2b.pages.myaccount.admin.ManageUsersPage
//import lscob2b.pages.myaccount.admin.ViewUserDetailsPage
//import lscob2b.test.data.PropertProviderTest
//import spock.lang.IgnoreRest
//import lscob2b.modules.MasterTemplate
//import lscob2b.modules.EditUserDetailsModule
//
//class ManageUsersPageTest extends PropertProviderTest {
//	
//	def setup() {
//		PageHelper.gotoPageLogout(browser, baseUrl)
//	}
//	
//	def "Verify ManageUser Page Fields"(){
//	setup:
//		to LoginPage
//		at LoginPage
//		login(user)
//		at HomePage
//		masterTemplate.clickMyAccount()
//		
//		when: "at MyAccountPage"
//		at MyAccountPage
//		
//		then: "click on ManageUsers Link "
//		clickOnManageUsersLink()
//		
//		when: "at ManageUsersPage"
//		at ManageUsersPage
//	
//		then: "Verify translations in ManageUsersPage"
//		//assert masterTemplate.breadCrumbActive.text() == expectedValue("text.account.profile")	
//	
//		where:
//		user=UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER)
//	}
//	
//	@IgnoreRest
//	def "Verify CreateNewUser Page Fields"(){
//		setup:
//		to LoginPage
//		at LoginPage
//		login(user)
//		at HomePage
//		masterTemplate.clickMyAccount()
//		
//		when: "at MyAccountPage"
//		at MyAccountPage
//		
//		then: "click on AddNewUsers Link "
//		clickOnAddNewUsers()		
//		
//		when: "at AddUserDetails Page"
//		at CreateUserPage
//		
//		then: "Verify translations in AddUserDetails Page"
//		//assert masterTemplate.breadCrumbActive.text() == expectedValue("text.account.profile")	
//		assert masterTemplate.mainContainerLabel.text()==expectedValue("text.company.manageUsers.adduser.title")
//		assert userDetails.firstNameLabel.text()==expectedValue("text.company.manageUser.user.firstName")
//		assert masterTemplate.requiredMessageText.text()==expectedValue("")
//		//println  masterTemplate.mainContainerLabel.text()
//		//println expectedValue("text.company.manageUsers.adduser.title")
//		where:
//		user=UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER)
//	}
//	
//	def "Verify ViewUserDetails Page Fields"(){
//		setup:
//		to LoginPage
//		at LoginPage
//		login(user)
//		at HomePage
//		masterTemplate.clickMyAccount()
//		
//		when: "at MyAccountPage"
//		at MyAccountPage
//		
//		then: "click on ManageUsers Link "
//		clickOnManageUsersLink()
//		
//		when: "at ManageUsersPage"
//		at ManageUsersPage
//		
//		then: "click on any existing Users Link"
//		//Add
//		
//		when: "at ViewUserDetailsPage"
//		at ViewUserDetailsPage
//		
//		then: "Verify translations in ViewUserDetailsPage"
//		//assert masterTemplate.breadCrumbActive.text() == expectedValue("text.account.profile")	
//		
//		where:
//		user=UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER)
//	}
//
//	def "Verify EditUserDetails Page Fields"(){
//		setup:
//		to LoginPage
//		at LoginPage
//		login(user)
//		at HomePage
//		masterTemplate.clickMyAccount()
//		
//		when: "at MyAccountPage"
//		at MyAccountPage
//		
//		then: "click on ManageUsers Link "
//		clickOnManageUsersLink()
//		
//		when: "at ManageUsersPage"
//		at ManageUsersPage
//		
//		then: "click on any existing Users Link"
//		//Add
//		
//		when: "at ViewUserDetailsPage"
//		at ViewUserDetailsPage
//		
//		then:"click on EDIT button"
//		//Add
//		
//		when: "at EditUserDetailsPage"
//		at EditUserDetailsPage
//		
//		then: "Verify translations in EditUserDetailsPage"
//		//assert masterTemplate.breadCrumbActive.text() == expectedValue("text.account.profile")	
//		
//		where:
//		user=UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER)
//	}
//
//}
