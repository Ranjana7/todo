import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TodoRequest } from './todo-request';

@Injectable()
export class TodoService {
  private baseUrl = 'http://localhost:8080/v1/todos/';

  constructor(private http: HttpClient) { }

  getTodos(userName: String):  Observable<any> {
    return this.http.get(this.baseUrl + userName);
  }

  createTodos(todoData: TodoRequest): Observable<any> {
    return this.http.post(this.baseUrl, todoData);
  }

  updateTodos(todoData: TodoRequest): Observable<any> {
    return this.http.put(this.baseUrl, todoData);;
  }

  deleteTodos(todoData: TodoRequest): Observable<any> {
    return this.http.delete(this.baseUrl);
  }
}