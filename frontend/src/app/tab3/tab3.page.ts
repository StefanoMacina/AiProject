import { Component, inject } from '@angular/core';
import { ExploreContainerComponent } from '../explore-container/explore-container.component';
import {IonicModule} from '@ionic/angular'
import { AuthService } from '../auth/auth.service';
import { UserService } from '../services/service.service';
import { UserDto } from '../models/user.interface';
import { CommonModule } from '@angular/common';

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
    this.$authService.logout().subscribe(() => {});
  }

  constructor() {}
}
