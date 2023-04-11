import { MatSnackBar } from '@angular/material/snack-bar';
import { RouterService } from './../router.service';
import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm!: FormGroup;

  constructor(private routeobj: RouterService, private auth: AuthService, private formBuilder: FormBuilder, private snackBar: MatSnackBar) {}

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      email: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  onSubmit() {
    const email = this.loginForm.controls['email'].value;
    const password = this.loginForm.controls['password'].value;
    this.auth.login(email, password).subscribe(
      (res: any) => {
        console.log(res);
        if (res.role === 'buyer') {
          this.routeobj.openBuyerDash();
          console.log("Open Buyer Dash");
        } else if (res.role === 'seller') {
          this.routeobj.openSellerDash();
          console.log("Open Seller Dash");
        }
      },
      (error: any) => {
        this.snackBar.open('Invalid Credentials', 'Close', {
          duration: 5000
        });
      }
    )
  }

  callRegistration() {
    this.routeobj.openRegistration();
  }

}
