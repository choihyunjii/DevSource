import React, { useEffect } from 'react';

function CssCode({ x, y, elementName }) {
    useEffect(() => {
        const handleMouseUp = () => {
            const cssCode = `${elementName}
                position: absolute;
                left: ${x}px;
                top: ${y}px;
            `;
            console.log(cssCode);
        };

        document.addEventListener('mouseup', handleMouseUp);

        return () => {
            document.removeEventListener('mouseup', handleMouseUp);
        };
    }, [x, y]);

    return null;
}

export default CssCode;
