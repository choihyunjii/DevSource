import styles from '../loginStyle.module.css';
import LogoUI from "../../../Logo/LogoUI";
import InputBoxUI from "../../../Input/InputBoxUI";
import BigButton from "../../../Button/BigButton";
import TextButton from "../../../Button/TextButton";
import talk from '../image/kakao.png';
import google from '../image/google.png';
import naver from '../image/naver.png';




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
                <div className={styles.textContainer}>
                    <TextButton text="아이디 찾기"/>
                    <TextButton text="비밀번호 재설정"/>
                    <div className={styles.right}>
                        <TextButton text="회원가입"/>
                    </div>
                </div>
                <div className={styles.snsContainer}>
                    <div className={styles.divide}>
                        -------------------- <span className={styles.snsText}>SNS 계정으로 로그인</span> --------------------
                    </div>

                    <div className={styles.snsBox}>
                        <img src={talk} style={{width: '40px'}}/>
                        <img src={google} style={{width:'40px'}}/>
                        <img src={naver} style={{width: '40px'}}/>
                    </div>
                </div>


            </div>


        </div>
    )
}