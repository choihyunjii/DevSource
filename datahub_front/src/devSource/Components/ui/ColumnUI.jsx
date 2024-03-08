import React, {useRef, useState} from "react";
import DataUI from "./DataUI";
import styles from '../../styleModule/ColumnStyle.module.css';
import up from '../../Image/upButton.png';
import down from '../../Image/downButton.png';
import { Button } from "./ButtonUI";
import Button_UI from "./Button_UI";
import { Image } from "react-bootstrap";

export default function ColumnUI({ columns , reloadData}) {
    const [clickCount, setClickCount] = useState(0);
    const [selectedRowIndex, setSelectedRowIndex] = useState(-1); // 선택된 행 인덱스
    const [deleteRowIndex , setDeleteRowIndex] = useState([])

    //DeleteRowIndex 행을 선택해주는 메소드
    const handleDeleteData = () => {
        if (selectedRowIndex !== -1) {
            setDeleteRowIndex([...deleteRowIndex, selectedRowIndex]);
        }
    };
    //
    const handleRollBackData = () => {
        if (selectedRowIndex !== -1) {
            if (deleteRowIndex.includes(selectedRowIndex)) {
                const updatedDeleteRow
                    = deleteRowIndex.filter(index => index !== selectedRowIndex);
                setDeleteRowIndex(updatedDeleteRow);
            }
        }
    };

    // 선택된 행의 인덱스를 설정하는 함수
    const handleRowClick = (index) => {
        console.log(index)
        setSelectedRowIndex(index);
    };

    const handleReload = () => {
        console.log("실행")
        reloadData()
    };
    const handlePushData = () =>{
        setClickCount(clickCount +1)
    }

    return (
        <div>
            <div className={styles.button}>
                <ul className={styles.menuIconBox}>
                    <div className={styles.leftIcon}>
                        <Button_UI image={Button[0].image} onClick={handleReload} />
                    </div>
                    <div className={styles.rightIcon}>
                        <Button_UI image={Button[1].image} onClick={handlePushData}/>
                        <Button_UI image={Button[2].image} onClick={handleDeleteData}/>
                        <Button_UI image={Button[3].image} />
                        <Button_UI image={Button[4].image} />
                        <Button_UI image={Button[5].image} onClick={handleRollBackData}/>
                    </div>
                </ul>
                <table className={styles.table}>
                    <thead>
                    <tr>
                        {columns.map((column, index) => (
                            <th key={index}>
                                <div className={styles.columnContainer}>
                                    <div style={{ display: 'flex', paddingLeft: '10px' }}>
                                        {column.name}
                                        <div className={styles.imageContainer}>
                                            <Image src={up} />
                                            <Image src={down} />
                                        </div>
                                    </div>
                                </div>
                            </th>
                        ))}
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        {columns.map((column, index) => (
                            <td key={index}>
                                <DataUI
                                    columnID={column.id}
                                    newDataCount={clickCount}
                                    selectedRowIndex={selectedRowIndex} // 선택된 행 인덱스 전달
                                    onRowClick={handleRowClick} // 행 클릭 핸들러 전달
                                    deleteRow = {deleteRowIndex}
                                />
                            </td>
                        ))}
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    );
}
