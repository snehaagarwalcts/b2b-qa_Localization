package lscob2b.pages.myaccount

import geb.Page
import lscob2b.modules.MasterTemplate

class AccountBalancePage extends Page {

	static url = "my-account/balance"

	static at = { waitFor { title == "Your Account Balance | LSCO B2B Site" || title == "DE_Your Account Balance | LSCO B2B Site"}  }

	static content = {

		masterTemplate { module MasterTemplate }

		totalBalance { $("div.balance-group",0).find("div.label") }

		totalOverdue { $("div.balance-group",1).find("div.label") }
		
		creditLimit { $("div.balance-group",2).find("div.label") }

	}
	
	def checkAccountBalanceExists(){
		!masterTemplate.mainContainerLabel.empty
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
