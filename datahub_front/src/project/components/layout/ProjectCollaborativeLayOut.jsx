import styles from "../styles.module.css";
import ProjectViewCardUI from "../uI/ProjectViewCardUI";
import {Image} from "react-bootstrap";
import CollaborativeUsers from "../uI/CollaborativeUsersUI"
import CollborativeUsers from "../uI/CollaborativeUsersUI";

export default function ProjectCollaborativeLayOut (){
    return(
        <div>
            <div className={styles.CollaborativeUsers} style={{position: 'absolute'}}>
                <CollborativeUsers/>

            </div>
            <ProjectViewCardUI cardtitle={"Collaborative"} iconImage={<Image src="../image/Collaborative.png"  className={styles.CollaborativeIcon}/>} buttontitle={"협업관리 하러가기"} style={{position: 'relative'}}/>

        </div>
    )
}