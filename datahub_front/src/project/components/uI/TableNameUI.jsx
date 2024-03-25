import styles from '../createTableStyle.module.css';

export default function TableNameUI({name}){
    return(
        <div className={styles.tableName}>
            {name}
        </div>
    )
}