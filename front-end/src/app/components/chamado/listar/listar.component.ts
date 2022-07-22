import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Chamado } from 'src/app/models/chamado';
import { ChamadoService } from 'src/app/services/chamado.service';
import { ColaboradorService } from 'src/app/services/colaborador.service';
import { StorageService } from 'src/app/services/StorageService';

@Component({
  selector: 'app-listar',
  templateUrl: './listar.component.html',
  styleUrls: ['./listar.component.css']
})
export class ListarComponent implements OnInit {

   list: Chamado[] = [];
   chamado: Chamado = {
    id: '',
    titulo: '',
    descricao: '',
    finalizado: false,
    colaborador: ''
   }
  prefil: any;
  showAdmin = false;
  showColaborador = false;
   total: any;
   pag: any;

   /*numPagina = [ 5, 10, 20, 30, 60 ];
   nPage: any;*/
   
 
   constructor(
    private service: ChamadoService, 
    private router: Router,
    private toast: ToastrService,
    private colaboradorService: ColaboradorService,
    private storage: StorageService) { }
 
   ngOnInit(): void {   
     this.findOpen() 
   }
 
   /*findAllOpenAdm(): void{
     this.service.findAllOpenAdm().subscribe(data =>{   
       this.list = data.content;
       this.total = data.totalElements;       
     })
   }*/

   findOpen(): void{  //teste
    this.colaboradorService.findByEmail(this.storage.getLocalUser().email).subscribe(resposta => { 
      
      //Puxando prefil do usuário
      this.prefil = resposta.perfis;      
      if(this.prefil == 'COLABORADOR'){            
        this.showColaborador = true;
      }else{
        this.showAdmin = true
      }
     
    this.service.findOpen(resposta.id).subscribe(resp => {
   
    this.list =  resp.content
    this.total = resp.totalElements      
    })
    
    })
    
   } 
   
   finalizar(item: Chamado):void{
      
    this.chamado.id = item.id;
    this.chamado.titulo = item.titulo;
    this.chamado.descricao = item.descricao;
    this.chamado.finalizado = item.finalizado = true;
    this.chamado.colaborador = item.colaborador.id;
       
     this.service.update(this.chamado).subscribe(() => {
       this.toast.success('Chamado finalizado com sucesso!', 'Sucesso');
       this.list = this.list.filter(chamado => chamado.id !== item.id);
       this.reloadPage();
      })        
     
   }
 
    delete(id: any, index: any): void{
      this.service.delete(id).subscribe((resposta) => {
       if(resposta === null){
        this.toast.success('Chamado deletado com sucesso!', 'Sucesso');
         this.list.splice(index, 1);
         this.list = this.list.filter(chamado => chamado.id !== id);
         this.reloadPage();
       }
      })
          
   }
 
   navegarParaFinalizados():void{
     this.router.navigate(['finalizados'])
   }
   
  
   /*carregarPagina(pagina:any){ 
    this.colaboradorService.findByEmail(this.storage.getLocalUser().email).subscribe(resposta => {   
      resposta.id 
     this.service.getChamadoPageOpen(pagina - 1).subscribe(data =>{
     this.list = data.content;
     this.total = data.totalElements;      
   });
  })
  }*/

  carregarPagina(pagina:any){ 
    this.colaboradorService.findByEmail(this.storage.getLocalUser().email).subscribe(resposta => {   
       
     this.service.getChamadoPageOpen(pagina - 1, resposta.id ).subscribe(data =>{
     this.list = data.content;
     this.total = data.totalElements;    
   })
  })
  }

  /*numPages(event: any):void {
    this.nPage = event.target.value;
    console.log(this.nPage);
  }*/

  reloadPage(): void{
    if(this.list.length === 0 || this.list.length === 4){
      window.location.reload();
    }
  }

}
