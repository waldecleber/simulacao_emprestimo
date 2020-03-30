import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule }   from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { NgxCurrencyModule } from "ngx-currency";

import { AppComponent } from './app.component';

import { ClienteService } from './services/cliente.service';
import { EmprestimoService } from './services/emprestimo.service';
import { ClientesListComponent } from './view/clientes-list/clientes-list.component';
import { TopBarComponent } from './view/top-bar/top-bar.component';
import { ClientesFormComponent } from './view/clientes-form/clientes-form.component';
import { EmprestimoFormComponent } from './view/emprestimo-form/emprestimo-form.component';

@NgModule({
  declarations: [
    AppComponent,
    ClientesListComponent,
    TopBarComponent,
    ClientesFormComponent,
    EmprestimoFormComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    AppRoutingModule,
    NgxCurrencyModule
  ],
  providers: [ClienteService, EmprestimoService],
  bootstrap: [AppComponent]
})
export class AppModule { }
