package lscob2b.test.helper

import groovy.json.JsonSlurper

class TestDataCatalog {

	static final String ADMIN_GROUP = "b2badmingroup"
	static List<User> users;
	static Map<String,B2BUnit> b2bUnits;
	
	static{
		b2bUnits = readB2BUnits("src/test/resources/testinput/B2BUnits.json")
		users = readUsers("src/test/resources/testinput/Users.json")
	}

	private static List<User> readUsers(String path){
		List<User> userList = new ArrayList<User>()
		Map<String, Map> jsonData = (Map<String, Map>) new JsonSlurper().parseText(new File(path).text)
		jsonData.values().each {
			User user = new User()
			user.setEmail(it.email)
			user.setPassword(it.password)
			user.setName(it.name)
			user.setDefaultB2BUnit(b2bUnits.(it.defaultB2BUnit))
			user.setTitle(it.title)
			user.setGroups(it.groups)
			userList.add(user)
		}
		return userList
	}
	
	private static Map<String,B2BUnit> readB2BUnits(String path){
		Map<String,B2BUnit> b2bUnitMap = new HashMap<String,B2BUnit>()
		Map<String, Map> jsonData = (Map<String, Map>) new JsonSlurper().parseText(new File(path).text)
		jsonData.keySet().each {
			B2BUnit b2bUnit = new B2BUnit();
			b2bUnit.setUid(it)
			b2bUnit.setName(jsonData.get(it).name)
			b2bUnit.setCountry(jsonData.get(it).country)
			b2bUnit.setActive(jsonData.get(it).active)
			b2bUnit.setGroups(jsonData.get(it).groups)
			b2bUnit.setOrderBlock(jsonData.get(it).orderBlock)
			b2bUnit.setPaymentTerms(jsonData.get(it).paymentTerms)
			b2bUnit.setReportingOrganization(jsonData.get(it).reportingOrganization)
			b2bUnit.setUserPriceGroup(jsonData.get(it).userPriceGroup)
			b2bUnitMap.put(it, b2bUnit)
		}
		return b2bUnitMap
	}

	static User getAnAdminUser(){
		getAUserWithGroup(ADMIN_GROUP)
	}

	static User getAUserWithGroup(String group){
		for (user in users) {
			if (user.groups.contains(group)) return user
		}
	}

	static User getALevisUser(){
		for (user in users) {
			if (user.defaultB2BUnit.isLevisUnit()) return user
		}
	}

	static User getAMultibrandUser(){
		for (user in users) {
			if (user.defaultB2BUnit.isMultibrandUnit()) return user
		}
	}

	static User getADockersUser(){
		for (user in users) {
			if (user.defaultB2BUnit.isDockersUnit()) return user
		}
	}
}
