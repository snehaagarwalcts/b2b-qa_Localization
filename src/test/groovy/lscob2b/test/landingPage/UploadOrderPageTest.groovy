package lscob2b.test.landingPage

import lscob2b.data.PageHelper
import lscob2b.data.UserHelper
import lscob2b.pages.LoginPage
import lscob2b.pages.UploadOrderPage
import lscob2b.test.data.PropertProvider

class UploadOrderPageTest extends PropertProvider{
	
	def setup() {
		PageHelper.gotoPageLogout(browser, baseUrl)
	}
	
	def "Verify translations - Women"() {
		setup:
		to LoginPage
		at LoginPage
		login(user)
		browser.go(baseUrl + link)
		
		when: "at Upload Order Page"
		at UploadOrderPage
		
		then: "click on UPLOAD FILE button"
		uploadFileButton.click()
		
		and: "verify translations of UploadOrderPage"
		verifyTrue(masterTemplate.mainContainerLabel.text(), expectedValue("text.order.upload").toUpperCase())
		verifyTrue(masterTemplate.alertMessageHeader.text(), expectedValue("text.please.note").toUpperCase())
		//verifyTrue(masterTemplate.alertMessage.text(), expectedValue("orderupload.invalid.file.format"))
		verifyTrue(downloadFileTitle.text(), expectedValue("orderupload.template.download").toUpperCase())
		verifyTrue(downloadFileLink.text()- ~/&/, expectedValue("orderupload.template.download").toUpperCase())
		verifyTrue(uploadFileButton.value(), expectedValue("order.upload.file"))
		verifyTestFailedOrPassed()
			
		where:
		user | link
		UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER) | "order/upload"
	}
}
