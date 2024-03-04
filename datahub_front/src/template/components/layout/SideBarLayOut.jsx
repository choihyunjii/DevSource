import React, { useState } from 'react';
import sideBarCategoriesExampleData from '../data/SideBarCategoriesExampleData'; // 적절한 카테고리 정보를 가져오는 경로로 수정
import SideButtonUI from '../uI/SideButtonUI'; // SideButtonUI 컴포넌트의 경로에 따라 수정
import styles from '../../styleModule/sidebarStyle.module.css';
import TableCanvasLayOut from "./TableCanvasLayOut";
import TreeCanvasLayOut from "./TreeCanvasLayOut";
import CardCanvasLayOut from "./CardCanvasLayOut";
import cardExampleData from "../data/cardExampleData";
import ListMenuCanvas from "./ListMenuCanvasLayOut";

export default function SideBarLayOut() {
    const [selectedComponent, setSelectedComponent] = useState(null);

    const handleButtonClick = (category) => {
        console.log("Clicked category:", category); // 클릭된 카테고리를 콘솔에 출력
        setSelectedComponent(category); // 선택된 카테고리 설정
    };

    return (
        <div>
            <div className={styles.sidebar}>
                {sideBarCategoriesExampleData.map(category => (
                    <SideButtonUI
                        key={category.id}
                        category={category.category}
                        onClick={handleButtonClick}
                    />
                ))}
            </div>
            {/* 선택된 카테고리에 따라 해당 컴포넌트 렌더링 */}
            {selectedComponent === 'Card' && <CardCanvasLayOut exampleData={cardExampleData} />}
            {selectedComponent === 'List' && <ListMenuCanvas />}
            {selectedComponent === 'Table' && <TableCanvasLayOut/> }
            {selectedComponent === 'Tree' && <TreeCanvasLayOut/>}
        </div>
    );
}
