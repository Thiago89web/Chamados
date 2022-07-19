import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { API_CONFIG } from '../config/api.config';
import { Chamado } from '../models/chamado';

@Injectable({
  providedIn: 'root'
})
export class ChamadoService {


  constructor(private http: HttpClient) {}

  /*findAllOpenAdm(): Observable<any> {   
    return this.http.get<Chamado[]>(API_CONFIG.baseUrl+'/chamados/openAdm');
  }*/

  findOpen(colaborador: any): Observable<any> {  
    return this.http.get<Chamado[]>(`${API_CONFIG.baseUrl}/chamados/openColaborador?value=${colaborador}`);
  }

  findAllClose(colaborador: any): Observable<any> {  
    return this.http.get<Chamado[]>(`${API_CONFIG.baseUrl}/chamados/close?value=${colaborador}`);
  }

  getChamadoPageOpen(pagina:any, colaborador:any): Observable<any> {   
    return this.http.get<Chamado[]>(`${API_CONFIG.baseUrl}/chamados/pageOpen/${pagina}?colaborador=${colaborador}`);
  }


  getChamadoPageClose(pagina:any, colaborador:any): Observable<any> {   
    return this.http.get<Chamado[]>(`${API_CONFIG.baseUrl}/chamados/pageClose/${pagina}?colaborador=${colaborador}`);
  }

  findById(id: any): Observable<Chamado> {    
    return this.http.get<Chamado>(`${API_CONFIG.baseUrl}/chamados/${id}`);
  }

  create(chamado: Chamado): Observable<Chamado> {
    return this.http.post<Chamado>(API_CONFIG.baseUrl+'/chamados',chamado);
  }

  update(chamado: Chamado):Observable<Chamado>{
    return this.http.put<Chamado>(`${API_CONFIG.baseUrl}/chamados/${chamado.id}`, chamado);
  }

  delete(id: any): Observable<void>{
    return this.http.delete<void>(`${API_CONFIG.baseUrl}/chamados/${id}`);
  }

}
