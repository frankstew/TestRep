var express = require("express");
var app = express();

var rise;
var hour;
var minute;
var second;
app.get("/", function(req, res) {
	// keeps subtracting except when server is restarted, then resets
	hour = (parseInt(hour, 10) - 8);
	if (hour < 0) {
		hour += 24;
	}
	res.send(hour.toString(10) + ":" + minute + ":" + second);
});

var request = require("request");
request("https://api.sunrise-sunset.org/json?lat=50.263721&lng=-119.273720&date=2019-08-19&formatted=1", function(error, response, body) {
	// check for error
	if (error) {
		console.log("something went wrong");
		console.log(error);
	// do sumn if all is well
	} else if (response.statusCode === 200) {
		// Things worked
		// body comes back as a string, need to parse to JSON
		bodyJSON = JSON.parse(body);
		rise = bodyJSON["results"]["sunset"];
		// console.log(typeof rise); ----> string
		time = rise.split(":"); // split is useful
		for (var i = 0; i < rise.length; i++) {
			hour = time[0];
			minute = time[1];
			second = time[2].split(" ")[0]; 
		}
		
		
	}
});



app.listen(3000, function() {
	console.log("port is listening on port 3000");
});
// see if info is got firsst before trying to do something with it