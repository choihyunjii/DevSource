import styles from './modalStyle.module.css';
export default function ModalTextUI({text}){
    return(
        <div>
            <div className={styles.modalTextBox}>
                <div className={styles.modalText}>
                    {text}
                </div>

            </div>
        </div>
    )
}