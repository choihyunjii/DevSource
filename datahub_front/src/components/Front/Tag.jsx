import React from 'react';

function Tag({ tag }) {
    switch (tag) {
        case 'H1':
            return <h1>This is an H1 tag</h1>;
        case 'P':
            return <p>This is a paragraph tag</p>;
        // 추가적인 태그에 대한 case를 필요에 따라 여기에 추가
        default:
            return null;
    }
}

export default Tag;
