import { useDispatch } from "react-redux";

import { TodoItem } from "../types/TodoType";
import { deleteTodo } from "../../api/todoApi";
import { AppDispatch } from "../../redux/store";
import { deletedTodo } from "../../redux/todoSlice";

interface ItemProps {
    todo: TodoItem
}

const Item:React.FC<ItemProps> = ({todo}) => {
    
    const dispatch = useDispatch<AppDispatch>(); 

    const handlerDeleted = (todo:TodoItem) => {
        try {
            deleteTodo(todo.id);
            dispatch(deletedTodo(todo));
        } catch (error) {
            console.log("Unexpected ERROR",error)
        }
    }

    return(
        <li className="p-4 border border-gray-200 rounded-lg shadow-sm flex items-center space-x-4">
            <div className="flex-1 text-lg font-semibold">{todo.title}</div>
            <p 
            onClick={(e) => console.log(e)}
            className={`text-sm ${todo.done === 'Y' ? 'text-green-500' : 'text-red-500'}`}
            >
                {todo.done === 'Y' ? 'Do' : 'unDo'}
            </p>
            <button onClick={() => handlerDeleted(todo)}>Delete</button>
        </li>
    );
}

export default Item;