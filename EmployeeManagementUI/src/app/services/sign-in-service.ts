import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ApiResponse } from '../model/api-response';

const BASE_URL = 'http://localhost:8080/public/signin';

@Injectable({
  providedIn: 'root',
})
export class SignInService {
  constructor(private http: HttpClient) {}

  signIn(form: any) {
    return this.http.post<ApiResponse>(BASE_URL, form);
  }
}
