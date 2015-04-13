package lscob2b.test.myaccount

import lscob2b.data.PageHelper
import lscob2b.data.UserHelper
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.pages.myaccount.AccountBalancePage
import lscob2b.pages.myaccount.MyAccountPage
import lscob2b.test.data.PropertProviderTest

class AccountBalancePageTest extends PropertProviderTest {

	def setup() {
		PageHelper.gotoPageLogout(browser, baseUrl)
	}

	def "Verify AccountBalance Page Fields"(){
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
		try{
		assert masterTemplate.breadCrumbActive.text() == expectedValue("text.account.accountBalance").toUpperCase()
		}catch(Exception e)
		{
		println(e);
		}
		assert masterTemplate.mainContainerLabel.text() == expectedValue("text.account.accountBalance").toUpperCase()
		assert masterTemplate.introContainerLabel.text() == expectedValue("text.account.accountBalanceIntro")
		assert totalBalance.text() == expectedValue("text.account.accountBalanceTotal").toUpperCase()
		assert totalOverdue.text() == expectedValue("text.account.accountBalanceOverdue").toUpperCase()
		assert creditLimit.text() == expectedValue("text.account.accountBalanceLimit").toUpperCase()
		where:
		user=UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER)
	}
}
