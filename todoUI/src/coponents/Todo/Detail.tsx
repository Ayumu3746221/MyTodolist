import React, { useEffect, useState } from "react";
import { useDispatch } from "react-redux";
import { AppDispatch } from "../../redux/store";
import { updatedTodo } from "../../redux/todoSlice";
import { getTodoListById , updateTodo } from "../../api/todoApi";

type DetailProps = {
    todoId:number
    onClose:() => void,
    isDetailOpen:boolean
}

const Detail:React.FC<DetailProps> = ({todoId , onClose , isDetailOpen}) => {
    const [title , setTitle] = useState("");
    const [ditail , setDitail] = useState("");
    const [deadline , setDeadline] = useState("");
    const [done , setDone] = useState("N");

    
    const dispatch = useDispatch<AppDispatch>();

    useEffect(() => {
        const loadTodo = async(id:number) => {
            try {
                const response = (await getTodoListById(id)).data;
                setTitle(response.title);
                setDitail(response.ditail);
                setDeadline(response.deadline);
                setDone(response.done);

            } catch (error) {
                console.error("Unexpected ERROR")
            }
        }

        if (isDetailOpen === true) {
            loadTodo(todoId);
        }

    },[])

    const handleSubmit = async (e : React.FormEvent) => {
        e.preventDefault();

        try {
            const newTodo = {id:todoId,title, ditail,deadline,done };
            updateTodo(newTodo);
            dispatch(updatedTodo(newTodo));
            onClose();
        } catch (error) {
            console.error("Unexpected" , error)
        }
    }

    if (!isDetailOpen) {
        return null;
    }

    return (
        <form onSubmit={handleSubmit} className="space-y-4">
            <div>
                <label htmlFor="title" className="block text-sm font-medium text-gray-700">Title</label>
                <input
                    id="title"
                    type="text"
                    value={title}
                    onChange={(e) => setTitle(e.target.value)}
                    className="mt-1 block w-full p-2 border border-gray-300 rounded-md"
                    required
                />
            </div>
            <div>
                <label htmlFor="detail" className="block text-sm font-medium text-gray-700">Detail</label>
                <input
                    id="title"
                    type="text"
                    value={ditail}
                    onChange={(e) => setDitail(e.target.value)}
                    className="mt-1 block w-full p-2 border border-gray-300 rounded-md"
                    required
                />
            </div>
            <div>
                <label htmlFor="deadline" className="block text-sm font-medium text-gray-700">DeadLine</label>
                <input
                    id="title"
                    type="date"
                    value={deadline}
                    onChange={(e) => setDeadline(e.target.value)}
                    className="mt-1 block w-full p-2 border border-gray-300 rounded-md"
                    required
                />
            </div>
            <div>
                <label htmlFor="done" className="block text-sm font-medium text-gray-700">Done</label>
                <select
                    id="done"
                    value={done}
                    onChange={(e) => setDone(e.target.value)}
                    className="mt-1 block w-full p-2 border border-gray-300 rounded-md"
                >
                    <option value="N">No</option>
                    <option value="Y">Yes</option>
                </select>
            </div>
            <div className="flex justify-end space-x-2">
                <button
                    type="button"
                    onClick={onClose}
                    className="px-4 py-2 bg-gray-500 text-white rounded-md"
                >
                    Cancel
                </button>
                <button
                    type="submit"
                    className="px-4 py-2 bg-blue-500 text-white rounded-md"
                >
                    Update Todo
                </button>
            </div>
        </form>
    );
}

export default Detail