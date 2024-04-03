import styles from './modalStyle.module.css';
import ModalTextUI from "./ModalTextUI";
import SendButton from "../Button/SendButton";
export default function NotMatchPWLayout({onclick}){
    return(
        <div>
            <div className={styles.modalBox}>
                <ModalTextUI text="비밀번호가 일치하지 않습니다."/>
                <SendButton text="돌아가기" onClick={onclick}/>
            </div>

        </div>
    )
}