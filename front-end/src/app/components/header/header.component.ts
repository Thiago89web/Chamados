import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { ColaboradorService } from 'src/app/services/colaborador.service';
import { StorageService } from 'src/app/services/StorageService';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  //private roles: string[] = [];
  //isLoggedIn = false;
  //showAdminBoard = false;
  //showModeratorBoard = false;
  username: string;
  email: string;

  constructor( private router: Router, 
    private serve: AuthService, 
    private storage: StorageService,
    private colaboradorService: ColaboradorService) { }

  ngOnInit(): void {
     this.loadDados();
       
  }

  loadDados(){
    this.colaboradorService.findByEmail(this.storage.getLocalUser().email).subscribe(
      resposta => {             
          this.username = resposta.nome;
          this.email = resposta.email;            
      }
    )
  }

  logout(): void {
    this.serve.logout();
    this.router.navigate(['login']);
  }

}
