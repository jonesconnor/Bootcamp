/* Write a Program to convert an array of objects to an object
	based on a given key */

// the value of the key passed in becomes the main key and the dictionary becomes the value
const convert = (l, key) => {
	// Write your code here
	let output = {};
	if (!Array.isArray(l) || key == null) {
		return null;
	}
	for (let i = 0; i < l.length; i++) {
		if (key in l[i]) {
			output[l[i][key]] = l[i];
		}
	}
	return output;
};

/* For example,
INPUT - convert([{id: 1, value: 'abc'}, {id: 2, value: 'xyz'}], 'id')
OUTPUT - {
			'1': {id: 1, value: 'abc'},
			'2': {id: 2, value: 'xyz'}
		 }

convert([{id: 1, value: 'abc'}, {id: 2, value: 'xyz'}], 'value')

		{
			"abc":{id: 1, value: 'abc'},
			"xyz" :{id: 2, value: 'xyz'}
		}




















*/

module.exports = convert;
