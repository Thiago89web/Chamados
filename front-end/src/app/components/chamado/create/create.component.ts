import { HttpEventType, HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Chamado } from 'src/app/models/chamado';
import { Motivo } from 'src/app/models/motivo';
import { ChamadoService } from 'src/app/services/chamado.service';
import { ColaboradorService } from 'src/app/services/colaborador.service';
import { MotivoService } from 'src/app/services/motivo.service';
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
    colaborador: '',
    motivo: '',
    fileEntity: ''
  }

  motivos: Motivo[] = []

  currentFile?: File;
  progress = 0;
  message = '';
  fileName = 'Selecione o arquivo';

  //motivo: FormControl = new FormControl(null, [Validators.required]);
  descricao: FormControl = new FormControl(null, [Validators.maxLength(255)]);

  constructor(private service: ChamadoService, 
    private router: Router,
    private toast: ToastrService,
    private storage: StorageService,
    private colaboradorService: ColaboradorService,
    private motivoService: MotivoService) { }

  ngOnInit(): void {
    this.findIdColaborador();
    this.findAllMotivos();
    //this.fileInfos = this.service.getFiles();
  }

  create(): void {
    //this.formataData();
    this.service.create(this.chamado).subscribe(() => {
      this.toast.success('Chamado criado com sucesso!', 'Sucesso');
      this.router.navigate(['listar']);
    }, () => {
      this.toast.error('Falha ao criar o chamado!', 'Erro');
    });
  }

  findIdColaborador(){
    this.colaboradorService.findByEmail(this.storage.getLocalUser().email).subscribe(
      resposta => {  this.chamado.colaborador = resposta.id;  });
  }

  findAllMotivos(): void {
    this.motivoService.findAll().subscribe(resposta => {
      this.motivos = resposta;
    });
  }

  cancel(): void {
    this.router.navigate(['listar']);
  }

  /*formataData(): void {
    let data = new Date(this.chamado.dataParaFinalizar)
    this.chamado.dataParaFinalizar = `${data.getDate()}/${data.getMonth() + 1}/${data.getFullYear()}`
  }*/

  /*inputFileChange(event) {
    if (event.target.files && event.target.files[0]) {

      const uploadFile = event.target.files[0];

      const formData = new FormData();
      formData.append('file', uploadFile);
      
      this.service.upload(formData).subscribe(() => {
      })
    }
  }*/

  selectFile(event: any): void {
    if (event.target.files && event.target.files[0]) {
      const file: File = event.target.files[0];
      this.currentFile = file;
      this.fileName = this.currentFile.name;
      this.upload();
    } else {
      this.fileName = 'Selecione o arquivo';
    }
  }

  upload(): void {
    this.message = "";
    if (this.currentFile) {
      
      this.service.upload(this.currentFile).subscribe((event: any) => {
           if (event instanceof HttpResponse) {
            this.message = event.body.message;           
            this.chamado.fileEntity = event.body.idFile;         
          }
        },(err: any) => {
          console.log(err);
          if (err.error && err.error.message) {
            this.message = err.error.message;
          } else {
            this.message = 'Não foi possível fazer upload do arquivo!';
          }
          this.currentFile = undefined;
        });
    
    }
  }

  validaCampos(): boolean {
    //return this.motivo.valid && this.descricao.valid 

    return this.descricao.valid       
  }

}
