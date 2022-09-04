import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Observable } from 'rxjs';
import { Chamado } from 'src/app/models/chamado';
import { Motivo } from 'src/app/models/motivo';
import { ChamadoService } from 'src/app/services/chamado.service';
import { MotivoService } from 'src/app/services/motivo.service';

@Component({
  selector: 'app-update',
  templateUrl: './update.component.html',
  styleUrls: ['./update.component.css']
})
export class UpdateComponent implements OnInit {

  chamado: Chamado = {
    titulo: '',
    descricao: '',
    colaborador: '',
    finalizado: false,
    motivo: '',
    fileEntity: ''
  }
  fileInfos?: Observable<any>;
  motivos: Motivo[] = []

  constructor(private service: ChamadoService, 
    private router: Router, 
    private route: ActivatedRoute,
    private toast: ToastrService,
    private motivoService: MotivoService) { }

  ngOnInit(): void {
    this.chamado.id = this.route.snapshot.paramMap.get("id")!;
    this.findById();
    this.findAllMotivos();
    this.findFileId(this.chamado.id);
  }

  findById(): void{
    this.service.findById(this.chamado.id).subscribe((resposta) => {
      this.chamado = resposta;
      this.chamado.colaborador =  resposta.colaborador.id;     
      this.chamado.motivo = resposta.motivo.id;
      this.chamado.fileEntity = resposta.fileEntity.id
      //this.findFileId(this.chamado.id);
    })
  }

  update(): void{
    this.service.update(this.chamado).subscribe(() => {
    this.toast.success('Chamado atualizado com sucesso!', 'Sucesso');
    this.router.navigate(['listar']);
    }, error => {      
      this.toast.error('Falha ao atualizar o chamado!', 'Error');      
    })
  }

  cancel(): void {
    this.router.navigate(['listar'])
  }

  findAllMotivos(): void {
    this.motivoService.findAll().subscribe(resposta => {
      this.motivos = resposta;
    })
  }

  findFileId(id: any){      
    this.fileInfos = this.service.getFileId(id);   
  }

 /* formataData(): void {
    let data = new Date(this.chamado.dataParaFinalizar)
    this.chamado.dataParaFinalizar = `${data.getDate()}/${data.getMonth() + 1}/${data.getFullYear()}`
  }*/
}