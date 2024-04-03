import React, { useEffect, useState } from "react";
import styles from '../../styles/styles.module.css'

export default function ChangeTableLayout({ tableId }) {
    const [tableData, setTableData] = useState({}); // 초기값을 일반 객체로 설정

    const changeColumData = async () => {
        try {
            if (!tableId) return; // tableId가 존재하지 않으면 요청하지 않음
            const response = await fetch(`http://localhost:8080/api/history/table/${tableId}`, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                }
            });
            const responseData = await response.json();
            setTableData(responseData);
            console.log(responseData)
        } catch (error) {
            console.error('Error fetching data:', error);
        }
    };

    useEffect(() => {
        setTableData({})
        changeColumData();
    }, [tableId]);

    return (
        <div>
            <table>
                <thead>
                <tr className={styles.columnNames}>
                    {Object.entries(tableData).map(([key, value], index) => (
                        <th className={styles.changeColumnName} key={index}>
                            {key}
                        </th>
                    ))}
                </tr>
                </thead>
                <tbody>
                {Object.entries(tableData).map(([key, value], index) => (
                    <td key={index} className={styles.td}>
                        {value.map((item, i) => (
                            <div key={i} className={`${styles.dataBox} ${item.action == 1 ? styles.redBox : styles.greenBox}`}>
                                {item.data}
                            </div>
                        ))}
                    </td>
                ))}
                </tbody>
            </table>
        </div>
    );
}
