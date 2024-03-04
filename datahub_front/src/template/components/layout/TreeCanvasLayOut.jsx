import styles from '../../styleModule/canvas.module.css';
import TreeComponent from "./TreeLayOut";
import TreeExampleData from "../data/TreeExampleData";

export default function TreeCanvasLayOut(){
    return (
        <div className={styles.all}>
            <div className={styles.treeGrid}>
                <TreeComponent title="DataBase" tables={TreeExampleData} />
            </div>
        </div>
    )
}