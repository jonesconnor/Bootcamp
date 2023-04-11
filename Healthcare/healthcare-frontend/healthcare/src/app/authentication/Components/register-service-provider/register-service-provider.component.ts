import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { RouterService } from 'src/app/healthroute/Service/router.service';
import { ProvidersService } from 'src/service-providers/services/providers.service';
import { ServiceProvider } from '../../Model/serviceprovider';
import { User } from '../../Model/User';
import { AuthServiceService } from '../../Service/auth-service.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-register-service-provider',
  templateUrl: './register-service-provider.component.html',
  styleUrls: ['./register-service-provider.component.css']
})
export class RegisterServiceProviderComponent {

  registerForm:FormGroup;
  user:User = new User();
  sp:ServiceProvider = {} as ServiceProvider; 
  showPwd:boolean=false;
  

  constructor(private spservice:ProvidersService,private router:RouterService,private authserve:AuthServiceService,private formbuilder:FormBuilder,private snackbar:MatSnackBar){
    this.user.role="ServiceProvider";
    this.registerForm = this.formbuilder.group({
      email:['',[Validators.required,Validators.maxLength(50),Validators.email]],
      firstName:['',[Validators.required,Validators.maxLength(50),Validators.minLength(2),Validators.pattern('^[a-zA-Z]+$')]],
      lastName:['',[Validators.required,Validators.maxLength(50),Validators.minLength(2),Validators.pattern('^[a-zA-Z]+$')]],
      phoneNumber:['',[Validators.required,Validators.maxLength(10),Validators.minLength(10),Validators.pattern('^[0-9]{10}$')]],
      location:['',[Validators.required,Validators.maxLength(50),Validators.pattern('^[a-z A-Z0-9\s.,-]+$')]],
      specialization:['',[Validators.required,Validators.maxLength(50),Validators.pattern('^[a-zA-Z ]+$')]],
      fee:['',[Validators.required,Validators.maxLength(6),Validators.pattern('^[0-9]+$')]],
      password:['',[Validators.required,Validators.minLength(8),Validators.maxLength(50)]]
    })
  }
  showPass(){
    this.showPwd=!this.showPwd;
  }

  register(registerForm:FormGroup){
    this.user.mailid=registerForm.controls['email'].value;
    this.user.password=registerForm.controls['password'].value;

    this.sp.email=registerForm.controls['email'].value;
    this.sp.firstName=registerForm.controls['firstName'].value;
    this.sp.lastName=registerForm.controls['lastName'].value;
    this.sp.phoneNumber=registerForm.controls['phoneNumber'].value;
    this.sp.location=registerForm.controls['location'].value;
    this.sp.specialization=registerForm.controls['specialization'].value;
    this.sp.fee=registerForm.controls['fee'].value;

    this.authserve.register(this.user).subscribe(
      res=>{
        console.log("Service Provider Login Registered");
        this.spservice.addServiceProvider(this.sp).subscribe(
          res=>{
            console.log("ServiceProvider Registered");
          },
          err=>{
            console.log("error");
            console.log(err);
            console.log("-----------");
          }
        )
        registerForm.reset();
        this.snackbar.open('Service Provider Registered','X')
        this.router.openAdminDash();
      },
      err=>{
        console.log(err);
        this.snackbar.open("Email already in use",'X',{
          duration:1000
        });
      }
    )
  }

}
