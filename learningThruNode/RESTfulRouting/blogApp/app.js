var express        = require("express"),
	bp             = require("body-parser"),
	mongoose       = require("mongoose"),
	methodOverride = require("method-override"),
	sanitizer      = require("express-sanitizer");
// also needs ejs 
var app = express();

app.use(bp.urlencoded({ extended: true }));
// so taht we can do put and delete requests, cant do them with forms
app.use(methodOverride("_method"));
app.use(express.static("public"));
app.use(sanitizer());


// for all decrecation errors/warnings
mongoose.set('useNewUrlParser', true);
mongoose.set('useFindAndModify', false);
mongoose.set('useCreateIndex', true);
mongoose.set('useUnifiedTopology', true);

//connecting to mongoose, will make cat_app if it doesnt exist already
mongoose.connect("mongodb://localhost/blog_app");

// what should a blogPost look like
var blogPostSchema = new mongoose.Schema({
	title: String,
	img: String,
	postBody: String,
	created: {type: Date, default: Date.now()}
	//date: String
});

// create blogPost model, pretty much makes a constructor for blogPost object kinda?
var blogPost = mongoose.model("blogPost", blogPostSchema);

// Landing page
app.get("/", function(req, res){
	res.render("landing.ejs");
});

// INDEX
app.get("/posts", function(req, res) {
	var posts = blogPost.find({}, function(err, posts) {
		if (err) {
			console.log(err);
		} else {
			res.render("index.ejs", {blogPosts: posts});
		}
	});
	
});

// NEW
app.get("/posts/new", function(req, res) {
	res.render("new.ejs");
});

// CREATE
app.post("/posts", function(req, res) {
	var title = req.body.Title;
	var imgURL = req.body.img;
	const sanitizedBody = req.sanitize(req.body.postBody);
	
	blogPost.create({title: title, img: imgURL, postBody: sanitizedBody}, function(err, post) {
		if (err) {
			res.render("new");
		} else {
			res.redirect("/posts");
		}
	});
});



// SHOW
app.get("/posts/:id", function(req, res) {
	var postID = req.params.id;
	var post = blogPost.findById(postID, function(err, post) {
		if (err) {
			console.log(err);
		} else {
			res.render("show.ejs", {post: post});
		}
	});
});

// EDIT
app.get("/posts/:id/edit", function(req, res) {
	var editID = req.params.id;
	var post = blogPost.findById(editID, function(err, post) {
		if (err) {
			res.render("show.ejs", {post: post});
		} else {
			res.render("edit.ejs", {post: post});
		}
	});
});

// UPDATE, could make this cleaner probably, instead of 3 conditionals
app.put("/posts/:id", function(req, res) {
	// var editID = req.params.id;
	req.body.post.postBody = req.sanitize(req.body.post.postBody);
	// always update created when a post is edited
	// can use req.body.post bc post gets sent from form as an object
	blogPost.updateOne({_id: req.params.id}, req.body.post, function(err, post) {
		if(err) {
			console.log(err);
		} else {
			console.log(post);
		}
	});
	
	res.redirect("/posts/" + req.params.id);
});

// DESTROY
app.delete("/posts/:id", function(req, res) {
	var deleteID = req.params.id;
	blogPost.deleteOne({_id: deleteID}, function(err, post) {
		if (err) {
			console.log(err);
		} else {
			console.log(post);
		}
	})
	res.redirect("/posts");
});



app.listen(3000, function() {
	console.log("Blog App is listening");
});