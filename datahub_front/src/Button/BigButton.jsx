import styles from './bigButtonStyle.module.css';
export default function BigButton({text,onClick}){




    return(
        <div>
            <div className={styles.ButtonBox}>
                <button type="button" className={styles.button} onClick={onClick} >{text}</button>
            </div>

        </div>
    )
}
