var promfun=require("../sourcefiles/funpromise")
var assert=require("assert")
var expect=require("chai").expect;
var should=require("chai").should();

describe("testing promise",()=>{


      it('check for existence of fun',function(){

        expect(promfun).to.be.an('object');
        expect(typeof promfun.funpromise).to.equal('function')



      })

  it('shoud return new',()=>{

   promfun.funpromise("saving life at Turkey").then(

    (msg)=>
    {
        expect("Today News is saving life at Turkey").to.equal(msg)
    }

   )

  }) //it

  it("should return error" ,()=>{
 
    promfun.funpromise().catch( msg=>
    {
     expect("Info is missing").to.equal(msg)   
    } )

  })




})