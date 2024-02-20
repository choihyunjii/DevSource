import exampleData from "../dataListView/dataListCompeonts/cardListComponent/exampleData";
import CardDesign from "../dataListView/dataListCompeonts/cardListComponent/CardDesign";

export default function projectCard({projectList}){

    return(
        <div className='card-grid'>
            {projectList.map(data =>(
                <p>{data.id}</p>
            ))}
        </div>
    )
}