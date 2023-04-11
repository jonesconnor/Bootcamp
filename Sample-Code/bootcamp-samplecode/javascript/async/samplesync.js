function printLine()
{
    setTimeout( ()=> {
        for (i=0;i<=1000;i++)
        console.log("*");

     },3000);
    
}

function welcomeUser()
{
    console.log("hai , welcome to the AJAX session ");
}

function mainFun()
{
    printLine();
    welcomeUser();
    console.log("Main method started");
   
}

mainFun()