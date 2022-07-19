import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse, HTTP_INTERCEPTORS } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, throwError } from "rxjs";
import { catchError, retry } from 'rxjs/operators';
import { FieldMessage } from "../models/FieldMessage";
import { HandleErrorService } from "../services/handle-error-service.service";



@Injectable()
export class ErrorInterceptor implements HttpInterceptor {

    constructor(private error: HandleErrorService){}

    // intercept function
   intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {

    // returning an observable to complete the request cycle
    return new Observable((observer) => {
      next.handle(req).subscribe(
        (res: HttpResponse<any>) => {
          if (res instanceof HttpResponse) {
            observer.next(res);
          }
        },
        (err: HttpErrorResponse) => {
          this.error.handleError(err);
        }
      );
    });
  }

  
    
}


export const ErrorInterceptorProvider = {
    provide: HTTP_INTERCEPTORS,
    useClass: ErrorInterceptor,
    multi: true,
}