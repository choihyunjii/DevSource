import styles from '../createTableStyle.module.css';
import dataTypeNo from '../../image/dataTypeNo.png';


export default function SelectHeaderUI(){
    return(
        <div>
            <table className={styles.selectHeaderTable}>
                <thead>
                <tr>
                    <th>컬럼이름</th>
                    <th >데이터타입</th>
                    <th >PK</th>
                    <th >FK</th>
                    <th >UK</th>
                    <th >조인</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td><input style={{width:'170px'}} type="text" className={styles.inputColumnName}/></td>
                    <td style={{width:'170px'}}>
                        <div className={styles.inputDataType}><img src={dataTypeNo} style={{width:'18px'}} className={styles.imgNo}/></div>
                    </td>
                    <td style={{width:'43px'}}></td>
                    <td style={{width:'43px'}}> </td>
                    <td style={{width:'43px'}}> </td>
                    <td><input style={{width:'250px'}} type="text"/></td>
                </tr>
                {/* 필요한 만큼 위와 같은 방식으로 추가할 수 있습니다 */}
                </tbody>
            </table>
        </div>
    )
}