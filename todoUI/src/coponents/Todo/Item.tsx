import { TodoItem } from "../types/TodoType";

interface ItemProps {
    todo: TodoItem
}

const Item:React.FC<ItemProps> = ({todo}) => {
    return(
        <li className="p-4 border border-gray-200 rounded-lg shadow-sm flex items-center space-x-4">
            <div className="flex-1 text-lg font-semibold">{todo.title}</div>
            <p className={`text-sm ${todo.done === 'Y' ? 'text-green-500' : 'text-red-500'}`}>
                {todo.done === 'Y' ? 'Do' : 'unDo'}
            </p>
        </li>
    );
}

export default Item;