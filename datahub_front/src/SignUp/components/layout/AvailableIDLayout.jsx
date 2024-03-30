import styles from '../modalStyle.module.css';
import ModalTextUI from "../uI/ModalTextUI";
import SendButton from "../../../Button/SendButton";
export default function AvailableIDLayout(){
    return(
        <div>
            <div className={styles.modalBox}>
                <ModalTextUI text="사용 가능한 아이디입니다."/>
                <SendButton text="돌아가기"/>
            </div>

        </div>
    )
}