import {useEffect, useState} from "react";
import ColumnUI from "../ui/ColumnUI";

export default function TableLayout(){
    const [columnData, setColumnData] = useState([]);
    const [tableID, setTableID] = useState(1);

    const fetchColumData = async () => {
        try {
            const response = await fetch(`http://localhost:8080/api/column/${tableID}`, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                }
            });
            const responseData = await response.json();

            const dataArray = Array.isArray(responseData) ? responseData : [responseData]; // 받은 데이터가 배열이 아니면 배열로 변환
            setColumnData(dataArray);

            // console.log(dataArray)
        } catch (error) {
            console.error('Error fetching data:', error);
        }
    };

    useEffect(() => {
        fetchColumData(); // 컴포넌트가 처음 렌더링될 때 열 데이터를 가져오도록 호출
    }, [tableID]); // tableID가 변경될 때마다 호출되도록 설정



    return (
        <div>
            <ColumnUI columns={columnData} />
        </div>
    );
}