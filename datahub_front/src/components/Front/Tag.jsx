import React from 'react';
import {Image} from "react-bootstrap";

function Tag({ tag }) {
    switch (tag) {
        case 'H1':
            return <h1>This is an H1 tag</h1>;
        case 'H2':
            return <h2>This is an H2 tag</h2>;
        case 'H3':
            return <h3>This is an H3 tag</h3>;
        case 'H4':
            return <h4>This is an H4 tag</h4>;
        case 'H5':
            return <h5>This is an H5 tag</h5>;
        case 'H6':
            return <h6>This is an H6 tag</h6>;
        case 'P':
            return <p>This is a paragraph tag</p>;
        case 'Button':
            return <button>This is a button tag</button>;
        case 'Strong':
            return <strong>This is a strong tag</strong>;
        case 'Link':
            return <a href="#">This is a link tag</a>;
        case 'Image':
            return <Image src="image.jpg" alt="This is an image" />;
        case 'Table':
            return (
                <table>
                    <thead>
                    <tr>
                        <th>Header 1</th>
                        <th>Header 2</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>Row 1 Data 1</td>
                        <td>Row 1 Data 2</td>
                    </tr>
                    <tr>
                        <td>Row 2 Data 1</td>
                        <td>Row 2 Data 2</td>
                    </tr>
                    </tbody>
                </table>
            );
        case 'List':
            return (
                <ul>
                    <li>List item 1</li>
                    <li>List item 2</li>
                    <li>List item 3</li>
                </ul>
            );
        // 추가적인 태그에 대한 case를 필요에 따라 여기에 추가
        default:
            return null;
    }
}

export default Tag;
