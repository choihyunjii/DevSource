import styles from "../styles.module.css";
import ProjectViewCardUI from "../uI/ProjectViewCardUI";
import {Image} from "react-bootstrap";
import CollborativeUsers from "../uI/CollaborativeUsersUI";
import {Link} from "react-router-dom";

export default function ProjectCollaborativeLayOut ({project}){
    return(
        <div>
            <div className={styles.CollaborativeUsers} style={{
                    position: `absolute`,
                    zIndex: 1
                }
            }>
                <CollborativeUsers teamProfile={project.teamProfile}/>

            </div>
            <Link to={"/project/teamProfile"}>
                <ProjectViewCardUI cardtitle={"Collaborative"} iconImage={<Image src="../image/Collaborative.png"  className={styles.CollaborativeIcon}/>} buttontitle={"협업관리 하러가기"} />
            </Link>

        </div>
    )
}