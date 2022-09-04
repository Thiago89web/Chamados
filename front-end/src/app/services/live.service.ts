import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { API_CONFIG } from '../config/api.config';
import { Live } from '../models/Live';
import { ResponsePageable } from '../models/ResponsePageable';

@Injectable({
  providedIn: 'root'
})
export class LiveService {
  [x: string]: any;

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(private httpClient: HttpClient) { }

  public getLives(): Observable<ResponsePageable> {
    return this.httpClient.get<ResponsePageable>(API_CONFIG.baseUrl+'/lives');
}

  public getLivesWithFlag(): Observable<ResponsePageable> {
      return this.httpClient.get<ResponsePageable>(API_CONFIG.baseUrl+'/lives');
  }

  public postLives(live: any): Observable<Live> {
      return this.httpClient.post<any>(API_CONFIG.baseUrl+'/lives', live, this.httpOptions);
  }
}
