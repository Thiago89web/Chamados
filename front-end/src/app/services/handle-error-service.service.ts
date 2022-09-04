import { HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ToastrService } from 'ngx-toastr';

@Injectable({
  providedIn: 'root'
})
export class HandleErrorService {

  constructor(private toast: ToastrService) {}

  // Handling HTTP Errors using Toaster
  public handleError(err: HttpErrorResponse) {
    
    let errorMessage: string;
    if (err.error instanceof ErrorEvent) {
      // Ocorreu um erro do lado do cliente ou da rede. Trate-o em conformidade.
      errorMessage = `Ocorreu um erro: ${err.error.message}`;
    } else {
      switch (err.status) {
        case 400:
          errorMessage = "Bad Request.";
          break;
        case 401:
         // errorMessage = "You need to log in to do this action.";
         errorMessage = "Usuário e/ou senha inválidos.";
          break;
        case 403:
          errorMessage = "You don't have permission to access the requested resource.";
          break;
        case 404:
          errorMessage = "The requested resource does not exist.";
          break;
        case 412:
          errorMessage = "Precondition Failed.";
          break;
        case 500:
          errorMessage = "Internal Server Error.";
          break;
        case 503:
          errorMessage = "The requested service is not available.";
          break;
        case 422:
          errorMessage = "Validation Error!";
          break;
        default:
          errorMessage = "Something went wrong!";
      }
    }

    /*if (errorMessage) {
      console.error(errorMessage);
    }*/

    //console.error("Teste "+err.error.error);
    if (err.status == 400 && err.error.error == 'Validation error') { //errors.fieldName
      console.error(errorMessage);
      err.error.errors.forEach(element => {
        if(element.fieldName != null) {
          //console.log(element.message);
          this.toast.error(element.message);
        }    
      });

    }
    
    if (err.status == 400 && err.error.error == 'Violacao de dados') {
      console.error(errorMessage);
      this.toast.error(err.error.message);
    }
    
    if(err.status == 401){
      console.error(errorMessage);
      this.toast.error('Email e/ou senha inválidos!');
    }

  }

}
