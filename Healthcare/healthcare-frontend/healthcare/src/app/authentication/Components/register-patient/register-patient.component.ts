import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RouterService } from 'src/app/healthroute/Service/router.service';
import { User } from '../../Model/User';
import { AuthServiceService } from '../../Service/auth-service.service';
import { PatientserviceService } from 'src/app/patient/patientservice.service';
import { Patient } from '../../Model/patient.model';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-register-patient',
  templateUrl: './register-patient.component.html',
  styleUrls: ['./register-patient.component.css']
})
export class RegisterPatientComponent {
  registerForm:FormGroup;
  user:User = new User();
  patient:Patient = new Patient();
  showPwd:boolean=false;

  constructor(private router:RouterService, 
    private authserve:AuthServiceService,
    private formbuilder:FormBuilder, 
    private patientserve:PatientserviceService,
    private snackbar:MatSnackBar
    ){
    this.user.role="Patient";
    this.registerForm = this.formbuilder.group({
      email:['',[Validators.required,Validators.maxLength(50),Validators.email]],
      firstName:['',[Validators.required,Validators.maxLength(50),Validators.minLength(2),Validators.pattern('^[a-zA-Z]+$')]],
      lastName:['',[Validators.required,Validators.maxLength(50),Validators.minLength(2),Validators.pattern('^[a-zA-Z]+$')]],
      phoneNumber:['',[Validators.required,Validators.pattern('^[0-9]{10}$')]],
      address:['',[Validators.required,Validators.maxLength(50),Validators.pattern('^[a-z A-Z0-9\s.,-]+$')]],
      city:['',[Validators.required,Validators.maxLength(50),Validators.pattern('^[a-zA-Z]+$')]],
      postalCode:['',[Validators.required,Validators.pattern("^[ABCEGHJKLMNPRSTVXY][0-9][ABCEGHJKLMNPRSTVWXYZ][0-9][ABCEGHJKLMNPRSTVWXYZ][0-9]$")]],
      pharmacyNumber:['',[Validators.required,Validators.pattern('^[0-9]{10}$')]],
      healthNumber:['',[Validators.required,Validators.pattern('^[0-9]{9}$')]],
      password:['',[Validators.required,Validators.minLength(8),Validators.maxLength(50)]]
    })
  }
  showPass(){
    this.showPwd=!this.showPwd;
  }

  register(registerForm:FormGroup){
    this.user.mailid=registerForm.controls['email'].value;
    this.user.password=registerForm.controls['password'].value;

    this.patient.email=registerForm.controls['email'].value;
    this.patient.firstName=registerForm.controls['firstName'].value;
    this.patient.lastName=registerForm.controls['lastName'].value;
    this.patient.phoneNumber=registerForm.controls['phoneNumber'].value;
    this.patient.address=registerForm.controls['address'].value;
    this.patient.municipality=registerForm.controls['city'].value;
    this.patient.postalCode=registerForm.controls['postalCode'].value;
    this.patient.phaemacyPhoneNumber=registerForm.controls['pharmacyNumber'].value;
    this.patient.phn =registerForm.controls['healthNumber'].value;
    this.authserve.register(this.user).subscribe(
      res=>{
        console.log("Patient Login Registered");
        

        this.patientserve.addPatient(this.patient).subscribe(
          res=>{
            console.log("Patient Registered");
            this.snackbar.open("You've been registered","X",{
              duration:1000
            })
          
          },
          err=>{
            console.log("Error");
            console.log(err);
          }
        );
        //this.patient.firstName=registerForm.controls['firstName'].value;
        //etc

        //this.patientserve.addPatient(this.patient).subscribe()...
        
        registerForm.reset();
        this.router.openLogin();
        

      },
      err=>{
        console.log(err);
      }
    )
  }

}
