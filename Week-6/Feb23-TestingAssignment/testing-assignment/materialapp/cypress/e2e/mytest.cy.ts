describe('Login', () => {
  beforeEach(() => {
    cy.visit('http://localhost:4200/login');
    cy.wait(1000);
  })

  it ('visit the page',()=>{
    cy.visit('http://localhost:4200/login');
    cy.wait(1000)
   })


   it('should successfully login with valid credentials', () => {
    cy.get('.expandable-title').should('be.visible').click()
    cy.wait(1000)
    cy.get(".uname").click({force:true}).type("admin")
    cy.get(".pwd").click({force:true}).type("password")
    cy.wait(1000);
    cy.get('.btn').click()
    cy.wait(1000)
    cy.url().should('contain', 'dashboard');

  });

  it('should remain on login page given invalid credentials', () => {
    cy.get('.expandable-title').should('be.visible').click()
    cy.wait(1000)
    cy.get('.uname').click({force:true}).type('invalidusername');
    cy.get('.pwd').click({force:true}).type('invalidpassword');
    cy.wait(1000);
    cy.get('.btn').click();
    cy.wait(1000);
    cy.url().should('contain', 'login');
  });

  it('should remain on login page if user tries to skip to dashboard', () => {
    cy.visit('http://localhost:4200/dashboard');
    cy.wait(1000);
    cy.url().should('contain', 'login');
  })

});

describe('Register', () => {
  beforeEach(() => {
    cy.visit('http://localhost:4200/register');
    cy.wait(1000);
  })

  it('should return to login page after registering new employee', () => {
    cy.get('.name').click({force:true}).type('Connor');
    cy.get('.role').click({force:true}).type('New Grad');
    cy.wait(1000);
    cy.get('.btn-register').click();
    cy.wait(1000);
    cy.contains('Registered');
    cy.wait(1000);
    cy.get('.btn-login').click();
    cy.wait(1000);
    cy.url().should('contain', 'login');
  })
})

describe('Dashboard', () => {
  beforeEach(() => {
    cy.visit('http://localhost:4200/login');
    cy.wait(1000);
    cy.get('.expandable-title').should('be.visible').click()
    cy.wait(1000)
    cy.get(".uname").click({force:true}).type("admin")
    cy.get(".pwd").click({force:true}).type("password")
    cy.wait(1000);
    cy.get('.btn').click()
    cy.wait(1000)
  })

  it ('should display the most recently registered employee "Connor"', () => {
    cy.contains('Connor');
    cy.contains('New Grad');
  })

  it ('should return to login page when SignOut button is clicked', () => {
    cy.get('.btn').click();
    cy.wait(1000);
    cy.url().should('contain', 'login');
  })
})
