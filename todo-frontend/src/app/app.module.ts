import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatButtonModule} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import {MatListModule} from '@angular/material/list';
import {MatDialogModule} from '@angular/material/dialog';
import {MatInputModule} from '@angular/material/input';

import {BrowserAnimationsModule} from '@angular/platform-browser/animations'; 
import {FormsModule} from '@angular/forms';
import { ToolbarComponent } from './toolbar/toolbar.component';
import { AddtaskComponent } from './addtask/addtask.component';
import { TasksComponent } from './tasks/tasks.component';
import { EdittaskComponent } from './edittask/edittask.component';

@NgModule({
  declarations: [
    AppComponent,
    ToolbarComponent,
    AddtaskComponent,
    TasksComponent,
    EdittaskComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatToolbarModule, 
    MatButtonModule, 
    MatListModule, 
    MatIconModule, 
    MatDialogModule, 
    MatInputModule, 
    BrowserAnimationsModule, 
    FormsModule
  ],
  providers: [],
  entryComponents: [AddtaskComponent],
  bootstrap: [AppComponent]
})
export class AppModule { }
