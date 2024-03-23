import styles from '../createTableStyle.module.css';
import dataTypeNo from '../../image/dataTypeNo.png';
import dataTypeYes from '../../image/dataTypeYes.png';
import {useState} from "react";


export default function SelectHeaderUI(){
    const [imgSrc, setImg] = useState(dataTypeNo);
    const [isInitial, setIsInitial] = useState(true); // 초기 이미지 상태 여부
    const [showDataTypes, setShowDataTypes]= useState(true);
    const toggleShowDataTypes = () => {
        setShowDataTypes(prevState => !prevState);
    };

    const handleClick = () => {
        if (isInitial) {
            setImg(dataTypeYes);

        } else {
            setImg(dataTypeNo);
        }
        setIsInitial(!isInitial);
        toggleShowDataTypes();
    };

    return(
        <div>
            <table className={styles.selectHeaderTable}>
                <thead>
                <tr>
                    <th>컬럼이름</th>
                    <th>데이터타입</th>
                    <th>PK</th>
                    <th>FK</th>
                    <th>UK</th>
                    <th>조인</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td><input style={{width:'170px'}} type="text" className={styles.inputColumnName}/></td>
                    <td style={{width:'170px'}}>
                        <div className={styles.inputDataType}><img src={imgSrc} style={{width:'16px'}} className={styles.typeNo} onClick={handleClick}/></div>
                    </td>
                    <td style={{width:'43px'}}></td>
                    <td style={{width:'43px'}}> </td>
                    <td style={{width:'43px'}}> </td>
                    <td><input style={{width:'250px'}} type="text"/></td>
                </tr>
                </tbody>
            </table>
        </div>
    )
}