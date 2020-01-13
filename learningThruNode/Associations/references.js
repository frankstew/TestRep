var mongoose = require("mongoose"), 
	User     = require("./models/user"),
	Post     = require("./models/post");


mongoose.set('useNewUrlParser', true);
mongoose.set('useFindAndModify', false);
mongoose.set('useCreateIndex', true);
mongoose.set('useUnifiedTopology', true);

mongoose.connect("mongodb://localhost/associations_2");



// making bob the slob

// User.create({
// 	email: "bobtheslob@gmail.com",
// 	name: "bob the slob",
// }, function(err, user) {
// 	if (err) {
// 		console.log(err);
// 	} else {
// 		console.log(user);
// 	}
// });

// create a post and make it bob the slobs

// Post.create({
// 	title: "this is some gnarly slob maaaaan",
// 	content: "fuckkkkkkkkkkkkkkkkkkk"
// }, (err, post) => {
// 	if (err) {
// 		console.log(err);
// 	} else {
// 		User.findOne({name: "bob the slob"}, (err, foundUser) => {
// 			if (err) {
// 				console.log(err);
// 			} else {
// 				foundUser.posts.push(post);
// 				foundUser.save((err, data) => {
// 					if (err) {
// 						console.log(err);
// 					} else {
// 						console.log(data);
// 					}
// 				});
// 			}
// 		});
// 	}
// });


//Find user
// find all posts by user

	// find bob the slob       populate posts with actual posts?  make it happen?
// User.findOne({name: "bob the slob"}).populate("posts").exec((err, user) => {
// 	if (err) {
// 		console.log(err);
// 	} else {
// 		console.log(user);
// 	}
// });








