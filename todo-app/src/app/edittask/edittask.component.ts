import { Component, OnInit, Inject, Input } from '@angular/core';
import {MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';
import { TodoService } from '../todo.service';
import { Todo } from './../todo.interface';

@Component({
  selector: 'app-edittask',
  templateUrl: './edittask.component.html',
  styleUrls: ['./edittask.component.css']
})
export class EdittaskComponent implements OnInit {
  @Input()
  userName: string;
  constructor(
    public dialogRef: MatDialogRef<EdittaskComponent>,
    @Inject(MAT_DIALOG_DATA) public passingData: Todo,
    private myData: TodoService
  ) { }

  ngOnInit() {
  }

  onCancel(): void {
    this.dialogRef.close();
  }


  onUpdate(formData: any){
    let editedTodo: any = {title: formData.title};
    this.myData.updateTodo(this.userName,editedTodo)
      .subscribe(
        (data: Todo) => location.reload(),
        (error) => console.log(error)
      );
    this.dialogRef.close();
  }

}
