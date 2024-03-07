import styles from "../styles.module.css";
import ProjectViewCardUI from "../uI/ProjectViewCardUI";
import {Image} from "react-bootstrap";

export default function ProjectInformationLayOut() {
    return(
        <div>
            <div className={styles.InformationInput} style={{
                    position: `absolute`,
                    zIndex: 1
                }}
            >
                <label className={styles.InformationCardMaker}>만든 사람<input className={styles.InputMaker} placeholder={"[만든 사람 이름]"} type="text"/></label> <br/>
                <label className={styles.InformationCardEx}>설명<input className={styles.InputEx} placeholder={"[설명]"} type="text"/></label><br/>
                <label className={styles.InformationCardDate}>생성 날짜<input className={styles.InputMaker} placeholder={"[2024-02-24]"} type="text"/></label><br/>

                <div className={styles.InformationAction}>
                    <div className={styles.ActionNewPull}><span className={styles.ActionTxt}>New pull<br/>↓ 0</span></div>
                    <div className={styles.ActionNewPush}><span className={styles.ActionTxt}>New push<br/>↑ 0</span></div>
                    <div className={styles.ActionCrash}><span className={styles.ActionTxt}>crash<br/><Image src="../image/crash.png" className={styles.CrashIcon}/>0</span></div>
                </div>

            </div>

            <ProjectViewCardUI cardtitle={"Project Information"} iconImage={<Image src="../image/Information.png"  className={styles.InformationIcon}/>} buttontitle={"DevTree 접속하기"} style={{ position: 'relative' }}/>

        </div>


    )
}