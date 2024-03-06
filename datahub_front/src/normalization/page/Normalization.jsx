import {useState} from "react";
import NormalizationColumnHeader from "../ui/NormalizationColumnHeader";
import ButtonUI from "../../project/components/uI/ButtonUI";


export default function Normalization(){
    const [normalizationColumnData , setNormalizationColumnData] = useState([])

    const exampleColumnData = ["학생정보","성적","강좌이름","강의실"]

    const updateNormalizationColumnData = (column) => {
        console.log(`선택한 데이터 ${column}`);
        setNormalizationColumnData((prevData) => [...prevData, column]);
    };

    const normalizationColumnDataSend = async () => {
        console.log(`현재 데이터 목록 ${normalizationColumnData}`)

        let normalData = {
            normalizationStep: 1,
            normalizationColumns: normalizationColumnData
        }
        try {
            const response = await fetch('http://localhost:8080/api/normalization', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(normalData)
            });

            if (response.ok) {
                const responseData = await response.json();
                console.log('Data sent successfully:', responseData);

            } else {
                const errorData = await response.json();
                console.log(errorData)
            }

        } catch (error) {
            console.error('Error sending data:', error);
        }
    }

    return(
        <div>
            <table>
                <thead id="example-table-header">
                    <NormalizationColumnHeader
                        columns={exampleColumnData}
                        selectColumnData={updateNormalizationColumnData}/>
                </thead>
                <tbody>

                </tbody>
            </table>
            <ButtonUI
                children={"전송"}
                onClick={normalizationColumnDataSend}
            />
        </div>
    )

}