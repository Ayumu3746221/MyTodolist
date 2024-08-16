import { useEffect, useState} from 'react';
import { useDispatch , useSelector } from 'react-redux';
import { AppDispatch ,RootState } from "../../redux/store";
import { getTodoList } from "../../api/todoApi";
import { setTodos } from '../../redux/todoSlice';

import Item from "./Item";
import Modal from './Modal';
import Form from "./From";
import Detail from './Detail';

const Todo = () => {
    const disptch = useDispatch<AppDispatch>();
    const todoList = useSelector((state: RootState) => state.todos.todoList || []);

    const[ isOpenModal , setOpenModal ] = useState(false);
    const[ isOpenForm , setOpenForm] = useState(false);
    const[ isOpenDetail ,setOpenDetail] = useState(false);
    const[ isTodoID , setTodoID ] = useState<number>(-1);
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
            onClick={() => {
                setOpenModal(true)
                setOpenForm(true)
            }}
            className="mb-4 px-4 py-2 bg-blue-500 text-white rounded-md">
                add Todo
            </button>
            <ul className="space-y-2">
                {todoList.map(todo => (
                    <Item key={todo.id} todo={todo} openDetail= {() => {setOpenModal(true),setOpenDetail(true)}} sendTodoId = {(id:number) => setTodoID(id)}></Item>
                ))}
            </ul>
            <Modal isOpenModal={isOpenModal} closeModal={() => setOpenModal(false)}>
                <Form onClose={() => {setOpenModal(false),setOpenForm(false)}} isFormOpen={isOpenForm} ></Form>
                <Detail todoId={isTodoID} onClose={() => {setOpenModal(false),setOpenDetail(false),setTodoID(-1)}} isDetailOpen ={isOpenDetail}></Detail>
            </Modal>
        </>
        
    );
}

export default Todo;