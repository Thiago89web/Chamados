import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Colaborador } from 'src/app/models/colaboradores';
import { ColaboradorService } from 'src/app/services/colaborador.service';

@Component({
  selector: 'app-cadastro',
  templateUrl: './cadastro.component.html',
  styleUrls: ['./cadastro.component.css']
})
export class CadastroComponent implements OnInit {

  colaborador: Colaborador = {
    nome: '',
    email: '',
    senha: ''
  }

  nome: FormControl = new FormControl(null, [Validators.required]);
  email: FormControl = new FormControl(null, [Validators.required]);
  senha: FormControl = new FormControl(null, [Validators.minLength(6)]);

  constructor(private router: Router, 
    private colaboradorService: ColaboradorService,
    private toast: ToastrService) { }

  ngOnInit(): void {
  }

  create(): void {
    //this.formataData();
    this.colaboradorService.create(this.colaborador).subscribe(() => {
      
      this.toast.success('Colaborador cadastrado com sucesso!', 'Sucesso');
      this.router.navigate(['login']);
    }, () => {
      //this.toast.error(ex.error.error);
      this.toast.error('Falha ao cadastrar colaborador!', 'Erro');
    })
  }

  cancel() {
    this.router.navigate(['login'])
  }

  validaCamposCad(): boolean {
    return this.nome.valid && this.email.valid  && this.senha.valid      
  }

}
