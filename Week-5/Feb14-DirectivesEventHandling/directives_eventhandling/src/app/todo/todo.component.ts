import { Component, OnInit } from '@angular/core';
import { Todo } from '../models/Todo';


@Component({
  selector: 'app-todo',
  templateUrl: './todo.component.html',
  styleUrls: ['./todo.component.css']
})

export class TodoComponent implements OnInit {
  // todoList contains all todos
  todoList: Todo[];
  constructor() { 
    this.todoList = [];
  }

  ngOnInit() {
  }
  // write logic to the onAddTodo method is to add a new todo to list
  // get maximum id in list and assign maximum id plus one while adding a todo
  onAddTodo(todoText: any) {
    if (todoText != "" && todoText != null) {
      const maxId = this.todoList.reduce((max, item) => {
        return item.todoId > max.todoId ? item: max;
      }, { todoId: 0, text: ''});
      const newTodo = {
        todoId: maxId.todoId + 1,
        text: todoText,
        isCompleted: false
      }
      this.todoList.push(newTodo);
    }
  }

  // write logic to the onClearTodos method to delete the all todos in the todoList
  onClearTodos() {
    this.todoList.splice(0, this.todoList.length);
  }

  // write logic to method onCompletingTask, to mark todo as as completed or not
  onCompletingTodo(todo: Todo) {
    todo.isCompleted = true;
  }

  // write logic to method onDeletingTask, to delete the todo
  onDeletingTodo(todoId) {
    const index = this.todoList.findIndex(todo => todo.todoId === todoId);
    if (index !== -1) {
      this.todoList.splice(index,1);
    }
  }
}
