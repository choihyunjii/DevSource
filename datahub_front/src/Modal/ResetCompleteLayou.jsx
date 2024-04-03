import styles from './modalStyle.module.css';
import ModalTextUI from "./ModalTextUI";
import SendButton from "../Button/SendButton";

export default function ResetCompleteLayout() {
    return(
        <div>
            <div className={styles.modalBox}>
                <ModalTextUI text="회원가입이 완료되었습니다."/>
                <SendButton text="돌아가기"/>
            </div>

        </div>
    )
}