import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { MatFormFieldModule } from '@angular/material/form-field';
import {MatSelectModule} from '@angular/material/select';

import { ToastrModule } from 'ngx-toastr';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { ColaboradorComponent } from './components/colaborador/colaborador.component';
import { HomeComponent } from './components/home/home.component';
import { CreateComponent } from './components/chamado/create/create.component';
import { UpdateComponent } from './components/chamado/update/update.component';
import { VisualizarComponent } from './components/chamado/visualizar/visualizar.component';
import { ListarComponent } from './components/chamado/listar/listar.component';
import { FinalizadosComponent } from './components/chamado/finalizados/finalizados.component';
import { LoginComponent } from './components/login/login.component';
import {MatInputModule} from '@angular/material/input';
import {MatButtonModule} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import {MatMenuModule} from '@angular/material/menu';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatCardModule} from '@angular/material/card';
import { NgxPaginationModule } from 'ngx-pagination';
import { MatDatepickerModule } from "@angular/material/datepicker";
import { MatNativeDateModule } from "@angular/material/core";
import { VisualizarFinalizadosComponent } from './components/chamado/finalizados/visualizar-finalizados/visualizar-finalizados.component';
import { AuthInterceptorProvider } from './interceptor/auth.interceptor';
import { StorageService } from './services/StorageService';
import { CadastroComponent } from './components/cadastro/cadastro.component';
import { ErrorInterceptorProvider } from './interceptor/error-interceptor';
import {MatTooltipModule} from '@angular/material/tooltip';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import {MatListModule} from '@angular/material/list';
import {MatDialogModule} from '@angular/material/dialog';
import {MatDividerModule} from '@angular/material/divider';
import {MatGridListModule} from '@angular/material/grid-list';
import { FlexLayoutModule } from '@angular/flex-layout';
import {MatChipsModule} from '@angular/material/chips';
import {MatTabsModule} from '@angular/material/tabs';
import { LocalDateTimePipe } from './pipe/local-date-time.pipe';
import { LiveFormDialogComponent } from './components/live-form-dialog/live-form-dialog.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    ColaboradorComponent,
    HomeComponent,
    CreateComponent,
    UpdateComponent,
    VisualizarComponent,
    ListarComponent,
    FinalizadosComponent,
    LoginComponent,
    VisualizarFinalizadosComponent,
    CadastroComponent,
    LocalDateTimePipe,
    LiveFormDialogComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    MatInputModule,
    MatFormFieldModule,
    MatSelectModule,
    MatButtonModule,
    MatIconModule,
    MatMenuModule,
    MatToolbarModule,
    MatCardModule,
    NgxPaginationModule,
    MatNativeDateModule,
    MatDatepickerModule,
    MatTooltipModule,
    MatProgressSpinnerModule,
    MatProgressBarModule,
    MatListModule,
    MatDialogModule,
    MatDividerModule,
    MatGridListModule,
    FlexLayoutModule,
    MatChipsModule,
    MatTabsModule,

    ToastrModule.forRoot({
      timeOut: 4000,
      closeButton: true,
      progressBar: true
    }),
  ],
  providers: [
    AuthInterceptorProvider, 
    StorageService, 
    ErrorInterceptorProvider, 
    LocalDateTimePipe],
    
  bootstrap: [AppComponent]
})
export class AppModule { }
