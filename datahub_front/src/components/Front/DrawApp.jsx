import React, { useState } from 'react';
import DraggableElement from "./DraggableElement";

function MyComponent() {
    const [draggableElements, setDraggableElements] = useState([]);
    const [deleteMode, setDeleteMode] = useState(false); // 삭제 모드 상태
    const [styleMode, setStyleMode] = useState(true); // 스타일 모드 상태

    const addDraggableElement = (tag) => {
        setDraggableElements(prevState => [...prevState, tag]);
    };

    const handleDelete = (index) => {
        if (deleteMode) {
            const confirmDelete = window.confirm("삭제 하시겠습니까?");
            if (confirmDelete) {
                setDraggableElements(prevState => prevState.filter((_, i) => i !== index));
            }
        }
        if (styleMode){
            alert("Style Mode입니다.")
            //CSS 관련 필터링
        }
    };

    return (
        <div>
            {/* 삭제 모드 토글 버튼 */}
            <button onClick={() => {
                setDeleteMode(prevDeleteMode => !prevDeleteMode);
                setStyleMode(prevStyleMode => !prevStyleMode);
            }}>
                {deleteMode ? "스타일 모드로 변경" : "삭제 모드로 변경"}
            </button>
            {/* H1 추가  */}
            <button onClick={() => addDraggableElement("H1")}>H1 추가</button>
            {/* P 추가  */}
            <button onClick={() => addDraggableElement("P")}>P 추가</button>

            {/* 드래그 가능한 요소들 표시 */}
            {draggableElements.map((tag, index) => (
                <div key={index} onDoubleClick={() => handleDelete(index)}>
                    <DraggableElement tag={tag} />
                </div>
            ))}

        </div>
    );
}

export default MyComponent;
