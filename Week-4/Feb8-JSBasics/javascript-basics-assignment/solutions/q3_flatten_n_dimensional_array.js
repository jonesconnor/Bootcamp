/* Write a Program to Flatten a given n-dimensional array */

const flatten = (arr) => {
	// Write your code here
	let result = [];
	if (!Array.isArray(arr)) {
		return null;
	}
	arr.forEach(function(element) {
		if (Array.isArray(element)) {
			result = result.concat(flatten(element))
		} else {
			result.push(element);
		}
	});
	return result;
};

/* For example,
INPUT - flatten([1, [2, 3], [[4], [5]])
OUTPUT - [ 1, 2, 3, 4, 5 ]

*/

module.exports = flatten;
