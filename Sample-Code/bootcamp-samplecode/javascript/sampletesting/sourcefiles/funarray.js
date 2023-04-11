// function funMain()
// {

var booklist=[];

function loadBook(books)
{
    booklist=books;
}

function getBook(ind)
{
    return booklist[ind];
}

function isAsyncBookexist(book,myfun)
{
  setTimeout(
        ()=>{
            myfun(booklist.indexOf(book)>=0);
        }
        ,2000
    )

}


// return{
//     loadBook,
//     getBook
// }

// }

module.exports={loadBook,getBook,isAsyncBookexist}
