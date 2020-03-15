import { Component, OnInit } from '@angular/core';
import { Todo } from '../todo';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { TodoService } from '../todo.service';
import { EdittaskComponent } from '../edittask/edittask.component';

@Component({
  selector: 'app-tasks',
  templateUrl: './tasks.component.html',
  styleUrls: ['./tasks.component.css']
})
export class TasksComponent implements OnInit {

  todos: Todo[];
  userName: string = "TestUser1";
  constructor(
    public dialog: MatDialog,
    private todoService: TodoService,
  ) {}
  

  ngOnInit(): void {
    this.getTodos();
  }

  getTodos(): void {
    this.todoService.getTodos(this.userName).subscribe(
      (data: Todo[]) =>  this.todos = data,
      (error: any)   => console.log(error),
      ()             => console.log('all data gets')
    );
  }

  //delete a todo
  deleteItem(todo: Todo){ 
    this.todoService.deleteTodo(this.userName, todo)
    .subscribe(
      (res: any) => location.reload(),
      (error: any) => console.log(error)
    )
  }

  //open the edit dialog
  openEditDialog(_id): void {
    this.todoService.getTodos(_id)
        .subscribe( 
          (resp: Todo) => {
            const dialogConfig = new MatDialogConfig();
            dialogConfig.width = '500px';
            dialogConfig.data = resp;
            const dialogRef = this.dialog.open(EdittaskComponent, dialogConfig);

            dialogRef.afterClosed().subscribe(result => {
              console.log('The dialog was closed');
            });
          },
          (error: any) => console.log(error),
          ()=> console.log('complete')
        );
  }
}
