import styles from '../createTableStyle.module.css';

export default function SelectInputUI() {
    return (
        /* <div>
             <input className={styles.inputColumnName} type="text"/>
         </div>*/

            <tbody>
            <tr>
                <td><input className={styles.inputColumnName} type="text"/></td>
                <td className={styles.dataType}></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            {/* 필요한 만큼 위와 같은 방식으로 추가할 수 있습니다 */}
            </tbody>

    )
}