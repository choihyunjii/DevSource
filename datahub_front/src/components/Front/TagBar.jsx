import React, { useState } from 'react';
import DraggableElement from "./DraggableElement";

function TagBar() {
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
            <button onClick={() => addDraggableElement("H1")}>h1</button>
            <button onClick={() => addDraggableElement("H2")}>h2</button>
            <button onClick={() => addDraggableElement("H3")}>h3</button>
            <button onClick={() => addDraggableElement("H4")}>h4</button>
            <button onClick={() => addDraggableElement("H5")}>h5</button>
            <button onClick={() => addDraggableElement("H6")}>h6</button>
            <button onClick={() => addDraggableElement("P")}>text</button>
            <button onClick={() => addDraggableElement("Button")}>Button</button>
            <button onClick={() => addDraggableElement("Strong")}>Strong</button>
            <button onClick={() => addDraggableElement("Link")}>Link</button>
            <button onClick={() => addDraggableElement("Image")}>Image</button>
            <button onClick={() => addDraggableElement("Table")}>Table</button>
            <button onClick={() => addDraggableElement("List")}>List</button>


            {/* 드래그 가능한 요소들 표시 */}
            {draggableElements.map((tag, index) => (
                <div key={index} onDoubleClick={() => handleDelete(index)}>
                    <DraggableElement tag={tag} />
                </div>
            ))}

        </div>
    );
}

export default TagBar;
