import DraggableElement from "./DraggableElement";
import React, { useState } from "react";

export default function CreateDraggableElement({ text }) {
    const [inputColor, setInputColor] = useState("#000000"); // 입력된 색상 
    const [innerText , setInnerText] = useState(""); // 입력된 텍스트 
    const [isVisible, setIsVisible] = useState(true); // 입력 필드의 등장여부

    const handleColorChange = (e) => {
        setInputColor(e.target.value);
    };

    const handleTextChange = (e) => {
        setInnerText(e.target.value);
    };

    const handleApplyChanges = (e) => {
        alert(`색상: ${inputColor}, 텍스트: ${innerText}`);
        setIsVisible(false);
    };

    switch (text) {
        case "제목글":
            return (
                <DraggableElement>
                    <div>
                        {isVisible && (
                            <>
                                <input type="color" value={inputColor} onChange={handleColorChange} />
                                <input type="text" value={innerText} onChange={handleTextChange} placeholder="텍스트 입력" />
                                <button onClick={handleApplyChanges}>적용</button>
                            </>
                        )}
                        <h1 style={{ color: inputColor }}>{innerText}</h1>
                    </div>
                </DraggableElement>
            );
        case "이미지":
            // 다른 요소
            break;
        default:
            return null;
    }
}
