import React, { useEffect, useState } from "react";

export default function DataUI({ columnID }) {
    const [data, setData] = useState([]);
    const [editingIndex, setEditingIndex] = useState(-1); //편집중인 데이터 설정
    const [editValue, setEditValue] = useState("");

    const fetchData = async () => {
        try {
            const response = await fetch(`http://localhost:8080/api/data/${columnID}`, {
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
    };

    useEffect(() => {
        fetchData();
    }, [columnID]);

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
