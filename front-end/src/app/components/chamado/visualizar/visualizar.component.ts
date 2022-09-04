import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Chamado } from 'src/app/models/chamado';
import { ChamadoService } from 'src/app/services/chamado.service';

@Component({
  selector: 'app-visualizar',
  templateUrl: './visualizar.component.html',
  styleUrls: ['./visualizar.component.css']
})
export class VisualizarComponent implements OnInit {

  chamado: Chamado = {
    titulo: '',
    descricao: '',
    finalizado: false,
    motivo:  '',
    fileEntity:''
  }
  dataCriacao: any;
  fileInfos?: Observable<any>;
  
  constructor(
    private service: ChamadoService, 
    private router: Router, 
    private route: ActivatedRoute,
    /* public dialogRef: MatDialogRef<VisualizarComponent>*/) { }

  ngOnInit(): void {
    this.chamado.id = this.route.snapshot.paramMap.get("id")!;
    this.findById();
    this.findFileId();
  }

  findById(): void{
    this.service.findById(this.chamado.id).subscribe((resposta) => {
      this.chamado = resposta;
      this.dataCriacao = resposta.dataCriacao;
    })
    //this.formataData();
  }

  voltar(): void {
    //this.dialogRef.close();
    this.router.navigate(['listar']);
  }

  findFileId(){      
    this.fileInfos = this.service.getFileId(this.chamado.id);    
  }

  /*formataData(): void {
    let data = new Date(this.chamado.dataParaFinalizar)
    this.chamado.dataParaFinalizar = `${data.getDate()}/${data.getMonth() + 1}/${data.getFullYear()}`
  }*/

}
