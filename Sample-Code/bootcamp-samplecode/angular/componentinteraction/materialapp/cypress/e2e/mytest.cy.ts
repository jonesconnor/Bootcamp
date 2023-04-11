describe('land in login page',()=>{

   it ('visit the page',()=>{
    cy.visit('/');
    cy.url().should('include','login');
    cy.wait(3000)
   })

    it('check if landup has header',()=>{
  cy.visit("/")

  // in header component
  cy.get("#spaheader").contains('Employee Resource Management')
cy.wait(2000)


    })

})

describe('login process testing',()=>{
 it('login credential check',()=>{
  cy.visit('/')

  //in login component
  cy.get('#matid').click()
  cy.wait(3000)
 cy.get("#uname").click({force:true}).type("admin")
 cy.get("#password").click({force:true}).type("password")
cy.wait(5000);
cy.get('#butsignin').click()
cy.wait(5000)

cy.url().should('include','dashboard')
cy.wait(3000)
//in empadd component
cy.get('#butadd').contains('Add Employee')

cy.wait(4000)

 }) //it

}) //describe

 describe('add employee record',()=>{
    it('should access dashboard',()=>{

      const login =(username:any)=>{
    cy.session(username,()=>{
        cy.request({
          method:'POST',
            url:'http://localhost:3000/auth/v1',
            body: {username ,password:'password'}
        }).then( ( {body})=> {
            window.sessionStorage.setItem("mytoken",body.token)
        })

        })
    }

      login('admin')

        cy.visit('dashboard/empview')

        // in empview component
        cy.get(".clsh3").contains('List of Employees')
        cy.wait(3000)

        // in  empadd component 
        cy.get("#id").type("101");
        cy.get("#empname").type("Tony");
    cy.get("#role").type("Project Manager");
    cy.get('#butadd').click()

    cy.wait(4000)

    //in empcard component
cy.get('.example-card #pname').last().should('contain','Tony')

cy.wait(5000)

//in header component
cy.get("#butlogout").click()
cy.wait(2000)
cy.url().should('include','login');
    }) // it
 
   }) //describe




 





