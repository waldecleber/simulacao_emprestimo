import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ClienteService } from './services/cliente.service';
import { ClientesListComponent } from './view/clientes-list/clientes-list.component';
import { ClientesFormComponent } from './view/clientes-form/clientes-form.component';
import { EmprestimoFormComponent } from './view/emprestimo-form/emprestimo-form.component';


const routes: Routes = [
  {
    path: '', component: ClientesListComponent
  },
  { 
    path: 'cliente-form', component: ClientesFormComponent 
  },
  { 
    path: 'cliente-form/:id', component: ClientesFormComponent 
  },
  { 
    path: 'emprestimo-form/:id', component: EmprestimoFormComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  providers: [ClienteService]
})
export class AppRoutingModule { }
