

let books=["c","cpp","java","python","pearl","javascript"]

//traversing
books.forEach( ele=>  console.log(ele))

// filtering

let result=books.filter( ele=>ele.startsWith("c"));

console.log(result)

//add 

books.push("asp.net")

console.log(books)


//pop

console.log(books.pop())

console.log(books)

// slice (star,end)   --> array , without including end position

let resultarr=books.slice(2,5)
console.log(resultarr)

//splice(index,noofelements) -- removes from starting index , no of elements

books.splice(3,2)

console.log(books)

for (let i=0;i<books.length;i++)
{
   console.log(books[i]);   
}




