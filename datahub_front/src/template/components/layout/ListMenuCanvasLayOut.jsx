import styles from '../../styleModule/canvas.module.css';
import ListMenu from "./ListMenuLayOut";
import ListMenuComponent from "./ListMenuLayOut";
import ListMenuExampleData from "../data/ListMenuExampleData";

export default function ListMenuCanvas (){
    return(
        <div className={styles.all}>
            <div className={styles.listGrid}>
                <ListMenuComponent title="menu" menus={ListMenuExampleData}/>
                <ListMenuComponent title="menu" menus={ListMenuExampleData}/>
                <ListMenuComponent title="menu" menus={ListMenuExampleData}/>
            </div>

        </div>

    )
}