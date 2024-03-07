import React, { useEffect, useState } from "react";
import styles from "../../styleModule/ColumnStyle.module.css";
import NewDataUI from "./NewDataUI";

export default function DataUI({ columnID, newDataCount, selectedRowIndex, onRowClick, isDataReady }) {
    const [data, setData] = useState([]);
    const [editingIndex, setEditingIndex] = useState(-1); // 편집중인 데이터 인덱스

    // 서버에서 데이터를 가져오는 함수
    const fetchData = async () => {
        try {
            const response = await fetch(`http://localhost:8080/api/data/${columnID}`, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                }
            });
            const responseData = await response.json();
            setData(responseData);
        } catch (error) {
            console.error('Error fetching data:', error);
        }
    };

    // 컴포넌트가 마운트될 때와 열의 ID가 변경될 때마다 데이터를 가져옴
    useEffect(() => {
        fetchData();
    }, [columnID]);


    // 행을 클릭할 때 실행되는 함수
    const handleClick = (index) => {
        setEditingIndex(index); // 행을 더블 클릭하여 입력창으로 변경
    };

    // 입력값을 저장하는 핸들러 함수
    const handleInputBlur = () => {
        setEditingIndex(-1); // 입력창이 포커스를 잃으면 편집 상태를 종료
    };

    const handleInputChange = (event, index) => {
        const newData = [...data];
        newData[index].data = event.target.value;
        setData(newData); // 변경된 데이터 업데이트
    };

    // 새로운 데이터 추가 시 호출되는 함수
    const handleAddData = (newData) => {
        setData([...data, { id: data.length + 1, data: newData }]);
    };

    return (
        <div>
            <table>
                <tbody>
                {/* 데이터 출력 */}
                {data.map((item, index) => (
                    <tr
                        key={index}
                        onDoubleClick={() => handleClick(index)} // 행 더블 클릭 시 핸들러 호출
                        onClick={() => onRowClick(index)}
                        style={{ backgroundColor: index === selectedRowIndex ? 'lightgray' : 'transparent' }} // 선택된 행에 따라 색상 변경
                    >
                        <td>
                            {editingIndex === index ? (
                                <input
                                    className={styles.input}
                                    type="text"
                                    defaultValue={item.data || ''}
                                    onBlur={handleInputBlur} // 입력창이 포커스를 잃으면 편집 상태를 종료
                                    onChange={(event) => handleInputChange(event, index)} // 입력값이 변경되면 핸들러 호출
                                />
                            ) : (
                                <span>{item.data || 'NULL'}</span>
                            )}
                        </td>
                    </tr>
                ))}
                {/* 새로운 데이터 입력 부분 */}
                {[...Array(newDataCount)].map((_, index) => (
                    <NewDataUI key={index} onAddData={handleAddData} isDataReady={isDataReady} />
                ))}                </tbody>
            </table>
        </div>
    );
}
