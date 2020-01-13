var faker = require("faker");
var maxName;
var maxPrice = 1000;
for (var i = 0; i < 10; i++) {
	var price = faker.commerce.price();
	var name  = faker.commerce.productName();
	if (price < maxPrice) {
		maxPrice = price;
		maxName = name;
	}
	
}
console.log(maxName + ": $" + maxPrice);