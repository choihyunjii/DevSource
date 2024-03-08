import styles from "../styles.module.css";
import ProjectViewCardUI from "../uI/ProjectViewCardUI";
import {Image} from "react-bootstrap";
import TablesUI from "../uI/TablesUI";

export default function ProjectTablesLayOut(){
    return(
        <div>
            <div className={styles.Tables} style={{
                position: `absolute`,
                zIndex: 1
            }
            } >
                <TablesUI />

            </div>
            <ProjectViewCardUI cardtitle={"Tables"} iconImage={<Image src="../image/Tables.png"  className={styles.TablesIcon}/>} buttontitle={"DevTool 접속하기"} style={{position: 'relative'}}/>
        </div>
    )
}