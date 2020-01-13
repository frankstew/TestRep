/*************** CHANGE PACKAGE.JSON SO APP2 IS MAIN***********************/

var express = require("express");
var request = require("request");
var bp = require("body-parser");
var app = express();

app.use(bp.urlencoded({extended: true}));

// apikey=thewdb

var searchResults = []; // storing search results
var timesSearched = 0; // used to display try again message
app.get("/", function(req, res) {
	// sends search results and timesSearched to home.ejs for displaying results/message
	res.render("home.ejs", {searchResults: searchResults, timesSearched: timesSearched});
	
});

// post request to /search from home.js
app.post("/search", function(req, res) {
	var query = req.body.movieSearch; // the search term they give in home.ejs, movieSearch, found by req.body.<name> for post requests
	//console.log(query);
	var realQuery = "http://www.omdbapi.com/?apikey=thewdb&s=" + query; // makes whole url for api call
	
	request(realQuery, function(error, response, body) { // call api using request
		
		if (!error && response.statusCode === 200) {
			var responseData = JSON.parse(response.body);
			timesSearched += 1; // need to increment the times a search has been made...
			searchResults = []; // ...and reset the movie array so that error message shows if the search shows nothing
			//console.log(responseData["Response"] === "False");
			if (responseData["Response"] === "False") { // if the request is fine but search term yields no results:
				
				res.redirect("/"); // redirect
			} else {
				
				//console.log(responseData);
				var movieList = responseData["Search"]; // get to the actual data we want (all inside "Search" key)
			
				// push the movies onto searchResults to send to home.ejs
				for (var i = 0; i < movieList.length; i++) {
					searchResults.push(movieList[i]["Title"]);
				}
				
				res.redirect("/"); // redirect home
			}
		}
	});
	
});

app.listen(3000, function() {
	console.log("server is listening on port 3000");
});