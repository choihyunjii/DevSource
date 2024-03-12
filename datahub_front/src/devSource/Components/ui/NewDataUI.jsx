import React, { useState } from "react";
import styles from "../../styleModule/ColumnStyle.module.css";

export default function NewDataUI({ column, columnCount  , createData ,setCreateData , dataLine }) {
    const [newDataValues, setNewDataValues] = useState(new Array(columnCount).fill("")); // 각 컬럼의 새로운 데이터 입력값
    const [line , setLine]  = useState(dataLine)
    const handleNewDataInputChange = (event, columnIndex) => {
        const updatedValues = [...newDataValues];
        updatedValues[columnIndex] = event.target.value;
        setNewDataValues(updatedValues);
    };

    const handleInputBlur = (event, index, value) => {
        const newCreateData = [...createData]; // 기존의 updateData

        let obj = {
            data : value,
            column : column,
            dataLine : line
        }
        setLine(dataLine + 1)
        newCreateData.push(obj); // 새로운 아이템을 추가
        setCreateData(newCreateData); // 업데이트된 데이터를 설정
    };

    return (
        <tr>
            {/* 각 컬럼에 대한 입력란 생성 */}
            {newDataValues.map((value, index) => (
                <td key={index} className={styles.newDataClass}  >
                    <input
                        className={styles.newDataInput}
                        type="text"
                        value={value}
                        onBlur={(event) => handleInputBlur(event, index,value)} // 입력창을 떠날 때 호출
                        onChange={(event) => handleNewDataInputChange(event, index)}
                        // onBlur={handleNewDataInputBlur}
                        placeholder="NULL"
                    />
                </td>
            ))}
        </tr>
    );
}

