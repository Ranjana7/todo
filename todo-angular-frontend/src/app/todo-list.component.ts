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
  newRequest: TodoRequest = new TodoRequest();
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
          console.log(`Todos for user '${this.userName}' not found, returning to list`);
        }
      });
  }

  createTodo(todoForm: NgForm): void {
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