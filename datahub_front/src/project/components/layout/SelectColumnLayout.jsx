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
    const [showSearch, setShowSearch] = useState(true);
    const [rows, setRows] = useState([initialRowState]);
    const [lastAddedRowIds, setLastAddedRowIds] = useState([]);

    const renderSearch = () => {
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
        setLastAddedRowIds([...lastAddedRowIds, newRow.id]); // 새로 추가된 행의 ID를 기억함
    };

    const handleDeleteRow = () => {
        if (lastAddedRowIds.length > 0) {
            const updatedRows = rows.filter(row => !lastAddedRowIds.includes(row.id));
            setRows(updatedRows);
            setLastAddedRowIds([]); // 모든 추가된 행의 ID 초기화
        }
    };


    return (
        <div>
            <div className={styles.buttonBig}>
                <button onClick={handleAddRow}>+</button>
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
                {rows.map(row => (
                    <tr key={row.id}>
                        <td><input style={{width: '170px'}} type="text" className={styles.inputColumnName}/></td>
                        <td style={{width: '170px'}}>
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
                        <td style={{width: '43px'}}>
                            <input type="checkbox" value="fk" className={styles.checkBox}/>
                        </td>
                        <td style={{width: '43px'}}>
                            <input type="checkbox" value="uk" className={styles.checkBox}/>
                        </td>
                        <td style={{width: '250px'}}>
                            <button onClick={renderSearch} className={styles.searchButton}>검색 ▼</button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
            <div className={styles.searchBox}>
                {showSearch && <TableSearchLayout/>}
            </div>

        </div>
    );
}
