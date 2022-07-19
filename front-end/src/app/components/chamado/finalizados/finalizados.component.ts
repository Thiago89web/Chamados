import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Chamado } from 'src/app/models/chamado';
import { ChamadoService } from 'src/app/services/chamado.service';
import { ColaboradorService } from 'src/app/services/colaborador.service';
import { StorageService } from 'src/app/services/StorageService';

@Component({
  selector: 'app-finalizados',
  templateUrl: './finalizados.component.html',
  styleUrls: ['./finalizados.component.css']
})
export class FinalizadosComponent implements OnInit {

  listfinishd: Chamado[] = [];
  pag: any ;
  total: any;
 
  constructor(
    private service: ChamadoService, 
    private router: Router,
    private toast: ToastrService,
    private colaboradorService: ColaboradorService,
    private storage: StorageService) { }

  ngOnInit(): void {
    this.findAllClose();
  }

  findAllClose(): void{
    this.colaboradorService.findByEmail(this.storage.getLocalUser().email).subscribe(resposta => {
    this.service.findAllClose(resposta.id).subscribe(resp =>{   
      this.listfinishd = resp.content;
      this.total = resp.totalElements;
    })
  })
  }

  voltar(): void{
    this.router.navigate(['listar'])
  }

  PageChange(event: any): void {
    this.pag = event;  
  }

  delete(id: any, index: any): void{
    this.service.delete(id).subscribe((resposta) => {
     if(resposta === null){
       this.toast.success('Chamado deletado com sucesso!', 'Sucesso');
       this.listfinishd.splice(index, 1);
       this.listfinishd = this.listfinishd.filter(chamado => chamado.id !== id);
       this.reloadPage();
     }
    })
 }

 carregarPagina(pagina:any){
  this.colaboradorService.findByEmail(this.storage.getLocalUser().email).subscribe(resposta => {
    this.service.getChamadoPageClose(pagina - 1, resposta.id).subscribe(resposta =>{
      this.listfinishd = resposta.content;
      this.total = resposta.totalElements;
    })
  })
  }

  reloadPage(): void{
    if(this.listfinishd.length === 0 || this.listfinishd.length === 4){   
      window.location.reload();
    }
  }

}
