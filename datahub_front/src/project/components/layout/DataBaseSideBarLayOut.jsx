import SideBarHeaderUI from "../uI/SideBarHeaderUI";
import SideBarContentUI from "../uI/SideBarContentUI";
import styles from "../styles.module.css";

export default function DataBaseSideBarLayOut(){
    return(
        <div>
            <div className={styles.dataBaseSideBar}>
                <SideBarHeaderUI headerTitle={"DataBase"}/>
                <SideBarContentUI/>
                <SideBarHeaderUI headerTitle={"Front Client"}/>

            </div>
        </div>
    )
}