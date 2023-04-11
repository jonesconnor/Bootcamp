var bookmainobj=require("../sourcefiles/funarray")
var assert=require("assert")

beforeEach('initilize book',()=>{

    
 bookmainobj.loadBook(['java','python','html','css'])

})

describe('checking array fun',()=>{

    it('returns the book for given index',()=>{

let result=bookmainobj.getBook(1);
assert.equal("python",result);

    })
}) //first describe



describe("checking async fun",()=>{

  it('should check existence of book', function(done)
      {
        bookmainobj.isAsyncBookexist('html',
         function(result)
         {
            assert.equal(result,true)
         }
         );
       done();
      
        }) //it


})





