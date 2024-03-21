import styles from '../createTableStyle.module.css';

export default function SelectInputUI() {
    return(
        <div>
            <table className={styles.selectHeaderTable}>
                <tbody>
                <tr>
                    <td>
                        <input className={styles.inputColumnName} type="text"/>
                    </td>
                </tr>
                <tr>
                    <td>
                    </td>
                </tr>
                </tbody>
            </table>

        </div>
    )
}