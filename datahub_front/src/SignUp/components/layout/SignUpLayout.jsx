// signUpLayout.js
import styles from '../signUpStyle.module.css';
import LogoUI from "../../../Login/components/uI/LogoUI";
import InputBoxUI from "../../../Input/InputBoxUI";
import { useState } from "react";
import BigButton from "../../../Button/BigButton";
import NotMatchPWLayout from "../../../Modal/NotMatchPWLayout";
import ModalLayOut from "../../../project/components/layout/ModalLayOut";

export default function SignUpLayout({ data }) {
    const [name, setName] = useState("");
    const [phoneNum, setPhoneNum] = useState("");
    const [email, setEmail] = useState("");
    const [pw, setPW] = useState("");
    const [checkPW, setCheckPW] = useState("");
    const [falsePwModal, setFalsePwModal] = useState(false);
    const [isSendModalOpen , setIsSendModalOpen] = useState(false);

    const checkPasswordMatch = () => {
        if (pw === checkPW) {


            return true;
        } else {
            return false;
        }
    }

    const saveUserData = async () => {
        if (!checkPasswordMatch()) {
            console.log("Password and Confirm Password do not match.");
            setFalsePwModal(true);
        } else {
            let obj = {
                name: name,
                phoneNum: phoneNum,
                email: email,
                pw: pw,
            }
            console.log(obj);
            try {
                const response = await fetch('http://localhost:8080/api/profile', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(obj)
                });
                const responseData = await response.json();
                if (response.ok) {
                    console.log('Data sent successfully:', responseData);
                    console.log(responseData.message);
                } else {
                    throw new Error(responseData.message);
                }
                console.log(obj);
            } catch (error) {
                console.error('Error sending data:', error);
            }
        }
    }

    return (
        <div>
            <div className={styles.signUpContainer}>
                <div className={styles.logoContainer}>
                    <div className={styles.logoBox}>
                        <LogoUI />
                    </div>
                </div>
                <div className={styles.inputContainer}>
                    <div className={styles.box}>
                        <InputBoxUI name="이름" way="text" setName={setName} />
                        <InputBoxUI name="전화번호" way="text" setName={setPhoneNum} />
                        <InputBoxUI name="이메일" way="text" setName={setEmail} />
                        <InputBoxUI name="비밀번호" way="password" setName={setPW} />
                        <InputBoxUI name="비밀번호 확인" way="password" setName={setCheckPW}/>
                    </div>
                </div>
                <div className={styles.ButtonBox}>
                    <BigButton text="회원가입" onClick={() => setIsSendModalOpen(true)} />
                    <ModalLayOut
                        isOpen={isSendModalOpen}
                        data={"회원가입을 진행하시겠습니까?"}
                        onClose={() => setIsSendModalOpen(false)}
                        onClickEvent={saveUserData}
                    />
                </div>
            </div>

            {falsePwModal && (
                <NotMatchPWLayout onclick={() => setFalsePwModal(false)} />
            )}
        </div>
    )
}
