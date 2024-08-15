import { useEffect, useState} from 'react';
import { useDispatch , useSelector } from 'react-redux';
import { AppDispatch ,RootState } from "../../redux/store";
import { getTodoList } from "../../api/todoApi";
import { setTodos } from '../../redux/todoSlice';

import Item from "./Item";
import Modal from './Modal';
import Form from "./From";

const Todo = () => {
    const disptch = useDispatch<AppDispatch>();
    const todoList = useSelector((state: RootState) => state.todos.todoList || []);

    const[ isOpenModal , setOpenModal ] = useState(false);
    const [loading, setLoading] = useState(true);

    //apiからTodoListを取得
    useEffect(() => {
        const loadTodos = async () => {
            try {
                const response = (await getTodoList()).data;
                disptch(setTodos(response))
            } catch (error) {
                console.error('Error message getTodoList', error);
            } finally {
                setLoading(false);
            }
        }
    loadTodos();
  } , [disptch])

    if (loading) {
        return <p>Loading...</p>
    }

    return(
        <>
            <button 
            onClick={() => setOpenModal(true)}
            className="mb-4 px-4 py-2 bg-blue-500 text-white rounded-md">
                add Todo
            </button>
            <ul className="space-y-2">
                {todoList.map(todo => (
                    <Item key={todo.id} todo={todo}></Item>
                ))}
            </ul>
            <Modal isOpenModal={isOpenModal} closeModal={() => setOpenModal(false)}>
                <Form onClose={() => setOpenModal(false)}></Form>
            </Modal>
        </>
        
    );
}

export default Todo;