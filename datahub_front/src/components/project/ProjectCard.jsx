import exampleData from "../dataListView/dataListCompeonts/exampleData";
import CardDesign from "../dataListView/dataListCompeonts/CardDesign";

export default function projectCard({projectList}){

    return(
        <div className='card-grid'>
            {projectList.map(data =>(
                <p>{data.id}</p>
            ))}
        </div>
    )
}