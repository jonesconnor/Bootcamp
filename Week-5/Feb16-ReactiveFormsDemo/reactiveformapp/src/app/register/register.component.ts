import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  saveForm: FormGroup;
  genderList: string[] = ['Male', 'Female', 'Other'];

  constructor(private formBuilder: FormBuilder) {
    this.saveForm = this.formBuilder.group({
      firstName:['',Validators.compose([Validators.required, Validators.maxLength(40), Validators.pattern("[a-zA-Z][a-zA-Z]+")])],
      lastName:['',[Validators.required, Validators.maxLength(40), Validators.pattern("[a-zA-Z][a-zA-Z]+")]],
      email:['',[Validators.required, Validators.maxLength(40), Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")]],
      mobileNumber:['',[Validators.required, Validators.pattern("^((\\+1-?)|0)?[0-9]{10}$")]],
      gender:['', Validators.required],
      address:['', [Validators.required, Validators.maxLength(200)]]
    })
  }

  registerUser(saveForm: FormGroup) {
    console.log(saveForm.value)
  }

}
