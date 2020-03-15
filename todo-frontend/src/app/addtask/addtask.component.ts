import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { TodoService } from '../todo.service';
import { Todo } from '../todo';
@Component({
  selector: 'app-addtask',
  templateUrl: './addtask.component.html',
  styleUrls: ['./addtask.component.css']
})
export class AddtaskComponent implements OnInit {
  userName: string = "TestUser1";
  todos = [];

  constructor( public dialogRef: MatDialogRef<AddtaskComponent>, private todoService: TodoService) {}

  ngOnInit(): void {
    this.todoService.getTodos(this.userName)
        .subscribe(
          (data: Todo[]) =>  this.todos = data,
          (error: any)   => console.log(error),
          ()             => console.log('Retrived all Todos')
        );
  }

  onSave(formData: any): void { 
    let newTodo: any = { title: formData.title};
    this.todoService.createTodo(this.userName,newTodo).subscribe(
      (data: Todo[]) => location.reload(),
      (error) => console.log(error)
    ); 
    this.dialogRef.close(); 
} 
  onCancel(): void { 
    this.dialogRef.close(); 
}

}
