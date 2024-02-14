import React from 'react';
import Data from "./Data";
import './style.css'
import {Button} from "react-bootstrap";

export default function DataSet() {
    const dataSetImageData = [
        {
            "text": "테이블",
            "imageSrc": "image/table.png"
        },
        {
            "text": "리스트",
            "imageSrc": "image/textList.png"
        }
    ];

    return (
        <div className="rectangle-container">
            <section id="name">Data Set</section>
            <div vertical aria-label='Vertical button group'>
                {dataSetImageData.map((data, index) => (
                    <Button key={index}>
                        <Data image={data.image} title={data.text}/>
                    </Button>
                ))}
            </div>
        </div>
    );
}