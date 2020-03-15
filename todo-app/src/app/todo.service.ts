import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Todo } from './todo.interface';
import { TodoRequest } from './todo-request';

@Injectable({providedIn: 'root' })
export class TodoService {

  private baseUrl = 'http://localhost:8080/v1/todos/';
  constructor(private http: HttpClient) { }

  getTodos(userName: string):  Observable<any> {
    return this.http.get(this.baseUrl + userName);
  }
  getTodoById(id: number) {
    return this.http.get(`${this.baseUrl}todo/${id}`);
  }
  addTodo(userName:string,todo: Todo): Observable<any> {
    return this.http.post(this.baseUrl, this.submitRequest(userName,todo), {
      headers: {
        'Content-Type': 'application/json'
      }
    });
  }

  updateTodo(userName:string,todo: Todo): Observable<any> {
    return this.http.put(this.baseUrl, this.submitRequest(userName,todo), {
      headers: {
        'Content-Type': 'application/json'
      }
    });
  }

  deleteTodo(userName:string,id: string): Observable<any> {
    return this.http.delete(`${this.baseUrl}${userName}/${id}`, {
      headers: {
        'Content-Type': 'application/json'
      }
    });
  }

  private submitRequest(userName:string,todo: Todo): TodoRequest{
    let request : TodoRequest = new TodoRequest();
    request.todos.push(todo);
    request.userName = userName;
    return request;
  }
}