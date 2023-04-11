// Create a list of fruits with their properties (name, color, pricePerKg)
// and convert it into a format so that for a given fruit name
// retrieval of its color and pricePerKg value is fast

var fruit1 = {
    name : "apple",
    color : "red",
    pricePerKg : 5
};

var fruit2 = {
    name : "banana",
    color : "yellow",
    pricePerKg : 4
};

var fruit3 = {
    name : "orange",
    color : "orange",
    pricePerKg : 3
};

var fruits = [fruit1, fruit2, fruit3];

function getFruitProperties(fruitName) {
    for (var i = 0; i < fruits.length; i++) {
      if (fruits[i].name === fruitName) {
        return {
          color: fruits[i].color,
          pricePerKg: fruits[i].pricePerKg
        };
      }
    }
    return 'Fruit not found.';
  }
