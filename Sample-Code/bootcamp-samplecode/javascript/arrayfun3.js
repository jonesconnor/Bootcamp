let fruits=["apple","mango","banana","orange","grapes"]

let ans= fruits.reduce(
                (cnt,frt)=>
                {
              //  cnt+=frt.length>5 ? 1 : 0 ;
              // return cnt
                    let a=0
                  a =  frt.length>5 ? 1 : 0 ;
                  return cnt+a;
                     
                },
                0

               )

console.log( " count is " + ans)


let arr= fruits.reduce(
                    (resutlarr,frt)=>
                    {
                        if (frt.length>5)
                        resutlarr.push(frt)
                        return resutlarr
                    },
                    []

                    )

console.log(arr)




let output= fruits.map( frt=>frt.toUpperCase())
                    .filter( frt=> frt.includes("N"))
                    .reduce( (cnt, frt)=>
                            {
                            cnt++;
                            return cnt;
                            }
                            ,
                            0
                            )

console.log("count of fruits with character N is  " + output)