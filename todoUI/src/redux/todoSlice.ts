import { createSlice , PayloadAction } from "@reduxjs/toolkit";
import { Todo } from "../coponents/types/TodoType";

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
    },
});

export const { setTodos, addTodo } = todoSlice.actions;
export default todoSlice.reducer;