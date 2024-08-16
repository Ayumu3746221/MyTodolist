import { createSlice , PayloadAction } from "@reduxjs/toolkit";
import { Todo, TodoItem } from "../coponents/types/TodoType";

interface TodoState {
    todoList: Todo[];
}

const initialState: TodoState = {
    todoList: [],
}

const todoSlice = createSlice({
    name: 'todos',
    initialState,
    reducers: {
        setTodos: (state, action: PayloadAction<Todo[]>) => {
            state.todoList = action.payload;
        },
        addTodo: (state, action: PayloadAction<Todo>) => {
            state.todoList = [...state.todoList , action.payload]
        },
        deletedTodo: (state, action: PayloadAction<TodoItem>) => {
            state.todoList = state.todoList.filter((todo) => todo.id !== action.payload.id)
        },
    },
});

export const { setTodos, addTodo , deletedTodo} = todoSlice.actions;
export default todoSlice.reducer;