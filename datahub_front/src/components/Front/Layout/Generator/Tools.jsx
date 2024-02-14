import React, { useState } from "react";
import './style.css';
import Data from "./Data";
import { Button } from "react-bootstrap";
import CreateDraggableElement from "../../CreateDraggableElement";

export default function Tools() {
    const [draggableElements, setDraggableElements] = useState([]);

    const toolImageData = [
        {
            "text": "제목글",
            "imageSrc": "image/heading.png"
        },
        {
            "text": "제목글",
            "imageSrc": "image/heading.png"
        },
        {
            "text": "제목글",
            "imageSrc": "image/heading.png"
        },
        {
            "text": "제목글",
            "imageSrc": "image/heading.png"
        },
        {
            "text": "제목글",
            "imageSrc": "image/heading.png"
        }
    ];

    const handleClick = (text) => {
        alert(`Clicked: ${text}`);
        const newElement = <CreateDraggableElement key={text} text={text} />;
        setDraggableElements(prevElements => [...prevElements, newElement]);
    };

    const handleDoubleClick = (text) => {
        if (window.confirm("해당 객체를 삭제 하시겠습니까?")) {
            setDraggableElements(prevElements => prevElements.filter(element => element.key !== text));
        }
    };

    const handleContextMenu = (e, text) => {
        e.preventDefault(); // 기본 우클릭 이벤트를 방지

        alert(`우클릭: ${text}`);
    };

    return (
        <div className="rectangle-container">
            <section id="name">Tools</section>
            <div vertical aria-label='Vertical button group'>
                {toolImageData.map((data, index) => (
                    <Button key={index} onClick={() => handleClick(data.text)} onContextMenu={(e) => handleContextMenu(e, data.text)}>
                        <Data image={data.imageSrc} title={data.text} />
                    </Button>
                ))}
            </div>
            {draggableElements.map((element, index) => (
                <div key={index} onDoubleClick={() => handleDoubleClick(element.key)} onContextMenu={(e) => handleContextMenu(e, element.key)}>
                    {element}
                </div>
            ))}
        </div>
    );
}
