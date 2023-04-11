/* Write a program to build a `Pyramid of stars` of given height */

const buildPyramid = (h) => {
	// Write your code here
     let output = "";
     for(i = 0; i < h; i++) {
          let spaces = " ".repeat(h - i - 1);
		let stars = " *".repeat(i + 1);
		output += spaces + stars + "  \n";
     }
     return output;
};

/* For example,
INPUT - buildPyramid(6)
OUTPUT -
     *
    * *
   * * *
  * * * *
 * * * * *
* * * * * *

*/

module.exports = buildPyramid;