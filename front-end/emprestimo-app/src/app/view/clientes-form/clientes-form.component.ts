import { Component, OnInit, AfterViewChecked } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';


import * as $ from 'jquery';
import { Cliente } from 'src/app/model/cliente';
import { ClienteService } from 'src/app/services/cliente.service';

@Component({
  selector: 'app-clientes-form',
  templateUrl: './clientes-form.component.html',
  styleUrls: ['./clientes-form.component.css']
})
export class ClientesFormComponent implements OnInit {

  cliente: Cliente;
  risco: string;

  constructor(
      private route: ActivatedRoute,
      private router: Router,
      private clienteService: ClienteService
    ) { }

  ngOnInit(): void {
    this.cliente = new Cliente();
    let id = this.route.snapshot.paramMap.get('id');
    if(id != null) {
      this.clienteService.buscarCliente(+id)
        .subscribe(data => { 
          this.cliente = data;
      },
      error => {
         console.log(error);
      });
    } else {
      this.cliente.tipo = "";
    }
  }

  calcularRisco() {
    this.clienteService.calcularRisco(this.cliente.rendimentoMensal)
      .subscribe(risco => { 
        this.cliente.risco = risco;
      },
      error => {
         console.log(error);
      });
  }

  salvarCliente() {
    this.clienteService.salvarCliente(this.cliente).subscribe(resp => {
      this.router.navigate(['/']);
    });
  }

  limparCamposTipoCliente() {
    this.cliente.vlrDividasAtuais = null;
    this.cliente.vlrTotalPatrimonio = null;
    this.cliente.empregado = null;
  }

}
