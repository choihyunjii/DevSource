import CardDesign from "../uI/CardDesignUI";
import styles from '../../styleModule/canvas.module.css';

export default function CardCanvasLayOut({exampleData}){

    return(
        <div className={styles.cardGrid}>
            {exampleData.map(data =>(
                <CardDesign
                    key = {data.id}
                    title={data.title}
                    image={data.image}
                    description={data.description}
                />
            ))}
        </div>
    )
}