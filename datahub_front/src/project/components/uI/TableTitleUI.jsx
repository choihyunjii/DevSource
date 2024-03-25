import styles from '../createTableStyle.module.css';

export default function TableTitleUI({title}){
    return(
        <div className={styles.tableTitle}>
            <div className={styles.title}>
                {title}
            </div>
        </div>
    )
}