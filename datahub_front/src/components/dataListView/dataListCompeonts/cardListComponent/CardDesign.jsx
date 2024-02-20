import React from 'react';
import './Card.css'; // CSS 파일을 불러옵니다. (카드 디자인을 위한 스타일)

// 카드 컴포넌트 정의
function Card({ title, image, description }) {
    return (
        <div className="card">
            <div className="card-image-container"> {/* 이미지를 감싸는 div */}
                <img src={image} alt={title} className="card-image" />
            </div>
            <div className="card-content">
                <h2 className="card-title">{title}</h2>
                <p className="card-description">{description}</p>
            </div>
            <div className="card-button-container">
                <button className="card-button">보기</button>
            </div>
        </div>
    );
}

export default Card;
