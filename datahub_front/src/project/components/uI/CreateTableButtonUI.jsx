import styles from '../createTableStyle.module.css';
export default function CreateTableButtonUI(){
    return(
        <div>
            <button type="button" className={styles.buttonBox} >
                테이블 생성
            </button>
        </div>
    )
}