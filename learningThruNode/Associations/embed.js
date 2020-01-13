var mongoose = require("mongoose");


mongoose.set('useNewUrlParser', true);
mongoose.set('useFindAndModify', false);
mongoose.set('useCreateIndex', true);
mongoose.set('useUnifiedTopology', true);

mongoose.connect("mongodb://localhost/associations");


// POST

var postSchema = new mongoose.Schema({
	title: String,
	content: String
});

var Post = mongoose.model("Post", postSchema);



// USER

var userSchema = new mongoose.Schema({
	email: String,
	name: String,
	posts: [postSchema] // embedding posts in each user
});

var User = mongoose.model("User", userSchema);

// var newUser = new User({
// 	email: "cuntfuck",
// 	name: "+bitch"
// });

// newUser.posts.push({
// 	title: "okay cool",
// 	content: "sick lol"
// });

// newUser.save(function(err, user) {
// 	if (err) {
// 		console.log(err);
// 	} else {
// 		console.log(user);
// 	}
// });


User.findOne({name: "+bitch"}, function(err, user)  {
	if (err) {
		console.log(err);
	} else {
		user.posts.push({
			title: "second post",
			content: "this is the second post, findOne should send it to one user"
		});
		user.save(function(err, user) {
			if (err) {
				console.log(err);
			} else {
				console.log(user);
			}
		});
	}
});













// TEST POST AND USER


// var newPost = new Post({
// 	title: "uh",
// 	content: "mufukn uh"
// });

// newPost.save(function(err, post) {
// 	if (err) {
// 		console.log(err);
// 	} else {
// 		console.log(post);
// 	}
// })

// var newUser = new User({
// 	email: "fleebflorb",
// 	name: "fuck"
// });

// newUser.save(function(err, user) {
// 	if (err) {
// 		console.log(err);
// 	} else {
// 		console.log(user);
// 	}
// });