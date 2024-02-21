export default function projectCard({projectList}){

    return(
        <div className='card-grid'>
            {projectList.map(data =>(
                <p>{data.id}</p>
            ))}
        </div>
    )
}