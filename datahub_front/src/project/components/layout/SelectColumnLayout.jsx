import styles from '../createTableStyle.module.css';
import React, { useState } from "react";
import TableSearchLayout from "./TableSearchLayout";
import ButtonUI from "../uI/ButtonUI";
import JoinColumnUI from "../uI/JoinColumnUI";

const initialRowState = {
    id: 1,
    columnName: '',
    dataType: 'int',
    pk: false,
    fk: false,
    uk: false
};

export default function SelectColumnLayout({sendColumnData}) {
    const [rows, setRows] = useState([initialRowState]); // rows행 [initialRowState]로 초기화
    const [creationTimes, setCreationTimes] = useState([Date.now()]); // 각 행의 생성 시간을 기록


    function handleSelectChange(event) {
        const selectedValue = event.target.value;
        console.log("Selected value:", selectedValue);
    }

    const handleAddRow = () => {
        const newRow = {
            id: rows.length + 1,
            columnName: '',
            dataType: 'int',
            pk: false,
            fk: false,
            uk: false,
            joinTable : ''
        };
        setRows([...rows, newRow]);
        console.log(rows)
        setCreationTimes([...creationTimes, Date.now()]); // 새로운 행의 생성 시간을 기록
    };

    const handleDeleteRow = () => {
        if (rows.length >1) {
            const updatedRows = [...rows.slice(1)]; // 가장 오래된 행을 삭제
            setRows(updatedRows);
            const updatedCreationTimes = [...creationTimes.slice(1)]; // 가장 오래된 행의 생성 시간을 삭제
            setCreationTimes(updatedCreationTimes);
        }
    };
    return (
        <div>
            <div className={styles.buttonBig}>
                <button onClick={handleAddRow} style={{marginRight: '2px'}}>+</button>
                <button onClick={handleDeleteRow}>-</button>

            </div>

            <table className={styles.selectHeaderTable}>
                <thead>
                <tr>
                    <th>컬럼이름</th>
                    <th>데이터타입</th>
                    <th>PK</th>
                    <th>FK</th>
                    <th>UK</th>
                    <th>조인</th>
                </tr>
                </thead>
                <tbody id="tableBody">
                {rows.map((row, index) => (
                    <tr key={index}>
                        <td><input type="text" className={styles.inputColumnName}/></td>
                        <td style={{width: '300px'}}>
                            <select className={styles.inputDataType} onChange={handleSelectChange}>
                                <option value="VARCHAR">VARCHAR</option>
                                <option value="INTEGER">INTEGER</option>
                                <option value="DATE">DATE</option>
                                <option value="BOOLEAN">BOOLEAN</option>
                            </select>
                        </td>
                        <td style={{width: '50px'}}>
                            <input type="checkbox" value="pk" className={styles.checkBox}/>
                        </td>
                        <td style={{width: '50px'}}>
                            <input type="checkbox" value="fk" className={styles.checkBox}/>
                        </td>
                        <td style={{width: '50px'}}>
                            <input type="checkbox" value="uk" className={styles.checkBox}/>
                        </td>
                        <td style={{width: '400px'}}>
                            <JoinColumnUI/>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
            <ButtonUI children={"테이블 생성하기"} className={styles.buttonBox} onClick={sendColumnData}/>
        </div>
    );
}