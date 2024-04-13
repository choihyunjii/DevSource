import styles from '../loginStyle.module.css';
import LogoUI from "../uI/LogoUI";
import InputBoxUI from "../../../Input/InputBoxUI";
import BigButton from "../../../Button/BigButton";
import TextButton from "../../../Button/TextButton";
import talk from '../image/kakao.png';
import google from '../image/google.png';
import naver from '../image/naver.png';
import {useState} from "react";
import ModalLayOut from "../../../project/components/layout/ModalLayOut";

export default function LoginLayout(){
    const [id,setID] = useState("");
    const [pw,setPW] = useState("");
    const [isSendModalOpen , setIsSendModalOpen] = useState(false);
    const confirmUserData = async () => {
        let obj ={
            id:id,
            pw:pw,
        }
        console.log(obj);

    }

    return(
        <div>
            <div className={styles.loginContainer}>
                <div className={styles.logoContainer}>
                    <LogoUI/>
                </div>
                <div className={styles.inputContainer}>
                    <div className={styles.box}>
                        <InputBoxUI way="text" name="아이디" setName={setID}/>
                        <InputBoxUI way="password" name="비밀번호" setName={setPW} />
                    </div>
                </div>
                <div className={styles.buttonContainer}>
                    <BigButton text="로그인" onClick={() => setIsSendModalOpen(true)}/>
                    <ModalLayOut
                        isOpen={isSendModalOpen}
                        data={"로그인을 진행하시겠습니까?"}
                        onClose={() => setIsSendModalOpen(false)}
                        onClickEvent={confirmUserData}
                    />
                </div>
                <div className={styles.textContainer}>
                    <TextButton text="아이디 찾기"/>
                    <TextButton text="비밀번호 재설정"/>
                    <div className={styles.right}>
                        <TextButton text="회원가입" />

                    </div>
                </div>
                <div className={styles.snsContainer}>
                    <div className={styles.divide}>
                        -------------------- <span className={styles.snsText}>SNS 계정으로 로그인</span> --------------------
                    </div>
                    <div className={styles.snsBox}>
                        <a href="링크 주소"> <img src={talk} style={{width: '40px'}}/></a>
                        <a href="링크 주소"> <img src={google} style={{width: '40px'}}/></a>
                        <a href="링크 주소"> <img src={naver} style={{width: '40px'}}/></a>
                    </div>
                </div>


            </div>


        </div>
    )
}