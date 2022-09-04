import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { API_CONFIG } from '../config/api.config';
import { Motivo } from '../models/motivo';

@Injectable({
  providedIn: 'root'
})
export class MotivoService {

  constructor(private http: HttpClient) { }

  findAll(): Observable<Motivo[]> {
    return this.http.get<Motivo[]>(`${API_CONFIG.baseUrl}/motivos`);
  }

}
