import React, {useCallback, useEffect, useState} from "react";

export default function DataUI({ columnID }) {
    const [data, setData] = useState([]);
    const [editingIndex, setEditingIndex] = useState(-1); //편집중인 데이터 설정
    const [editValue, setEditValue] = useState("");

    //불 필요한 함수 호출을 방지하기 위해 useCallBack 활용.
    const fetchData = useCallback(async () => {
        try {
            const response = await fetch(`http://localhost:8080/api/data/${parseInt(columnID, 10)}`, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                }
            });
            const responseData = await response.json();

            const dataArray = Array.isArray(responseData) ? responseData : [responseData];
            setData(dataArray);
        } catch (error) {
            console.error('Error fetching data:', error);
        }
    }, [columnID]);


    useEffect(() => {
        fetchData();
    }, [columnID, fetchData]);


    const handleItemClick = (index) => {
        setEditingIndex(index);
        setEditValue(data[index].data);
    };

    const handleInputChange = (event) => {
        setEditValue(event.target.value);
    };

    const handleInputBlur = () => {
        if (editingIndex !== -1) {
            const newData = [...data];
            newData[editingIndex].data = editValue;
            setData(newData);
            setEditingIndex(-1);
        }
    };

    return (
        <div>
            <table>
                <tbody>
                {data.map((item, index) => (
                    <tr key={item.id} onDoubleClick={() => handleItemClick(index)}>
                        {editingIndex === index ? (
                            <td>
                                <input
                                    type="text"
                                    value={editValue}
                                    onChange={handleInputChange}
                                    onBlur={handleInputBlur}
                                />
                            </td>
                        ) : (
                            <td>{item.data}</td>
                        )}
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
}
