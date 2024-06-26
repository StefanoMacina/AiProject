import { Component, OnInit, inject } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { IonicModule } from '@ionic/angular';
import { AuthService } from 'src/app/auth/auth.service';
import { LoginRequest } from 'src/app/models/user.interface';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ReactiveFormsModule, RouterModule, IonicModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent  implements OnInit {

  $router=inject(Router);
  $authService=inject(AuthService)

  loginForm : FormGroup;
  
  constructor() {
    this.loginForm = new FormGroup({
      username : new  FormControl('',[Validators.required]),
      password : new FormControl('',[Validators.required])
    })
   }

  onSubmit(){
    const data = this.loginForm;
    if(data.valid){
      this.$authService.signin(data.value)
      .subscribe({
        next : () => this.$router.navigate(['/home']),
        error : (err) => console.log(err)
      });
      
    } 
  }

  ngOnInit() {}

}
