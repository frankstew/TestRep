var mongoose = require("mongoose");

// for all decrecation errors/warnings
mongoose.set('useNewUrlParser', true);
mongoose.set('useFindAndModify', false);
mongoose.set('useCreateIndex', true);
mongoose.set('useUnifiedTopology', true);

//connecting to mongoose, will make cat_app if it doesnt exist already
mongoose.connect("mongodb://localhost/cat_app");

// what should a cat look like
var catSchema = new mongoose.Schema({
	name: String,
	age: Number,
	temperament: String
});

// create Cat model, pretty much makes a constructor for cat object kinda?
var Cat = mongoose.model("Cat", catSchema);


// add a new cat to db

// new Cat() and Cat.save() in one, take the data and a callback
Cat.create({
	name: "moose",
	age: 69,
	temperament: "OLD"
}, function(err, cat) {
	if (err) {
		console.log(err);
	} else {
		console.log(cat);
	}
});


// Cat.save()
/*Kleo.save(function(err, cat) {
	if (err) {
		console.log("There was an error");
	} else {
		console.log("We saved a cat to the database");
		console.log(cat);
	}
})*/

// show all cats in db


// showing all cats in db
Cat.find({}, function(err, cats) {
	if (err) {
		console.log("uh oh spaghetti- o");
		console.log(err);
	} else {
		console.log("ALl the CATS")
		console.log(cats);
	}
});