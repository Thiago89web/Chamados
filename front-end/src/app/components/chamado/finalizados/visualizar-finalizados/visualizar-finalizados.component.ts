import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
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
    finalizado: false
  }
  dataCriacao: any;

  constructor(private service: ChamadoService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.chamado.id = this.route.snapshot.paramMap.get("id")!;
    this.findById();
    
  }

  findById(): void{
    this.service.findById(this.chamado.id).subscribe((resposta) => {
      this.chamado = resposta;
      this.dataCriacao = resposta.dataCriacao;
    })
    //this.formataData();
  }

  voltar(): void {
    this.router.navigate(['finalizados'])
  }

  /*formataData(): void {
    let data = new Date(this.chamado.dataParaFinalizar)
    this.chamado.dataParaFinalizar = `${data.getDate()}/${data.getMonth() + 1}/${data.getFullYear()}`
  }*/

}
