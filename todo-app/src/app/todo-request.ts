import { Todo } from './todo.interface';

export class TodoRequest {
    todos: Todo[] = [];
    userName: string;
  }