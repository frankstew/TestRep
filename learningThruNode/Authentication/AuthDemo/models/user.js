var mongoose 				= require("mongoose"),
	passportLocalMongoose 	= require("passport-local-mongoose");

var userSchema = new mongoose.Schema({
	username: String,
	password: String
});

// makes erialize and deserialize methods so we dont have to make them
userSchema.plugin(passportLocalMongoose);


module.exports = mongoose.model("User", userSchema);