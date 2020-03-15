import { Component } from '@angular/core';
import {MatDialog} from '@angular/material';
import { AddtaskComponent } from './addtask/addtask.component';
import { UserComponent } from './user/user.component';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})



export class AppComponent {


  user: string = "TestUser1";
  constructor(public dialog: MatDialog) {}

  title = 'Todo-App';


  openDialog(): void {
    const dialogRef = this.dialog.open(AddtaskComponent, 
      {
        width: '500px',
        data: {userName: this.user},
      });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }
}
