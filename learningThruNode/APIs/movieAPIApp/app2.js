/*************** CHANGE PACKAGE.JSON SO APP2 IS MAIN***********************/
var express = require("express");
var request = require("request");
var bp = require("body-parser");
var app = express();

app.use(bp.urlencoded({extended: true}));

// apikey=thewdb


app.get("/", function(req, res) {
	// render search form, sneaky, dont always render the homepage on "/" useful to keep the filenames and routes separate in your head to use them most effectively
	res.render("search2.ejs");
	
});

app.get("/results", function(req, res) {
	// thing sent from search2.ejs is req.query.searchTerm
	// each search is independent, refreshing weirdness
	// making the search form a dif file is good but have it render on the home page
	
	var url = "http://www.omdbapi.com/?apikey=thewdb&s=" + req.query.searchTerm;
	
	request(url, function(error, response, body) { // call api using request
			if (!error && response.statusCode === 200) {
				var parsedBody = JSON.parse(body);
				res.render("home2.ejs", {parsedBody: parsedBody});
			}
		});
});


app.listen(3000, function() {
	console.log("server is listening on port 3000");
});