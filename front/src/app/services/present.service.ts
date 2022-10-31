import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Present } from '../models/present';

@Injectable({
  providedIn: 'root'
})
export class PresentService {

  constructor(private http:HttpClient) { }

  draw(): Observable<any> {
    return this.http.get(`${environment.apiUrl}/present/draw`);
  }

  hasDrawn(): Observable<any> {
    return this.http.get(`${environment.apiUrl}/present/draw/done`);
  }

  getPresentList(): Observable<any> {
    return this.http.get(`${environment.apiUrl}/present`);
  }
}
