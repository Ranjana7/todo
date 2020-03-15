import { Component, OnInit } from '@angular/core';
import { Todo } from './todo';
import { NgForm } from '@angular/forms';
import { TodoRequest } from './todo-request';
import { TodoService } from './todo.service';

@Component({
  selector: 'todo-list',
  templateUrl: './todo-list.component.html'
})

export class TodoListComponent implements OnInit {
  request: TodoRequest;
  userName: string = "TestUser1";
  todos: Todo[];
  newTodo: Todo = new Todo();
  editing: boolean = false;
  editingTodo: Todo = new Todo();

  constructor(
    private todoService: TodoService,
  ) {}

  ngOnInit(): void {
    this.getTodos();
  }

  getTodos(): void {
    this.todoService.getTodos(this.userName).subscribe((todos: any) => {
        if (todos) {
          this.todos = todos;
        } else {
          console.log(`Todos for user '${this.userName}' not found`);
        }
      });
  }

  createTodo(todoForm: NgForm): void {
    this.request = new TodoRequest();
    this.request.todos.push(this.newTodo);
    this.request.userName = this.userName;
    this.todoService.createTodos(this.request).subscribe((todos: any) => {
      if (todos) {
        this.todos.push(todos);
      } else {
        console.log(`Error occured while creating Todos for user '${this.request.userName}'`);
      }
    });
  }

  deleteTodo(id: string): void {

  }

  updateTodo(todoData: Todo): void {

  }

  toggleCompleted(todoData: Todo): void {

  }

  editTodo(todoData: Todo): void {

  }

  clearEditing(): void {

  }
}