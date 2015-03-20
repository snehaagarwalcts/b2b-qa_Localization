package lscob2b.pages.myaccount

import geb.Page
import lscob2b.modules.MasterTemplate

class AccountBalancePage extends Page {

	static url = "my-account/balance"

	static at = { waitFor { title == "Your Account Balance | LSCO B2B Site"}  }

	static content = {

		masterTemplate { module MasterTemplate }

		mainContainer { $("#main-container > h1") }

		totalBalance { $("div.balance-group",0).find("div.label") }

		totalOverdue { $("div.balance-group",1).find("div.label") }
		
		creditLimit { $("div.balance-group",2).find("div.label") }
		
		//localization
		balanceIntro {$(".intro-container")}

	}
	
	def checkAccountBalanceExists(){
		!mainContainer.empty
	}
	
	def checkTotalBalanceExists(){
		!totalBalance.empty
	}
	
	def checkTotalOverdueExists(){
		!totalOverdue.empty
	}
	
	def checkCreditLimitExists(){
		!creditLimit.empty
	}

}
