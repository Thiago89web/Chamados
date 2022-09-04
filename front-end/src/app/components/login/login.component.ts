import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Credenciais } from 'src/app/models/credenciais';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  form: Credenciais = {
    email: '',
    senha: ''
  }

  showSpinner = false;

  username = new FormControl(null, Validators.minLength(3));
  senha = new FormControl(null, Validators.minLength(6));

  constructor(
    private toast: ToastrService,
    private service: AuthService,
    private router: Router) { }

  ngOnInit(): void {
   
  }

  logar() {

    this.service.authenticate(this.form).subscribe(resposta => {     
      this.service.successfulLogin(resposta.headers.get('Authorization').substring(7));     
      this.toast.success('Login realizado com sucesso!')
      this.loadSpinner();
      this.router.navigate([''])
      
    }, () => {
      ///this.toast.error('Usuário inválidos');     
    })
  }

  /*validaCampos(): boolean {
    return this.username.valid && this.senha.valid
  }*/


  loadSpinner(){
    this.showSpinner = true
    setTimeout(()=>{
      this.showSpinner = false;
    }, 8000);
  }

}
