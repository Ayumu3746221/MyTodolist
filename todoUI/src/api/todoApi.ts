import axios from "axios";
import { Todo } from "../coponents/types/TodoType";

const api = axios.create({
    baseURL: 'http://localhost:8080/',
})

//Todoリストの取得
export const getTodoList = () => api.get('/todo');

export const createTodo = async (todo:Todo) => {
    return api.post('/create/todo' , todo);
}