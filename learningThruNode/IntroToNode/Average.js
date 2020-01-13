function average(scores) {
	var sum = 0;
	for (var i = 0; i < scores.length; i++) {
		sum += scores[i];
	}
	return Math.round(sum/(scores.length));
}

var s = [90, 98, 89, 100, 100, 86, 94];
console.log(average(s));