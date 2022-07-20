import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './auth/auth.guard';
import { CadastroComponent } from './components/cadastro/cadastro.component';
import { CreateComponent } from './components/chamado/create/create.component';
import { FinalizadosComponent } from './components/chamado/finalizados/finalizados.component';
import { VisualizarFinalizadosComponent } from './components/chamado/finalizados/visualizar-finalizados/visualizar-finalizados.component';
import { ListarComponent } from './components/chamado/listar/listar.component';
import { UpdateComponent } from './components/chamado/update/update.component';
import { VisualizarComponent } from './components/chamado/visualizar/visualizar.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';

const routes: Routes = [
 
  { path: 'login', component: LoginComponent},
  { path: 'cadastro', component: CadastroComponent},
  { path: '', component: HomeComponent, canActivate: [AuthGuard] },
  { path: 'listar', component: ListarComponent, canActivate: [AuthGuard] },
  { path: 'finalizados', component: FinalizadosComponent, canActivate: [AuthGuard] },
  { path: 'create', component: CreateComponent, canActivate: [AuthGuard] },
  { path: 'listar/update/:id', component: UpdateComponent, canActivate: [AuthGuard] },
  { path: 'listar/visualizar/:id', component: VisualizarComponent, canActivate: [AuthGuard] },
  { path: 'finalizados/visualizar/:id', component: VisualizarComponent, canActivate: [AuthGuard] },
  { path: 'finalizados/visualizar-finalizados/:id', component: VisualizarFinalizadosComponent, canActivate: [AuthGuard] }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
