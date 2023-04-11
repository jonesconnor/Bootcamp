var funcalcref=require("../sourcefiles/funcalcu")

var assert=require("assert")

var expect=require('chai').expect;


describe.skip("testing funcalcu ", ()=>{

    it("divides and return quotient", ()=>{

    let result=funcalcref(20,4)
  //  assert.equal(result,5);
  
  expect(result).equal(5);

    })

    it("divisor zero should fail",()=>{


let ans=funcalcref(12,0);
assert.equal(ans,-1);

    })



});

