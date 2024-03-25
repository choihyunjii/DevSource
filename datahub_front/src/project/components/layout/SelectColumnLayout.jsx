import styles from '../createTableStyle.module.css';
import React, { useState } from "react";
import TableSearchLayout from "./TableSearchLayout";

const initialRowState = {
    id: 1,
    columnName: '',
    dataType: 'int',
    pk: false,
    fk: false,
    uk: false
};

export default function SelectColumnLayout() {
    const [showSearch, setShowSearch] = useState(false); // searchBox 렌더링
    const [rows, setRows] = useState([initialRowState]); // rows행 [initialRowState]로 초기화
    const [creationTimes, setCreationTimes] = useState([Date.now()]); // 각 행의 생성 시간을 기록

    const renderSearch = () => { //
        setShowSearch(prevState => !prevState);
    };

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
            uk: false
        };
        setRows([...rows, newRow]);
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
                        <td><input style={{width: '210px'}} type="text" className={styles.inputColumnName}/></td>
                        <td style={{width: '210px'}}>
                            <select className={styles.inputDataType} onChange={handleSelectChange}>
                                <optgroup label="Integer">
                                    <option value="int">INT</option>
                                    <option value="bigInt">BIGINT</option>
                                    <option value="smallInt">SMALLINT</option>
                                </optgroup>
                                <optgroup label="String">
                                    <option value="char">CHAR</option>
                                    <option value="varchar">VARCHAR</option>
                                </optgroup>
                            </select>
                        </td>
                        <td style={{width: '45px'}}>
                            <input type="checkbox" value="pk" className={styles.checkBox}/>
                        </td>
                        <td style={{width: '45px'}}>
                            <input type="checkbox" value="fk" className={styles.checkBox}/>
                        </td>
                        <td style={{width: '45px'}}>
                            <input type="checkbox" value="uk" className={styles.checkBox}/>
                        </td>
                        <td style={{width: '300px'}}>
                            <button onClick={renderSearch} className={styles.searchButton}>검색 ▼</button>
                        </td>

                    </tr>
                ))}
                </tbody>
            </table>
            <div className={styles.searchBox}>
                {showSearch && <TableSearchLayout/>}
            </div>
          {/*  <div className={styles.buttonContainer}>
                <button type="button">테이블 생성</button>
                //버튼이 밀려서 이거 어케 할지 고민중이에욤..
            </div>*/}

        </div>
    );
}
