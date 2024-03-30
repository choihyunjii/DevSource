import styles from '../signUpStyle.module.css';
import LogoUI from "../../../Logo/LogoUI";
import InputBoxUI from "../../../Input/InputBoxUI";
import InputIdBoxUI from "../uI/InputIdBoxUI";
import {useState} from "react";
import BigButton from "../../../Button/BigButton";

export default function SignUpLayout() {

    const [name,setName]= useState(" ");
    const [phoneNum,setPhoneNum]= useState(" ");
    const [email,setEmail]= useState(" ");
    const [id,setId]= useState(" ");
    const [pw,setPw]= useState(" ");

    const saveUserData = () => {
        let obj = {
            name:name,
            phoneNum:phoneNum,
            email:email,
            id:id,
            pw:pw
        }
    }

    return(
        <div>
            <div className={styles.signUpContainer}>
                <div className={styles.logoContainer}>
                    <div className={styles.logoBox}>
                        <LogoUI/>
                    </div>
                </div>
                <div className={styles.inputContainer}>
                    <div className={styles.box}>
                        <InputBoxUI name="이름"/>
                        <InputBoxUI name="전화번호"/>
                        <InputBoxUI name="이메일"/>
                        <InputIdBoxUI/>
                        <InputBoxUI name="비밀번호"/>
                        <InputBoxUI name="비밀번호 확인"/>
                    </div>
                </div>
                <BigButton text="회원가입"/>
            </div>
        </div>
    )
}