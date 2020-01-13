var express					= require("express"),
	mongoose 				= require("mongoose"),
	passport 				= require("passport"),
	bp 						= require("body-parser"),
	LocalStrategy 			= require("passport-local"),
	passportLocalMongoose 	= require("passport-local-mongoose");
	User 					= require("./models/user.js");


mongoose.set('useNewUrlParser', true);
mongoose.set('useFindAndModify', false);
mongoose.set('useCreateIndex', true);
mongoose.set('useUnifiedTopology', true);


mongoose.connect("mongodb://localhost/auth_demo_app");


var app = express();
app.use(require("express-session")({
	secret: "I love suck",// used to encode/ decode
	resave: false,
	saveUninitialized: false
}));
app.use(passport.initialize());
app.use(passport.session());
app.use(bp.urlencoded({extended: true}));


//another method we dont have to write this time bc of LocalStrategy
passport.use(new LocalStrategy(User.authenticate()));
passport.serializeUser(User.serializeUser());
passport.deserializeUser(User.deserializeUser());



// ============
//Routes
// ===========


app.get("/", (req, res) => {
		res.render("home.ejs");
});

			// middleware isLoggedIn, next refers to next arg in app.get (req, res) => 
app.get("/secret", isLoggedIn, (req, res) => {
	res.render("secret.ejs");
});

//AUTH ROUTES
app.get("/register", (req, res) => {
	res.render("register.ejs");
});


//handling user signup
app.post("/register", (req, res) => {
	//req.body.userName;
	User.register(new User({username: req.body.username}), req.body.password, (err, user) => {
		if (err) {
			console.log(err);
			return res.render("register.ejs");
		}		// local strategy, could say twitter or google etc.
		passport.authenticate("local")(req, res, () => {
			res.redirect("/secret");
		});
	});
});

// ==================
// LOGIN ROUTES, can currently log in over each other, which is maybe fine
// ==================

// LOGIN FORM
app.get("/login", (req, res) => {
	res.render("login.ejs");
});

// LOGIN LOGIC
				//middleware cause its in the middle
app.post("/login", passport.authenticate("local", {
	successRedirect: "/secret",
	failureRedirect: "/login"
}), (req, res) => {
	
});

app.get("/logout", (req, res) => {
	req.logout(); // wow its easy
	res.redirect("/")
});

//middleware to stop getting to secret after logout, all middleware take these 3 args
function isLoggedIn(req, res, next) {
	// if they are logged in, do the next arg in app.get
	if(req.isAuthenticated()) {
		return next()
	}
	// if they arent logged in, then...
	res.redirect("/login");
}


app.listen(3000, () => {
	console.log("Server start");
});