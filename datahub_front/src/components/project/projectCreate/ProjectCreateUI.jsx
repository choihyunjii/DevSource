import TitleComponent from "./TitleComponent";
import styles from './styles.module.css';
import InputForm from "./InputForm";
export default function ProjectCreateUI(){
    return(
        <div>
            <div className={styles.titleComponent}>
                <TitleComponent title="새로운 프로젝트 생성"/>
                <InputForm/>
            </div>
        </div>
    )
}