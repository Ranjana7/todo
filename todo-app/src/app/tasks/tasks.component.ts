import { Component, OnInit, Inject, Input } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material';

import { TodoService } from '../todo.service';
import { Todo } from './../todo.interface';
 import { EdittaskComponent } from './../edittask/edittask.component';
import { UserComponent } from '../user/user.component';



@Component({
  selector: 'app-tasks',
  templateUrl: './tasks.component.html',
  styleUrls: ['./tasks.component.css']
})

export class TasksComponent implements OnInit {
  
  @Input()
  userName: string;
  todos: Todo[];

  constructor(
    public dialog: MatDialog,
    private todoService: TodoService,
  ) {}

  ngOnInit(): void{
    this.todoService.getTodos(this.userName)
    .subscribe(
      (data: Todo[]) =>  this.todos = data,
      (error: any)   => console.log(error),
      ()             => console.log('all data gets')
    );
  }

  ngOnChanges(): void{
    this.todoService.getTodos(this.userName)
    .subscribe(
      (data: Todo[]) =>  this.todos = data,
      (error: any)   => console.log(error),
      ()             => console.log('all data gets')
    );
  }

  //delete a todo
  deleteItem(id: string){
    this.todoService.deleteTodo(this.userName,id)
      .subscribe(
        (res: any) => location.reload(),
        (error: any) => console.log(error)
      )
  }

  //open the edit dialog
  openEditDialog(): void {
    this.todoService.getTodos(this.userName)
        .subscribe( 
          (resp: Todo[]) => {
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
