package lscob2b.test.login

import de.hybris.geb.page.hac.console.ImpexImportPage
import lscob2b.data.PageHelper
import lscob2b.data.UserHelper
import lscob2b.pages.HomePage;
import lscob2b.pages.LoginPage
import lscob2b.pages.TermsAndConditionPage
import lscob2b.test.data.PropertProvider

class TermsAndConditionsPageTest extends PropertProvider{
		PageHelper.gotoPageLogout(browser, baseUrl)
		
		then: "verify translations of TermsAndConditionPage"
		verifyTrueContains(masterTemplate.mainContainerLabel.text(), expectedValue("login.agreement.terms").toUpperCase(),"use contains()")
		verifyTrue(headerMessage.text(), expectedValue("login.agreement.infoMsg").toUpperCase())
		verifyTrue(disagree.text(), expectedValue("login.agreement.dontagree").toUpperCase())
		verifyTrue(agree.text(), expectedValue("login.agreement.agree").toUpperCase())
//		for(msg in message){
//			verifyTrueContains(msg.text().toLowerCase(), expectedValue("login.agreement.msg").toLowerCase(), "use contains()")
//		}
		verifyTrue(headerMessage.text(), expectedValue("login.agreement.errorMsg").toUpperCase())
	
	def "verify translations of TemsAndConditions page - from Footer Links"(){
		setup:
		PageHelper.gotoPageLogout(browser, baseUrl)
		to LoginPage
		at LoginPage
		login(user)
		
		when: "at Home Page"
		at HomePage
		
		then: "click on TermsAndConditions link from footer"
		masterTemplate.termsAndConditionsLink.click()
		
		when: "at terms and conditions page"
		at TermsAndConditionPage
		
		then: "verify translations of TermsAndConditionPage"
		verifyTrue(masterTemplate.mainContainerLabel.text(), expectedValue("login.agreement.terms").toUpperCase())
		//verifyTrue(title.text(), expectedValue("login.agreement.terms").toUpperCase())
//		for(msg in message){
//			verifyTrueContains(msg.text().toLowerCase(), expectedValue("login.agreement.msg").toLowerCase(), "use contains()")
//		}
		verifyTestFailedOrPassed()
		
		where:
		user=UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER)
	}
}