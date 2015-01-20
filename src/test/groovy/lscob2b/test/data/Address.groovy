package lscob2b.test.data

class Address {
	
	String country
	
	String region
	
	String town
	
	String postalcode
	
	String streetname
	
	String firstname
	
	String lastname
	
	def boolean compare(Address add) {
		return country == add.country && region == add.region && town == add.town && postalcode == add.postalcode && streetname == add.streetname && firstname == add.firstname && lastname == add.lastname
	}
	
}
