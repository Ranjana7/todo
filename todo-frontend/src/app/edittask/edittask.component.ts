import { Component, OnInit, Inject } from '@angular/core';
import { Todo } from '../todo';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { TodoService } from '../todo.service';

@Component({
  selector: 'app-edittask',
  templateUrl: './edittask.component.html',
  styleUrls: ['./edittask.component.css']
})
export class EdittaskComponent implements OnInit {
  userName: string = "TestUser1";
  constructor(
    public dialogRef: MatDialogRef<EdittaskComponent>,
    @Inject(MAT_DIALOG_DATA) public passingData: Todo,
    private todoService: TodoService
  ) { }

  ngOnInit() {
  }

  onCancel(): void {
    this.dialogRef.close();
  }


  onUpdate(formData: any){
    let editedTodo: any = { _id: formData._id, title: formData.title};
    this.todoService.updateTodo(this.userName,editedTodo)
      .subscribe(
        (data: Todo) => location.reload(),
        (error: any) => console.log(error)
      );
    this.dialogRef.close();
  }

}
