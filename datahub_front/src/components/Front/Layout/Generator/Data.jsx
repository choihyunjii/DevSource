export default function Data({image,title}){
    return (
        <li>
            <p>{title}</p>
            <img src={image}/>
        </li>
    )
}
