async function sample()
{


let promiseobj=new Promise( (res,rej)=>{
                            setTimeout( ()=>res("successfully registered"),1000)
                            });

//var prom=fetch('        ');

   let result=  await promiseobj;

   console.log(result);



}

sample()
