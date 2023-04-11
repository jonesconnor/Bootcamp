function changeColor(colorcode)
{
    if(colorcode=='r')
    document.bgColor="red";
    else if (colorcode=='g')
    document.bgColor="green";
    else
    document.bgColor="blue"
}

function changeHeader()
{
   document.getElementsByClassName("clsh1")[0].style.color="red"  
}


function changeContent()
{
    document.getElementById("spaone").innerText="Save Trees Save Earth - Topic of the day";
}

function changePara()
{
    document.getElementsByClassName("clspara")[0].innerHTML="<ul><li>100$ : UK</li><li> 200$: US </li></ul>"
}


function getValue()
{
    let location=document.getElementById("spatwo").innerText;
   alert("Your location is " + location)


}