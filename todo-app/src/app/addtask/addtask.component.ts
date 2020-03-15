import { Component, OnInit, Inject, Input } from '@angular/core';
import {MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';
import { TodoService } from '../todo.service';
import { Todo } from './../todo.interface';
import { getTreeNoValidDataSourceError } from '@angular/cdk/tree';



@Component({
  selector: 'app-addtask',
  templateUrl: './addtask.component.html',
  styleUrls: ['./addtask.component.css']
})
export class AddtaskComponent implements OnInit {

  userName: string;
  constructor(
    public dialogRef: MatDialogRef<AddtaskComponent>,
    private todoService: TodoService,
    @Inject(MAT_DIALOG_DATA) public data: any
  ){
    this.userName=data.userName;
  }

  todos = [];

  ngOnInit() {
        this.todoService.getTodos(this.userName)
        .subscribe(
          (data: Todo[]) =>  this.todos = data,
          (error: any)   => console.log(error),
          ()             => console.log('all todos gets')
        );
  }

    onCancel(): void {
      this.dialogRef.close();
    }
    
    onSave(formData: any){
      let newTodo: any = { title: formData.title};
      this.todoService.addTodo(this.userName,newTodo)
        .subscribe(
          (data: Todo) => location.reload(),
          (error) => console.log(error)
        );
      this.dialogRef.close();
    }

}


