import React, {useEffect, useRef} from "react";

export default function DraggableElement({ children }) {
    const elementRef = useRef(null);

    useEffect(() => {
        const makeDraggable = (element) => {
            let isDragging = false;
            let offsetX = 0;
            let offsetY = 0;
            let elementToDrag = null;

            element.addEventListener('mousedown', function(e) {
                isDragging = true;
                offsetX = e.offsetX;
                offsetY = e.offsetY;
                elementToDrag = element;
            });

            document.addEventListener('mousemove', function(e) {
                if (isDragging && elementToDrag !== null) {
                    elementToDrag.style.left = e.clientX - offsetX + 'px';
                    elementToDrag.style.top = e.clientY - offsetY + 'px';
                }
            });

            document.addEventListener('mouseup', function() {
                isDragging = false;
                elementToDrag = null;
            });
        };

        makeDraggable(elementRef.current);

    }, []);

    return (
        <div
            ref={elementRef}
            style={{ position: 'absolute', left: 0, top: 0 }}
        >
            {children}
        </div>
    );
}
