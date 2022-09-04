import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Chamado } from 'src/app/models/chamado';
import { ChamadoService } from 'src/app/services/chamado.service';

@Component({
  selector: 'app-visualizar-finalizados',
  templateUrl: './visualizar-finalizados.component.html',
  styleUrls: ['./visualizar-finalizados.component.css']
})
export class VisualizarFinalizadosComponent implements OnInit {

  chamado: Chamado = {
    titulo: '',
    descricao: '',
    finalizado: false,
    motivo:  '',
    fileEntity:''
  }
  dataCriacao: any;
  fileInfos?: Observable<any>;

  constructor(private service: ChamadoService, private router: Router, private route: ActivatedRoute) { }

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
  }

  findFileId(){      
    this.fileInfos = this.service.getFileId(this.chamado.id);      
  }

  voltar(): void {
    this.router.navigate(['finalizados'])
  }

}
