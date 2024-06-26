import { Component, inject } from '@angular/core';
import { ExploreContainerComponent } from '../explore-container/explore-container.component';
import {IonicModule} from '@ionic/angular'
import { UserService } from '../services/service.service';
import { UserDto } from '../models/user.interface';
import { CommonModule } from '@angular/common';
import { AuthService } from '../auth/authService/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-tab3',
  templateUrl: 'tab3.page.html',
  styleUrls: ['tab3.page.scss'],
  standalone: true,
  imports: [ExploreContainerComponent, IonicModule,CommonModule],
})
export class Tab3Page {

  $authService=inject(AuthService);
  $service=inject(UserService);
  $router=inject(Router);
  user! : UserDto;

  ngOnInit(): void {
    this.$service.getUserInfo().subscribe(
      data => {
        this.user = data
        //console.log(data)
      },
      error => console.error("failed to fetch user info")
    ) 
  }

  logout(){
    this.$authService.logout().subscribe(() => {
      
    });
    this.$router.navigate(['/login'])
  }

  constructor() {}
}
