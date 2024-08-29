import axios from "axios";
import { Todo } from "../coponents/types/TodoType";

const apiUrl = import.meta.env.VITE_API_URL

const api = axios.create({
    baseURL: `${apiUrl}/api`,
})

//Todoリストの取得
export const getTodoList = () => api.get('/todo');
export const getTodoListById = (id:number) => api.get(`/todo/${id}`);

export const createTodo = async (todo:Todo) => {
    return api.post('/create/todo' , todo);
}

export const deleteTodo = async (id:number) => {
    return api.delete(`/todo/${id}/delete`);
}

export const updateTodo = async (todo:Todo) => {
    return api.put(`/todo/${todo.id}/update`,todo);
}

