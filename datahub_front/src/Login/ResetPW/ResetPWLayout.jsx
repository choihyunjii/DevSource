import styles from './ResetPWStyle.module.css';
import LogoUI from "../../Logo/LogoUI";
import InputBoxUI from "../../Input/InputBoxPWUI";
import BigButton from "../../Button/BigButton";
import {useState} from "react";
export default function ResetPWLayout(){

    const [email,setEmail]= useState(" ");
    const [pw,setPw]= useState(" ");
    const saveUserData = () => {
        let obj = {
            email:email,
            pw:pw
        }
    }

    return(
        <div>
            <div className={styles.Container}>
                <div className={styles.logoContainer}>
                    <LogoUI/>
                </div>
                <div className={styles.inputContainer}>
                    <div className={styles.box}>
                        <InputBoxUI name="이메일" way="text"/>
                        <InputBoxUI name="비밀번호 재설정" way="password"/>
                        <InputBoxUI name="비밀번호 확인" way="password"/>
                    </div>
                </div>
                <div className={styles.buttonBox}>
                    <BigButton text="확인"/>

                </div>

            </div>

        </div>
    )
}