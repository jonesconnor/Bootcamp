async function myfun()
{
    try
    {
   let res=    await fetch('localhost:3000/abc');
    }
    catch(err)
    {
        console.log(" Error occured " + err )
    }
}

myfun()