import { HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { throwError } from 'rxjs';
import { BaseResponse } from '../model/interfaces';

export function getOptions(): { headers: HttpHeaders } {
    return {
        headers: new HttpHeaders({
          'Content-Type': 'application/json'
      }),
    };
}


export function errorHandler(error: HttpErrorResponse) {
  if (error.error instanceof ErrorEvent) {
    // A client-side or network error occurred. Handle it accordingly.
    console.error('An error occurred:', error.error.message);
    return throwError(
      'Something went wrong; please try again later or contact support.'
    );
  } else {
    // The backend returned an unsuccessful response code.
    // The response body may contain clues as to what went wrong,
    return throwError(error.error as BaseResponse);
  }
}
