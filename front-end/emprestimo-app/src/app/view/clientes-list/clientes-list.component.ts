import { Component, OnInit, ElementRef, ViewChild } from '@angular/core';

  import { from } from 'rxjs';
import { ClienteService } from 'src/app/services/cliente.service';
import { Cliente } from 'src/app/model/cliente';

@Component({
  selector: 'app-clientes-list',
  templateUrl: './clientes-list.component.html',
  styleUrls: ['./clientes-list.component.css']
})
export class ClientesListComponent implements OnInit {

  clientes: Array<any>;  
  clienteSelecionado: Cliente;
  filtro: string;
  naoEncontrado: boolean;
  @ViewChild('closeModal') private closeModal: ElementRef;

  constructor( private clienteService: ClienteService) { }

  ngOnInit() {
    this.listar();
    this.clienteSelecionado = new Cliente();
  }

  listar() {
    this.clientes = new Array();
    
    if (typeof this.filtro != 'undefined' && this.filtro) {
      this.clienteService.search(this.filtro)
        .subscribe(dados => {          
          if (dados.length === 0) {
            this.naoEncontrado = true;            
          } else {
            this.clientes = dados;  
            this.naoEncontrado = false;
          }
        });
    } else {
      this.clienteService.listarClientes()
      .subscribe(dados => {
        this.clientes = dados;
        this.naoEncontrado = false;
      });
    }    
  }

  onSelectCliente(cliente: Cliente) {
    this.clienteSelecionado = cliente;    
  }

  deletarCliente() {
    this.clienteService.excluirCliente(this.clienteSelecionado)
      .subscribe(dados => {   
        this.listar();
      }, error => console.log(error));

      this.closeModal.nativeElement.click();     
  }

}
