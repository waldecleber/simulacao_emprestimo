import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Cliente } from 'src/app/model/cliente';
import { Emprestimo } from 'src/app/model/emprestimo';
import { ClienteService } from 'src/app/services/cliente.service';
import { EmprestimoService } from 'src/app/services/emprestimo.service';


@Component({
  selector: 'app-emprestimo-form',
  templateUrl: './emprestimo-form.component.html',
  styleUrls: ['./emprestimo-form.component.css']
})
export class EmprestimoFormComponent implements OnInit {

  cliente: Cliente;
  emprestimo: Emprestimo;
  taxaJuros: number;
  totalParcelas: number;
  totalFinanciado: number;

  constructor(
      private route: ActivatedRoute,
      private router: Router,
      private clienteService: ClienteService,
      private emprestimoService: EmprestimoService
    ) { }

  ngOnInit(): void {
    let id = this.route.snapshot.paramMap.get('id');
    if(id != null) {
      this.clienteService.buscarCliente(+id)
        .subscribe(data => { 
          this.cliente = data;
      },
      error => {
         console.log(error);
      });
    }
    this.cliente = new Cliente();
    this.emprestimo = new Emprestimo();
  }

  calcularJuros() {
    this.emprestimoService.calcularJuros(this.cliente.id)
      .subscribe(dados => {
        this.taxaJuros = dados;
        this.calcularParcelas();
      });
  }

  calcularParcelas() {    
    let parcela = (this.emprestimo.vlrEmprestimo / this.emprestimo.duracao);
    this.totalParcelas = parcela + (parcela * this.taxaJuros/100);
    this.totalFinanciado = this.emprestimo.duracao * this.totalParcelas;
  }

}
