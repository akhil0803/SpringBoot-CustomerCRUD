import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ToDo } from '../models/ToDo';
import { Observable } from 'rxjs';

const httpOptions = {
   headers : new HttpHeaders(
     {'Content-Type':'application/json'}
   )
}


@Injectable({
  providedIn: 'root'
})
export class ToDoServiceService {


  todosurl :string = 'https://jsonplaceholder.typicode.com/todos';

  constructor(private http:HttpClient) { }

  getToDos(): Observable<ToDo[]>{
    return this.http.get<ToDo[]>(this.todosurl);
  }
 checked(todo:ToDo):Observable<any>{
   const url = `https://jsonplaceholder.typicode.com/todos/${todo.id}`;
   return this.http.put(url,todo,httpOptions);
 }
 deleteTodo(todo:ToDo): Observable<any>{
     const url = `https://jsonplaceholder.typicode.com/todos/${todo.id}`;
     return this.http.delete<ToDo>(url,httpOptions); 

 }

 addTodo(todo:ToDo):Observable<ToDo>{
return this.http.post<ToDo>(`https://jsonplaceholder.typicode.com/todos`,todo,httpOptions);
 }
}
