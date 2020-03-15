import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Todo } from './todo.interface';
import { TodoRequest } from './todo-request';

@Injectable({providedIn: 'root' })
export class TodoService {
  private baseUrl = 'http://localhost:8080/v1/todos/';
  private request: TodoRequest = new TodoRequest();
  constructor(private http: HttpClient) { }

  getTodos(userName: String):  Observable<any> {
    return this.http.get(this.baseUrl + userName);
  }

  addTodo(userName:string,todo: Todo): Observable<any> {
    return this.submitRequest(userName,todo);
  }

  updateTodo(userName:string,todo: Todo): Observable<any> {
    return this.submitRequest(userName,todo);
  }

  deleteTodo(userName:string,todo: Todo): Observable<any> {
    return this.submitRequest(userName,todo);
  }

  private submitRequest(userName:string,todo: Todo): Observable<any>{
    this.request.todos.push(todo);
    this.request.userName = userName;
    return this.http.post(this.baseUrl, this.request);
  }
}