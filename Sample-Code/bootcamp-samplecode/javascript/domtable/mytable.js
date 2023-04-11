function fetchData()
{
    

    let searchstr=document.getElementById("txtsearch").value;
   
   let result;
      let tab=document.getElementsByTagName("table")[0]
       let tbody= tab.getElementsByTagName("tbody")[0]
       let rows=tbody.getElementsByTagName("tr");

       for(let i=1;i<rows.length;i++)
       {
          tcells= rows[i].getElementsByTagName("td");
 

          if(tcells[0].innerText===searchstr)
          {
result=tcells[1].innerText + "  fees is " + tcells[2].innerText;
          }

    tcellarr= [...tcells]
   // console.log(tcellarr);

//   let resultarr=tcellarr.filter( td=> td.innerText=searchstr)
//      if (resultarr.length>0)
//      {
           
//      }      
  
       }

       document.getElementById("sparesult").innerText=result


}