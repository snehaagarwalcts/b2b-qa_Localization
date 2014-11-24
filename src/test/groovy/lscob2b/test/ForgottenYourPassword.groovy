package lscob2b.test

//import lscob2b.modules.Login
import lscob2b.pages.ForgottenYourPasswordPage;
import spock.lang.Stepwise;
import geb.spock.GebReportingSpec
import geb.spock.GebSpec

class ForgottenYourPasswordTest extends GebReportingSpec
{

	String levisUser = "robert.moris@monsoon.com"
	String dockersUser = "deno.rota@fashion-world.com"
	String multibrandUser = "joseph.hall@city-apparel.com"
	
	def setup(){
		to ForgottenYourPasswordPage
		
		forgottenYourPasswordButton.click()
		
		}
	
	def "Forgotten your password? multibrandUser"(){
		
		waitFor(30){ $("input", name: "email") }
		when: "Forgotten your password?"
		
		emailAddress (multibrandUser)
		
		then: "we should arrive at"
		//not sure what to put here
		}
	
	def "Forgotten your password? levisUser"(){
		
		waitFor(30){ $("input", name: "email") }
		when: "Forgotten your password?"
		
		emailAddress (levisUser)
		
		then: "we should arrive at"
		//not sure what to put here
		
		}

	def "Forgotten your password? dockersUser"(){
		
		waitFor(30){ $("input", name: "email") }
		when: "Forgotten your password?"
		
		emailAddress (dockersUser)
		
		then: "we should arrive at"
		//not sure what to put here
		
		}

	
	def emailAddress(String email){
		sendEmail(email)
	}
}
