import { Component, EventEmitter, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { User } from '../../Model/User';
import { AuthServiceService } from '../../Service/auth-service.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import jwt_decode from 'jwt-decode';
import { RouterService } from 'src/app/healthroute/Service/router.service';
import { ProvidersService } from 'src/service-providers/services/providers.service';
import { PatientserviceService } from 'src/app/patient/patientservice.service';
import { HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  user: User = new User();
  loginForm: FormGroup;
  showPwd:boolean=false;

  constructor(
    private router: RouterService,
    private authserve: AuthServiceService,
    private formbuilder: FormBuilder,
    private snackbar: MatSnackBar,
    private spserve: ProvidersService,
    private patserve: PatientserviceService
  ) {
    this.loginForm = this.formbuilder.group({
      email: [
        '',
        [Validators.required, Validators.maxLength(50), Validators.email],
      ],
      password: [
        '',
        [
          Validators.required,
          Validators.minLength(8),
          Validators.maxLength(50),
        ],
      ],
    });
  }
  showPass(){
    this.showPwd=!this.showPwd;
  }

  login(loginForm: FormGroup) {
    console.log("Login Form Login()");
    console.log(loginForm.valid);
    if (loginForm.valid) {
      this.user.mailid = loginForm.controls['email'].value;
      this.user.password = loginForm.controls['password'].value;
      this.authserve.login(this.user).subscribe(
        (res:any) => {
          let token = res['token'];
          sessionStorage.setItem('bearerToken', token);
          let decode: any = jwt_decode(token);
          let role = decode['Role'];
          let email = decode['jti'];
          sessionStorage.setItem('emailId', email);
          sessionStorage.setItem('Role', role);
          
          if(role=="ServiceProvider"){
            console.log("You are a SP")
            this.spserve.getServiceProvidersByEmail(email).subscribe(
              res=>{
                console.log(res);
                sessionStorage.setItem('userId',res.id!);
                sessionStorage.setItem('name',res.firstName+" "+res.lastName);
                this.finishLogin();
              }
            );
          }
          else if((role== "Admin")){
            console.log("You are an Admin")
            sessionStorage.setItem('name','Admin');
            this.finishLogin();
            

          }
          else{
            let headers = new HttpHeaders().set("Authorization", `Bearer ${token}`);
            console.log("patient Logging in");
            this.patserve.getPatientByEmail(headers,email).subscribe(
              res=>{
                console.log(res);
                sessionStorage.setItem('userId',res.id);
                sessionStorage.setItem('name',res.firstName+" "+res.lastName);
                this.finishLogin();
               },
               err=>{
                 console.log("Patient LoginError");
                 console.log(err);
               }
             );
            
          }          
          
        },
        (err: any) => {
          console.log(err);
          this.snackbar.open('Invalid Credentials', 'X', {
            duration: 2000,
          });
        }
      );
    }
  }
  finishLogin(){
    this.loginForm.reset();
    window.location.reload();
  }

}
