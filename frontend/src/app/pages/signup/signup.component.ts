import { Component, OnInit, inject } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { IonButton, IonCheckbox, IonContent, IonDatetime, IonDatetimeButton, IonItem, IonLabel, IonModal } from '@ionic/angular/standalone';

import { IonInput } from '@ionic/angular/standalone';
import { AuthService } from 'src/app/auth/auth.service';
import { User } from 'src/app/models/user.interface';

@Component({
  selector: 'app-signup',
  standalone: true,
  imports : [ReactiveFormsModule, RouterModule, IonInput, IonDatetimeButton, IonModal,IonButton, IonCheckbox, IonContent, IonItem, IonLabel],
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
      birthdate : new FormControl('')
    })
  }


  onSubmit(){
    
    if(this.signupForm.valid){

      const data : User = {
        ...this.signupForm.value,
        role: 'user'
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
