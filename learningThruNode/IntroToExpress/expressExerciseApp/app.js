var express = require("express");
var app = express();

// object for sounds that the animals make
var sounds = {
	pig: "'Oink'",
	cow: "'Moo'",
	dog: "'Woof Woof!'",
	monkey: "'Yeet'",
	sloth: "'SLOOOOOOOOOOOOOOOOOOOwwwwww'"
}

// home page
app.get("/", function(req, res) {
	res.send("Hi there, welcome to my assignment!");
});

// speak using route parameters
app.get("/speak/:animalName", function(req, res) {
	var animal = req.params.animalName.toLowerCase();
	res.send("The " + animal + " says " + sounds[animal]);
});

// repeat using route parameters
app.get("/repeat/:string/:number", function(req, res) {
	var result = ""
	for (var i = 0; i < req.params.number; i++) {
		result += req.params.string + " ";
	}
	res.send(result);
});

app.get("*", function(req, res) {
	res.send("Sorry, page not found.....What are you doing with your life?");
})

// listening
app.listen(3000, function() {
	console.log("app is listening on port 3000");
});