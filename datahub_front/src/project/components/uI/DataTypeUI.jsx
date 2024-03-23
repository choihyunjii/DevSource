import styles from '../createTableStyle.module.css';

export default function DataTypeUI(){
    return(
        <div>
            <div className={styles.types}>
                <table>
                    <tbody>
                    <tr>
                        <td>Int</td>
                    </tr>
                    <tr>
                        <td>char</td>
                    </tr>
                    <tr>
                        <td>varchar</td>
                    </tr>

                    </tbody>
                </table>
            </div>

        </div>
    )
}