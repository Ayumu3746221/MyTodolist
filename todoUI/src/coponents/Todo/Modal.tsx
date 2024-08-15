import React, { ReactNode } from "react";

interface ModalProps {
    isOpenModal: boolean
    closeModal: () => void
    children: ReactNode
}

const Modal:React.FC<ModalProps> = ({isOpenModal,closeModal,children}) => {
    if(!isOpenModal) return null;

    return (
        <div className="fixed inset-0 flex items-center justify-center z-50">
            <div className="fixed inset-0 bg-black opacity-50" onClick={closeModal} />
            <div className="bg-white p-6 rounded-lg shadow-lg z-10">
                <button className="absolute top-2 right-2 text-gray-500" onClick={closeModal}>
                    &times;
                </button>
                {children}
            </div>
        </div>
    );
}

export default Modal;