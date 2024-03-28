import styles from '../modalStyle.module.css';
import ModalTextUI from "../uI/ModalTextUI";

export default function ModalLayout() {
    return(
        <div>
            <div className={styles.modalBox}>
                <ModalTextUI text="사용 불가능한 아이디입니다."/>

            </div>

        </div>
    )
}