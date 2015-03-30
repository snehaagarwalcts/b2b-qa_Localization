package lscob2b.test.myaccount

import lscob2b.data.PageHelper
import lscob2b.data.UserHelper;
import lscob2b.pages.HomePage;
import lscob2b.pages.LoginPage;
import lscob2b.pages.myaccount.AccountBalancePage;
import lscob2b.pages.myaccount.MyAccountPage;
import lscob2b.test.data.PropertProviderTest

class AccountBalancePageTest extends PropertProviderTest {

	def setup() {
		PageHelper.gotoPageLogout(browser, baseUrl)
	}

	def "Verify OrderHistory Page Fields"(){
		setup:
		to LoginPage
		at LoginPage
		login(user)
		at HomePage
		masterTemplate.clickMyAccount()

		when: "at MyAccountPage"
		at MyAccountPage
		
		then: "click on AccountBalance Link "
		clickOnMyAccountBalanceLink()

		when: "at AccountBalancePage"
		at AccountBalancePage		

		then: "Verify translations at AccountBalancePage"
		assert masterTemplate.breadCrumbActive.text() == expectedValue("text.account.accountBalance")
		assert masterTemplate.mainContainerLabel.text() == expectedValue("text.account.accountBalance")
		assert masterTemplate.introContainerLabel.text() == expectedValue("text.account.accountBalanceIntro")
		assert totalBalance.text() == expectedValue("text.account.accountBalanceTotal")
		assert totalOverdue.text() == expectedValue("text.account.accountBalanceOverdue")
		assert creditLimit.text() == expectedValue("text.account.accountBalanceLimit")
		where:
		user=UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER)
	}
}
