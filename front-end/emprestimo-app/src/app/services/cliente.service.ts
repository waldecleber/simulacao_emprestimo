import { Injectable } from '@angular/core';

import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { Cliente } from '../model/cliente';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {
  
  url = environment.API + "/clientes";
  
  constructor(private http: HttpClient) { }
  
  listarClientes() {
    return this.http.get<any[]>(`${this.url}`);
  }

  search(nome: string) {
    return this.http.get<any[]>(`${this.url}/search/${nome}`);
  }

  buscarCliente(id: number) {
    return this.http.get<any>(`${this.url}/${id}`);
  }
  
  salvarCliente(cliente: Cliente) {    
    return this.http.post(this.url, cliente);
  }

  calcularRisco(rendaMensal: number) {
    let params = new HttpParams();
    params = params.append("rendimentoMensal", rendaMensal.toString());
    const headers = new HttpHeaders().set('Content-Type', 'text/plain; charset=utf-8');
    const options = {headers, params, responseType: 'text' as 'text'};
    
    return this.http.get(`${this.url}/risco`, options);
  }

  excluirCliente(clienteSelecionado: Cliente) {
    return this.http.delete(`${this.url}/${clienteSelecionado.id}`);
  }

  


}
