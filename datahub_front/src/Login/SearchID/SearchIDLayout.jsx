import styles from './SearchIDStyle.module.css';
import LogoUI from "../components/uI/LogoUI";
import SelectUI from "./SelectUI";
import InputBoxUI from "../../Input/InputBoxUI";
import BigButton from "../../Button/BigButton";
import TextButton from "../../Button/TextButton";
export default function SearchIDLayout(){
    return(
        <div>
            <div className={styles.Container}>
                <div className={styles.logoContainer}>
                    <LogoUI/>
                </div>
                <div className={styles.inputContainer}>
                    <SelectUI/>

                </div>
                <div className={styles.button}>
                    <BigButton text="아이디 찾기"/>
                </div>
                <div className={styles.text} >
                    <TextButton text="비밀번호 재설정"/>
                </div>

            </div>
        </div>
    )
}