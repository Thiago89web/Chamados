import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { API_CONFIG } from '../config/api.config';
import { Colaborador } from '../models/colaboradores';

@Injectable({
  providedIn: 'root'
})
export class ColaboradorService {

  constructor(private http: HttpClient) { }

  findById(id: any): Observable<Colaborador> {    
    return this.http.get<Colaborador>(`${API_CONFIG.baseUrl}/colaboradores/${id}`);
  }

  findByEmail(email: string) {    
    return this.http.get<Colaborador>(`${API_CONFIG.baseUrl}/colaboradores/email?value=${email}`);
  }

  create(colaborador: Colaborador): Observable<Colaborador> {
    return this.http.post<Colaborador>(API_CONFIG.baseUrl+'/colaboradores/cadastro',colaborador);
  }

  update(chamado: Colaborador):Observable<Colaborador>{
    return this.http.put<Colaborador>(`${API_CONFIG.baseUrl}/colaboradores/${chamado.id}`, chamado);
  }
}
