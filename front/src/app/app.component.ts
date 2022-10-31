import { Component, OnInit } from '@angular/core';
import { KeycloakService } from 'keycloak-angular';
import { KeycloakProfile } from 'keycloak-js';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  username?: string = '';

  constructor(private keycloak: KeycloakService) { }

  ngOnInit(): void {
      this.keycloak.loadUserProfile().then(
        (value: KeycloakProfile) => {
          this.username = value.firstName;
        }
      )
  }

  logout(): void {
    this.keycloak.logout()
  }
}
