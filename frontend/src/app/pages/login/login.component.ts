import { Component, OnInit, inject } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { IonicModule } from '@ionic/angular';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ReactiveFormsModule, RouterModule, IonicModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent  implements OnInit {

  router=inject(Router);

  loginForm : FormGroup;
  
  constructor() {
    this.loginForm = new FormGroup({
      username : new  FormControl('',[Validators.required]),
      password : new FormControl('',[Validators.required])
    })
   }

  onSubmit(){
    console.log(this.loginForm.value);
  }

  ngOnInit() {}

}
