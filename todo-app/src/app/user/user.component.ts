import { Component, OnInit, Optional, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  formDialog:string;
  constructor(public dialogRef: MatDialogRef<UserComponent>) { }

  ngOnInit() {
  }

  onCancel(): void {
    this.dialogRef.close({event:'close',data:"Guest"});
  }

  onLogin(){
    this.dialogRef.close({event:'login',data:this.formDialog});
  }
}
