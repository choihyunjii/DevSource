import TitleUI from "../uI/TitleUI";
import CreateProjectFormLayout from "../layout/CreateProjectFormLayout";
import styles from "../styles.module.css";
import Header from "../../../Layout/Header/Header";
import 'bootstrap/dist/css/bootstrap.min.css';


export default function CreateProjectPage(){
    return(
        <div>

            <Header/>
            <div className={styles.middleBox}>
                <TitleUI title={"새로운 프로젝트 생성"}/>
                <CreateProjectFormLayout/>
            </div>
        </div>

    )
}