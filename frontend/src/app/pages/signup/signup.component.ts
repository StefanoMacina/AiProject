import { Component, OnInit, inject } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { IonicModule } from '@ionic/angular';
import { AuthService } from 'src/app/auth/auth.service';
import { RegistrationRequest } from 'src/app/models/user.interface';


@Component({
  selector: 'app-signup',
  standalone: true,
  imports : [ReactiveFormsModule, RouterModule, IonicModule],
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss'],
})
export class SignupComponent  implements OnInit {

  authService = inject(AuthService);
  router = inject(Router);

  signupForm : FormGroup;

  constructor() { 
    this.signupForm = new FormGroup({
      firstname : new FormControl('', [Validators.required]),
      lastname : new FormControl('', [Validators.required]),
      username : new FormControl('', [Validators.required]),
      email : new FormControl('', [Validators.required, Validators.email]),
      password : new FormControl('', [Validators.required]),
      birthdate : new FormControl(''),
      gender : new FormControl('', Validators.required),
      policy:new FormControl(false,Validators.requiredTrue)
    })
  }

  onSubmit(){
    console.log(this.signupForm.value)
    if(this.signupForm.valid){
      console.log("successs")
      const data : RegistrationRequest = {
        ...this.signupForm.value,
        role: 'USER'
      }

      this.authService.signUp(data)
      .subscribe({
        next : (data) => this.router.navigate(['/login']),
        error: (err) => console.log(err)
      })
    }
  }

  ngOnInit() {}

}
