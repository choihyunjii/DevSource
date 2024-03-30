import styles from '../loginStyle.module.css';
import LogoUI from "../../../Logo/LogoUI";
import InputBoxUI from "../../../Input/InputBoxUI";
import BigButton from "../../../Button/BigButton";


export default function LoginLayout(){
    return(
        <div>
            <div className={styles.loginContainer}>
                <div className={styles.logoContainer}>
                    <LogoUI/>
                </div>
                <div className={styles.inputContainer}>
                    <div className={styles.box}>
                        <InputBoxUI name="아이디"/>
                        <InputBoxUI name="비밀번호"/>
                    </div>
                </div>
                <div className={styles.buttonContainer}>
                    <BigButton text="로그인"/>
                </div>

            </div>


        </div>
    )
}