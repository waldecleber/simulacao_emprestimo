import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class EmprestimoService {

  url = environment.API + "/emprestimos";
  
  constructor(private http: HttpClient) { }

  calcularJuros(idCliente: number) {    
    return this.http.get<any>(`${this.url}/calcularJuros/${idCliente}`);
  }
}
