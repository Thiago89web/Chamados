import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { API_CONFIG } from '../config/api.config';
import { Credenciais } from '../models/credenciais';
import { LocalUser } from '../models/LocalUser';
import { StorageService } from './StorageService';


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  jwtHelper: JwtHelperService = new JwtHelperService();

  constructor(private http: HttpClient, private storage: StorageService) { }

  authenticate(creds: Credenciais) {
    return this.http.post(`${API_CONFIG.baseUrl}/login`, creds, {
      observe: 'response',
      responseType: 'text'
    })
  }

  /*successfulLogin(authToken: string) {
    localStorage.setItem('token', authToken);
  }*/

  successfulLogin(authorizationValue : string) {
    localStorage.setItem('token', authorizationValue);
    //let tok = authorizationValue.substring(7);
    let tok = authorizationValue;
    let user : LocalUser = {
        token: tok,
        email: this.jwtHelper.decodeToken(tok).sub
    };
    this.storage.setLocalUser(user);
  }

  isAuthenticated() {
    let token = localStorage.getItem('token')
    if(token != null) {
      return !this.jwtHelper.isTokenExpired(token);
    }
    return false;
  }

  logout() {
    localStorage.clear();
  }
}
