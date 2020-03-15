import { Component } from '@angular/core';
import {MatDialog} from '@angular/material';
import { AddtaskComponent } from './addtask/addtask.component';
import { UserComponent } from './user/user.component';
import { ComponentType, T } from '@angular/core/src/render3';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})



export class AppComponent {


  user: string = "Guest";
  constructor(public dialog: MatDialog) {}

  title = 'Todo-App';


  ngOnInit() {
    const dialogRef = this.dialog.open(UserComponent, 
      {
        width: '500px',
        data: {userName: this.user},
      });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed',result);
      this.user = result.data;
    });
  }
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
