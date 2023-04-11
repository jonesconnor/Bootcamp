function funpromise(info)
{
  if(!info)
   return Promise.reject("Info is missing");

   return new Promise((res,rej)=>{
setTimeout( ()=>res("Today News is "+ info ,1000));

   })

}

module.exports={funpromise}