function funpromise()
{
    let score=-20;

    let promobj=new Promise( (resolve,reject)=>
                            {
                            if (score<0)
                            reject("Error. invalid score")
                            else
                            resolve("you have appeared for exam");
       
                            }
                            );


promobj.then( res=> console.log("inside then  " + res))
        .catch( err=> console.log("inside catch " + err))
    


}

funpromise()