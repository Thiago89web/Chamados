import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Chamado } from 'src/app/models/chamado';
import { Colaborador } from 'src/app/models/colaboradores';
import { ChamadoService } from 'src/app/services/chamado.service';
import { ColaboradorService } from 'src/app/services/colaborador.service';
import { StorageService } from 'src/app/services/StorageService';

@Component({
  selector: 'app-create',
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.css']
})
export class CreateComponent implements OnInit {

  chamado: Chamado = {
    titulo: '',
    descricao: '',  
    finalizado: false,
    colaborador: ''
  }

  //colaborador: Colaborador[] = []

  fileName = '';

  titulo: FormControl = new FormControl(null, [Validators.required]);
  descricao: FormControl = new FormControl(null, [Validators.minLength(10)]);



  constructor(private service: ChamadoService, 
    private router: Router,
    private toast: ToastrService,
    private storage: StorageService,
    private colaboradorService: ColaboradorService) { }

  ngOnInit(): void {
    this.colaboradorService.findByEmail(this.storage.getLocalUser().email).subscribe(
      resposta => {             
        this.chamado.colaborador = resposta.id;            
      })
    
  }

  create(): void {
    //this.formataData();
    this.service.create(this.chamado).subscribe(() => {
      
      this.toast.success('Chamado criado com sucesso!', 'Sucesso');
      this.router.navigate(['listar']);
    }, () => {
      this.toast.error('Falha ao criar o chamado!', 'Erro');
    })
  }

  cancel(): void {
    this.router.navigate(['listar'])
  }

  /*formataData(): void {
    let data = new Date(this.chamado.dataParaFinalizar)
    this.chamado.dataParaFinalizar = `${data.getDate()}/${data.getMonth() + 1}/${data.getFullYear()}`
  }*/

  

  onFileSelected(event: any) {
    const file:File = event.target.files[0];
    if (file) {
        this.fileName = file.name;
        const formData = new FormData();
        formData.append("thumbnail", file);
       // const upload$ = this.http.post("/api/thumbnail-upload", formData);
       // upload$.subscribe();
    }
  }

  validaCampos(): boolean {
    return this.titulo.valid && this.descricao.valid       
  }

}
