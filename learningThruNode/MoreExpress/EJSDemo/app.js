/***************EJS FILES***********
use <% code %> for code that is only logic, wont be sent to be rendered in the request
use <%= code %> for code that is returned to be snet to request and rendered
use <%- include("header path") %> to include a header
*/


// import express
var express = require("express");
var app = express();

// serve public dir and look for ejs files always
app.use(express.static("public"));
app.set("view engine", "ejs");

// get request to home page (/)
app.get("/", function(req, res) {
	res.render("home");

});

// get request to /getfuckedby/sumn
app.get("/getfuckedby/:thing",function(req, res) {
	var thing = req.params.thing;
	// res.resnder("<filename>", {key1: value1, key2: value2...});
	// no .ejs vc already looking for ejs files
	// object is in form of whatTheEjsFileSees: whatItIsCalledHere
	// i.e. fucked.ejs now knows thing as thingVar, so use thingVar in fucked.ejs
	res.render("fucked", {thingVar: thing});
});

// get request to /posts
app.get("/posts", function(req, res) {
	var posts = [
		{title: "ur mom gay", author: "Timmy"},
		{title: "I fucked ur mom", author: "gay lady"},
		{title: "I didnt fuck your mom", author: "straight guy"}
	];
	res.render("posts", {posts: posts}); // sends posts as posts
});

// listen on port 3000
app.listen(3000, function() {
	console.log("app is listening on port 3000");
});