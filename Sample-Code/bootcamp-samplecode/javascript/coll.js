let person={
            "name":"Margret",
            "age":13
            }

console.log(person["name"])

let personlist=[
                    {
                        "name":"Vipul",
                        "course":"Java"
                    },
                    {
                        "name":"Jose",
                        "course":"Java"
                    },
                    {
                        "name":"Ross",
                        "course":"Python"
                    },
                    
               ]

               personlist.forEach( ele => console.log(ele["name"] + " : " + ele["course"]) )

               let resultarr=personlist.filter( ele=>ele["course"]==="Java")
                            
          resultarr.forEach( ele=> console.log(ele))

