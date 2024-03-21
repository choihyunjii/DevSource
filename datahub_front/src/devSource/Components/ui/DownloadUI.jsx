import styles from '../../styleModule/DownloadStyle.module.css'
import {useState} from "react";
export default function DownloadUI(){
    const [tableID , setTableID] = useState(1)
    const [table , setTable] = useState()

    const fetchColumData = async () => {
        try {
            const response = await fetch(`http://localhost:8080/api/table/${tableID}`, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                }
            });
            const responseData = await response.json();
            console.log(responseData)
        } catch (error) {
            console.error('Error fetching data:', error);
        }
    };
    const dataFormat = (data) => {
        const numRows = data.table.keys.size // 행의 개수
        const numCols = 5; // 열의 개수

        const array = new Array(numRows); // 행의 개수만큼 배열 생성

        for (let i = 0; i < numRows; i++) {
            array[i] = new Array(numCols).fill(""); // 각 행마다 빈 문자열("")로 열의 개수만큼 초기화
        }

        console.log(array);

    }
    const downloadBtnOnClickEvent= () =>{
        fetchColumData()
    }

    return(
        <div>
            <div className={styles.downloadBox}>
                <p className={styles.downloadText} onClick={downloadBtnOnClickEvent}>엑셀 다운로드</p>
            </div>
        </div>
    )
}