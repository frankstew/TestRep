var express = require("express");
var app = express();

// "/" => HI
// "/bye" =>bye
// "/cat" => meow

// ********routes*********
app.get("/", function(req, res) {
	res.send("Hi");
});

app.get("/cats", function(req, res) {
	res.send("MEOW");
});

app.get("/bye", function(req, res) {
	res.send("fuck");
});

// route parameter for any /r/sumn
app.get("/r/:subredditName", function(req, res) {
	var subName = req.params.subredditName;
	res.send("ITS THE " + subName.toUpperCase() + " SUBREDDIT");
});

// uses req.params object to make the page dynamic
app.get("/r/:subredditName/comments/:id/:title", function(req, res) {
	var subName = req.params.subredditName;
	res.send("WELCOME TO THE " + subName.toUpperCase() + " SUBREDDIT");
});

// * matches everything, needs to go at the end
app.get("*", function(req, res) {
	res.send("UFUCKEDUPTHISAINTAVALIDURL");
});

// listen
app.listen(3000, function() {
	console.log("Server is lsitening on port 3000");
});