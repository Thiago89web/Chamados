import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Chamado } from 'src/app/models/chamado';
import { ChamadoService } from 'src/app/services/chamado.service';
import { ColaboradorService } from 'src/app/services/colaborador.service';
import { StorageService } from 'src/app/services/StorageService';

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
    
    finalizado: false
  }

  constructor(private service: ChamadoService, 
    private router: Router, 
    private route: ActivatedRoute,
    private toast: ToastrService,
    private colaboradorService: ColaboradorService,
    private storage: StorageService) { }

  ngOnInit(): void {
    this.chamado.id = this.route.snapshot.paramMap.get("id")!;
    this.findById();
    this.findIdColaborador(this.storage.getLocalUser().email);
  }

  findById(): void{
    this.service.findById(this.chamado.id).subscribe((resposta) => {
      this.chamado = resposta;
         
    })
  }

  update(): void{
    //this.formataData();
    this.service.update(this.chamado).subscribe((resposta) => {
    this.toast.success('Chamado atualizado com sucesso!', 'Sucesso');
    this.router.navigate(['listar']);
    }, error => {
      
      this.toast.error('Falha ao atualizar o chamado!', 'Error');      
    })
  }

  findIdColaborador(email: any): void {
    this.colaboradorService.findByEmail(email).subscribe(resposta => {
      this.chamado.colaborador = resposta.id;
    })
  }

  cancel(): void {
    this.router.navigate(['listar'])
  }

 /* formataData(): void {
    let data = new Date(this.chamado.dataParaFinalizar)
    this.chamado.dataParaFinalizar = `${data.getDate()}/${data.getMonth() + 1}/${data.getFullYear()}`
  }*/
}