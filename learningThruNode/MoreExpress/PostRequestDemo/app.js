var express = require("express");
var app = express();
var bp = require("body-parser");

app.use(bp.urlencoded({extended: true}));
app.set("view engine", "ejs");

var friends = ["tony", "jen", "lil homie"];

app.get("/" , function(req, res) {
	res.render("home");
});

app.get("/friends", function(req, res) {
	
	res.render("friends", {friends: friends});
});

app.post("/addFriend", function(req, res) {
	var newFriend = req.body.newFriend;
	friends.push(newFriend);
	res.redirect("/friends");
	
})

app.listen(3000, function() {
	console.log("app is listening on port 3000");
});
