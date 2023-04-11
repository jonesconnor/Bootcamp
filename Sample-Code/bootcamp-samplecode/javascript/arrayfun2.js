let items=["cake","chocolate","carrot milkshake","icecream","pasta","peanut burger"]

//search

let pos=    items.indexOf("icecream");

if(pos<0)
console.log("Not found")
else
{
console.log("icecream found at position " + pos)
 items.splice(pos,1)

}

items.shift()

console.log(items)

let resultarr=items.map( ele=> ele.toUpperCase())

console.log(resultarr)

// flat

let days=[1,2,3,[4,5,6],7,8]

// console.log(days.length)
// console.log(days)

//let arrdays=days.flat()

//console.log(arrdays)

days.forEach( ele=> {
                    if(Array.isArray(ele))
                        {
                            ele.forEach( e=>console.log(e))
                        }
                        else
                        console.log(ele)
    
                     })





