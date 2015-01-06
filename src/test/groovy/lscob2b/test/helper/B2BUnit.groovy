package lscob2b.test.helper

class B2BUnit{
	static final String LEVIS_GROUP_CODE = "010"
	static final String DOCKERS_GROUP_CODE = "020"
	static final String MULTIBRAND_GROUP_CODE = "120"
	
	String uid
	String name
	String country
	String userPriceGroup
	String groups //we may need to change this into a list in the future but it seems it is all single value at the moment
	String reportingOrganization
	String orderBlock
	String paymentTerms
	Boolean active

	boolean isLevisUnit(){
		groups == LEVIS_GROUP_CODE
	}

	boolean isMultibrandUnit(){
		groups == MULTIBRAND_GROUP_CODE
	}

	boolean isDockersUnit(){
		groups == DOCKERS_GROUP_CODE
	}
}