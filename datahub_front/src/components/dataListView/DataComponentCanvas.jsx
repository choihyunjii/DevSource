import exampleData from "./dataListCompeonts/exampleData";
import CardDesign from "./dataListCompeonts/CardDesign";
import './DataComponentCanvas.css';
export default function DataComponentCanvas(){

    return(
        <div className='card-grid'>
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