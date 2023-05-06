import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})

export class PizzaService {

  constructor(private httpClient: HttpClient) { }

  fetchAllPizzas(){
    return this.httpClient.get("http://localhost:9002/api/v2/getAllPizza");
  }

}
