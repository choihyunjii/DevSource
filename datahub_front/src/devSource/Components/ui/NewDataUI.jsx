import React, { useState } from "react";
import styles from "../../styleModule/ColumnStyle.module.css";

export default function NewDataUI({ onAddData, columnCount }) {
    const [newDataValues, setNewDataValues] = useState(new Array(columnCount).fill("")); // 각 컬럼의 새로운 데이터 입력값

    const handleNewDataInputChange = (event, columnIndex) => {
        const updatedValues = [...newDataValues];
        updatedValues[columnIndex] = event.target.value;
        setNewDataValues(updatedValues);
    };

    // const handleNewDataInputBlur = () => {
    //     // 모든 컬럼의 데이터가 입력되었는지 확인
    //     if (newDataValues.every(value => value.trim() !== "")) {
    //         onAddData(newDataValues); // 새로운 데이터를 추가하는 부모 컴포넌트의 콜백 함수 호출
    //         setNewDataValues(new Array(columnCount).fill("")); // 입력값 초기화
    //     }
    //     //이부분 수정하기!
    // };

    return (
        <tr>
            {/* 각 컬럼에 대한 입력란 생성 */}
            {newDataValues.map((value, index) => (
                <td key={index} className={styles.newDataClass}  >
                    <input
                        className={styles.newDataInput}
                        type="text"
                        value={value}
                        onChange={(event) => handleNewDataInputChange(event, index)}
                        // onBlur={handleNewDataInputBlur}
                        placeholder="NULL"
                    />
                </td>
            ))}
        </tr>
    );
}

