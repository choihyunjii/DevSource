import React, { useState } from "react";
import styles from "../../styleModule/ColumnStyle.module.css";

export default function NewDataUI({ onAddData }) {
    const [newDataValue, setNewDataValue] = useState(""); // 새로운 데이터 입력값

    const handleNewDataInputChange = (event) => {
        setNewDataValue(event.target.value);
    };

    const handleNewDataInputBlur = () => {
        if (newDataValue.trim() !== "") {
            onAddData
            (
                newDataValue
            ); // 새로운 데이터를 추가하는 부모 컴포넌트의 콜백 함수 호출
            setNewDataValue(""); // 입력값 초기화
        }
    };

    return (
        <tr>
            <td className={`${styles.input} ${styles.newDataClass}`}>
                <input
                    className={styles.input}
                    type="text"
                    value={newDataValue}
                    onChange={handleNewDataInputChange}
                    onBlur={handleNewDataInputBlur}
                    placeholder="add Data"
                />
            </td>
        </tr>
    );
}
