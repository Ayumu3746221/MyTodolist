import React, { useState } from "react";
import { useDispatch } from "react-redux";
import { Todo } from "../types/TodoType";
import { AppDispatch } from "../../redux/store";
import { addTodo } from "../../redux/todoSlice";
import { createTodo } from "../../api/todoApi";
import axios from "axios";

const Form:React.FC<{onClose:() => void}> = ({onClose}) => {
    const [title , setTitle] = useState("");
    const [ditail , setDitail] = useState("");
    const [deadline , setDeadline] = useState("");
    const [done , setDone] = useState("N");

    
    const dispatch = useDispatch<AppDispatch>();

    const handleSubmit = async (e : React.FormEvent) => {
        e.preventDefault();

        try {
            const newTodo = {id:0 ,title, ditail,deadline,done };
            const response = (await createTodo(newTodo));
            const createdTodo:Todo = response.data;
            dispatch(addTodo(createdTodo));
            onClose();
        } catch (error) {
            if (axios.isAxiosError(error)) {
                const errorResponse = error.response?.data;
                const errorMessage = errorResponse?.message || "An unknown error occurred";
                console.error("API ERROR",errorMessage)
            }
            else{
                console.error("Unexpected" , error)
            }
        }
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
                    type="textarea"
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
                    Add Todo
                </button>
            </div>
        </form>
    );
}

export default Form