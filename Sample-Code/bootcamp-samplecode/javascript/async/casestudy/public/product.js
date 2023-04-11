var productarr=[];

function addProduct(event)
{
event.preventDefault()

let pid=document.getElementById("id").value;
let pname=document.getElementById("pname").value
let company=document.getElementById("company").value

let data= {
            "id":pid,
            "pname":pname,
            "company":company

            }

            console.log(data)

return fetch("http://localhost:3000/product",
        {
            "method":"POST",
            "headers" : { "content-type" : "application/json"},
            "body":JSON.stringify(data)
        }
     ).then(

        (res )=>
        {
                if(res.status==201)
                {
            console.log(res)
            productarr.push(data);
            displayProductTable();
            document.getElementById("id").value=""
            document.getElementById("pname").value=""
            document.getElementById("company").value=""
            console.log("successfully added "  )
            return Promise.resolve(res)
                }
                else    {
                    return Promise.reject(new error("duplicate id"));
                }
        }
     )
     .catch(
        (err)=>
        {
            console.log("error occured " + err )
            return Promise.reject(err)
        }
     )
    }

    function getProduct()
{
return fetch("http://localhost:3000/product")
    .then(
        (res)=>
        {
              if(res.ok)
              {
             return(   res.json())
             return Promiser.resolve(res)
              }
              else
              return Promise.reject(new error("something went wrong while fetching"))

          //  console.log(res)
        }

    )
    .then
    (
        (rjson)=>
        {
            productarr=rjson;
            displayProductTable();
        console.log(rjson)
        }
    )
    .catch(
        (err)=>
        {
            console.log("error " + err)
        }
    )


}

function displayProductTable()
{
  result="";
  result="<table border=3>";
  result+="<tr><td>Id</td><td>Name</td><td>Company</td></tr>";
  if(typeof productarr!='undefined')
  {
    productarr.forEach( (prd)=>
    {
        result+="<tr>";
    result+="<td>" + prd.id + "</td>" + "<td>" + prd.pname + "</td>" + "<td>" + prd.company + "</td>"; 
    result+=`<td><button onclick='deleteStudent(${prd.id})'> Delete </button> </td>`   ;

        result+="</tr>";
    }
    )
  }
  result+="</table>";
  document.getElementById("spatable").innerHTML=result;
}

function deleteStudent(paramid)
{
alert(paramid)
let ind=productarr.findIndex( ele=>  ele.id==paramid);

fetch("http://localhost:3000/product/"+paramid,
{
    "method":"DELETE"
})
.then(
    res=>
    {
        if(res.status==200)
        {
            productarr.splice(ind,1)
            displayProductTable();
            console.log("Successfully deleted");
        }
    }
)

}


getProduct()