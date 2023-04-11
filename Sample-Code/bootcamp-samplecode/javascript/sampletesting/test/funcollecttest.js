var funall=require("../sourcefiles/funcollect")
var assert=require("assert");

var expect=require("chai").expect;

var should=require("chai").should();

describe("checking collection funs",()=>{

    it('test collection attribute',()=>{

 let userlist={"username":"Dan","qualification":"Engineering","experience":"5years"}

    let userobj= funall.initializeUsers(userlist)


    userobj.should.have.property("username").equals("Dan");

    });

    it('get the value of given key',()=>{

        let userlist={"username":"Dan","qualification":"Engineering","experience":"5years"}

       let result=  funall.searchUser(userlist,"qualification")


       expect(result).equal("Engineering");
    })





})