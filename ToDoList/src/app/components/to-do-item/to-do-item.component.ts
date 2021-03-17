import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { ToDo } from 'src/app/models/ToDo';
import { ToDoServiceService } from '../../services/to-do-service.service'

@Component({
  selector: 'app-to-do-item',
  templateUrl: './to-do-item.component.html',
  styleUrls: ['./to-do-item.component.css']
})
export class ToDoItemComponent implements OnInit {

  @Input() todo: ToDo;
  @Output() deleteTodo : EventEmitter<ToDo> = new EventEmitter();

  constructor(private todoservice:ToDoServiceService) { }

  ngOnInit(): void {
  }
  setClasses() {
    let classes = {
      todo : true,
      'is_complete': this.todo.completed

    }
    return classes;
  }
  onChange(todo:ToDo){
    todo.completed = !todo.completed;
    this.todoservice.checked(todo).subscribe(todo => (console.log(todo)));

  }
  onClick(todo:ToDo){
   this.deleteTodo.emit(todo);
  }

}
