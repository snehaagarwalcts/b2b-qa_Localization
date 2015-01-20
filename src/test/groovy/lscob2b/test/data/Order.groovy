package lscob2b.test.data

class Order {

	String number
	
	Address address
	
	def boolean compareWithoutNumber(Order order) {
		return address.compare(order.address)
	}
	
	def boolean compare(Order order) {
		return number == order.number && compareWithoutNumber(order)
	}
	
}
