import { Component, OnInit } from '@angular/core';

import { ToDo } from '../../models/ToDo';
import {ToDoServiceService} from '../../services/to-do-service.service';
@Component({
  selector: 'app-to-dos',
  templateUrl: './to-dos.component.html',
  styleUrls: ['./to-dos.component.css']
})
export class ToDosComponent implements OnInit {
  
  todoarray:ToDo[];

  constructor(private todoservice:ToDoServiceService) {
    this.todoarray = [];
   }

  ngOnInit(): void {
    this.todoservice.getToDos().subscribe(todos => {this.todoarray = todos});
  }

  deleteTodo(todo:ToDo)
  {
    this.todoarray = this.todoarray.filter(t => t.id != todo.id);
    this.todoservice.deleteTodo(todo).subscribe();
  }
  addTodo(todo:ToDo)
  {
    this.todoservice.addTodo(todo).subscribe(todo=>{this.todoarray.push(todo)});
  }

}
