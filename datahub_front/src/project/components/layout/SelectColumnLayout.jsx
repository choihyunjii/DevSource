import styles from '../createTableStyle.module.css';
import React, { useState } from "react";
<<<<<<< HEAD
import TableSearchLayout from "./TableSearchLayout";
=======
>>>>>>> origin/master
import ButtonUI from "../uI/ButtonUI";
import JoinColumnUI from "../uI/JoinColumnUI";

const initialRowState = {
    id: 1,
    columnName: '',
    dataType: 'int',
    pk: false,
    fk: false,
<<<<<<< HEAD
    uk: false
=======
    uk: false,
    joinTable: {}
>>>>>>> origin/master
};

export default function SelectColumnLayout({sendColumnData}) {
    const [rows, setRows] = useState([initialRowState]); // rows행 [initialRowState]로 초기화
    const [creationTimes, setCreationTimes] = useState([Date.now()]); // 각 행의 생성 시간을 기록

<<<<<<< HEAD

    function handleSelectChange(event) {
        const selectedValue = event.target.value;
        console.log("Selected value:", selectedValue);
=======
    function handleSelectChange(event, index) {
        const { name, value } = event.target;
        const updatedRows = [...rows];
        updatedRows[index][name] = value;
        setRows(updatedRows);
>>>>>>> origin/master
    }

    const handleAddRow = () => {
        const newRow = {
            id: rows.length + 1,
            columnName: '',
            dataType: 'int',
            pk: false,
            fk: false,
            uk: false,
<<<<<<< HEAD
            joinTable : ''
        };
        setRows([...rows, newRow]);
        console.log(rows)
        setCreationTimes([...creationTimes, Date.now()]); // 새로운 행의 생성 시간을 기록
=======
            joinTable : {}
        };
        setRows([...rows, newRow]);
        setCreationTimes([...creationTimes, Date.now()]); // 새로운 행의 생성 시간을 기록

        console.log(rows)
>>>>>>> origin/master
    };

    const handleDeleteRow = () => {
        if (rows.length >1) {
            const updatedRows = [...rows.slice(1)]; // 가장 오래된 행을 삭제
            setRows(updatedRows);
            const updatedCreationTimes = [...creationTimes.slice(1)]; // 가장 오래된 행의 생성 시간을 삭제
            setCreationTimes(updatedCreationTimes);
        }
    };
<<<<<<< HEAD
=======

>>>>>>> origin/master
    return (
        <div>
            <div className={styles.buttonBig}>
                <button onClick={handleAddRow} style={{marginRight: '2px'}}>+</button>
                <button onClick={handleDeleteRow}>-</button>
<<<<<<< HEAD

=======
>>>>>>> origin/master
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
<<<<<<< HEAD
                        <td><input type="text" className={styles.inputColumnName}/></td>
                        <td style={{width: '300px'}}>
                            <select className={styles.inputDataType} onChange={handleSelectChange}>
=======
                        <td><input type="text" name="columnName" value={row.columnName} onChange={(e) => handleSelectChange(e, index)} className={styles.inputColumnName}/></td>
                        <td style={{width: '300px'}}>
                            <select name="dataType" value={row.dataType} onChange={(e) => handleSelectChange(e, index)} className={styles.inputDataType}>
>>>>>>> origin/master
                                <option value="VARCHAR">VARCHAR</option>
                                <option value="INTEGER">INTEGER</option>
                                <option value="DATE">DATE</option>
                                <option value="BOOLEAN">BOOLEAN</option>
                            </select>
                        </td>
                        <td style={{width: '50px'}}>
<<<<<<< HEAD
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
=======
                            <input type="checkbox" name="pk" checked={row.pk} onChange={(e) => handleSelectChange(e, index)} className={styles.checkBox}/>
                        </td>
                        <td style={{width: '50px'}}>
                            <input type="checkbox" name="fk" checked={row.fk} onChange={(e) => handleSelectChange(e, index)} className={styles.checkBox}/>
                        </td>
                        <td style={{width: '50px'}}>
                            <input type="checkbox" name="uk" checked={row.uk} onChange={(e) => handleSelectChange(e, index)} className={styles.checkBox}/>
                        </td>
                        <td style={{width: '400px'}}>
                            <JoinColumnUI row={row} index={index} handleSelectChange={handleSelectChange}/>
>>>>>>> origin/master
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
            <ButtonUI children={"테이블 생성하기"} className={styles.buttonBox} onClick={sendColumnData}/>
        </div>
    );
<<<<<<< HEAD
}
=======
}
>>>>>>> origin/master
