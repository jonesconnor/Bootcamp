describe('Compassionet e2e tests', () => {
  it('redirects to landing page', () => {
    cy.visit('http://localhost:4200');

    cy.url().should('include', '/landing');
    cy.get('nav').contains('Compassionet');
    cy.get('nav').contains('Home');
    cy.get('nav').contains('Service Provider');
    cy.get('nav').contains('Medications');
  });

  it('landing page displays  Compassionet information', () => {
    cy.visit('http://localhost:4200/landing');

    cy.url().should('include', '/landing');
    cy.get('h3.who-we-are__title').should('exist');
    cy.get('h3.how-we-help__title').should('exist');
    cy.get('h3.what-makes__title').should('exist');
  });

  it('register a new user', () => {
    cy.visit('http://localhost:4200');

    cy.wait(2000);

    cy.get('a.btn').click({ force: true });
    cy.url().should('include', '/patientregister');

    cy.get('#mat-input-0').click({ force: true }).type('Test');
    cy.get('#mat-input-1').click({ force: true }).type('Patient');
    cy.get('#mat-input-2').click({ force: true }).type('test@patient.com');
    cy.get('#mat-input-3').click({ force: true }).type('1234567890');
    cy.get('#mat-input-4').click({ force: true }).type('Belmont');
    cy.get('#mat-input-5').click({ force: true }).type('Victoria');
    cy.get('#mat-input-6').click({ force: true }).type('A1A1A1');
    cy.get('#mat-input-7').click({ force: true }).type('1234567890');
    cy.get('#mat-input-8').click({ force: true }).type('123456789');
    cy.get('#mat-input-9').click({ force: true }).type('12345678');

    cy.get('span.mat-mdc-button-touch-target').click({ force: true });
    cy.url().then((url) => {
      if (!(url.includes('/patientregister') || url.includes('/login'))) {
        throw new Error(
          'URL must be login or register depending if the user has been already created'
        );
      }
    });
  });

  it('should be able to login as patient', () => {
    cy.visit('http://localhost:4200/login');

    cy.get('#mat-input-0').click({ force: true }).type('test@patient.com');
    cy.get('#mat-input-1').click({ force: true }).type('12345678');
    cy.get('mat-card-actions button').click({ force: true });

    cy.url().should('include', '/patientdash');
  });

  it('should be able to check all patient options on dashboard', () => {
    cy.visit('http://localhost:4200/login');

    cy.get('#mat-input-0').click({ force: true }).type('test@patient.com');
    cy.get('#mat-input-1').click({ force: true }).type('12345678');
    cy.get('mat-card-actions button').click({ force: true });

    cy.url().should('include', '/patientdash');
    cy.get('ul.sidenav__actions').contains('Dashboard');
    cy.get('ul.sidenav__actions').contains('My Profile');
    cy.get('ul.sidenav__actions').contains('My Medical History');
    cy.get('ul.sidenav__actions').contains('My Prescription');
    cy.get('ul.sidenav__actions').contains('My Payment History');
  });

  it('should be able to create an emergency as patient', () => {
    cy.visit('http://localhost:4200/login');

    cy.get('#mat-input-0').click({ force: true }).type('test@patient.com');
    cy.get('#mat-input-1').click({ force: true }).type('12345678');
    cy.get('mat-card-actions button').click({ force: true });

    cy.url().should('include', '/patientdash');

    cy.get('button.btn-emerg').click({ force: true });
    cy.wait(1500);

    //check that in case of emergency, a meeting starts or in case the doctors are not available
    cy.url().then((url) => {
      if (
        !(url.includes('/meetinginprogress') || url.includes('/patientdash'))
      ) {
        throw new Error(
          'URL must be login or register depending if the user has been already created'
        );
      }
    });
  });

  it('should be able to check their patient account and details', () => {
    cy.visit('http://localhost:4200/login');

    cy.get('#mat-input-0').click({ force: true }).type('test@patient.com');
    cy.get('#mat-input-1').click({ force: true }).type('12345678');
    cy.get('mat-card-actions button').click({ force: true });

    cy.url().should('include', '/patientdash');
    cy.get('i.fa-solid.fa-user-circle').click({ force: true });

    cy.url().should('include', '/patientprofile');

    cy.get('div.update-button').contains('Save Profile');
  });

  it('should be able to display all medications', () => {
    cy.visit('http://localhost:4200/login');

    cy.get('#mat-input-0').click({ force: true }).type('test@patient.com');
    cy.get('#mat-input-1').click({ force: true }).type('12345678');
    cy.get('mat-card-actions button').click({ force: true });

    cy.url().should('include', '/patientdash');

    cy.get('i.fa-solid.fa-pills').click({ force: true });

    cy.get('mat-progress-spinner').should('exist');

    cy.wait(10000);

    cy.get('mat-progress-spinner').should('not.exist');
  });

  it('should be able to display notification panel', () => {
    cy.visit('http://localhost:4200/login');

    cy.get('#mat-input-0').click({ force: true }).type('test@patient.com');
    cy.get('#mat-input-1').click({ force: true }).type('12345678');
    cy.get('mat-card-actions button').click({ force: true });

    cy.url().should('include', '/patientdash');

    cy.get('button.btn-notifications').click({ force: true });

    cy.get('p.message').contains('You have no active notifications');
  });

  it('should be able to logout', () => {
    cy.visit('http://localhost:4200/login');

    cy.get('#mat-input-0').click({ force: true }).type('test@patient.com');
    cy.get('#mat-input-1').click({ force: true }).type('12345678');
    cy.get('mat-card-actions button').click({ force: true });

    cy.url().should('include', '/patientdash');

    cy.get('button.btn-logout').click({ force: true });

    cy.url().should('include', '/login');
  });
});
