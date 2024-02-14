import React, { useRef } from 'react';
import DraggableElement from './DraggableElement';
import CssCode from './CssCode';
import Toolkit from "./Layout/ToolKit";

export default function DrawHTML() {
    const buttonRef = useRef(null);
    const headingRef = useRef(null);

    return (
        <div>
            <Toolkit/>
            {/*<DraggableElement>*/}
            {/*    <button ref={buttonRef} className="draggableButton">Button</button>*/}
            {/*    <CssCode x={buttonRef.current?.getBoundingClientRect().left} y={buttonRef.current?.getBoundingClientRect().top} elementName="Button"/>*/}
            {/*</DraggableElement>*/}
            {/*<DraggableElement>*/}
            {/*    <h1 ref={headingRef} className="draggableHeading">Heading</h1>*/}
            {/*    <CssCode x={headingRef.current?.getBoundingClientRect().left} y={headingRef.current?.getBoundingClientRect().top} elementName="Heading"/>*/}
            {/*</DraggableElement>*/}
            {/*<DraggableElement>*/}
            {/*    <p className="draggableText">Text</p>*/}
            {/*</DraggableElement>*/}
        </div>
    );
}
