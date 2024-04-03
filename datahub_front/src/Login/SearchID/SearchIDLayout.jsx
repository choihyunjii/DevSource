import styles from './SearchIDStyle.module.css';
import LogoUI from "../../Logo/LogoUI";
import SelectUI from "./SelectUI";
import InputBoxUI from "../../Input/InputBoxPWUI";
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