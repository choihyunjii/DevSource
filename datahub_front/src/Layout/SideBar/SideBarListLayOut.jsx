export default function SideBarListLayOut({title}){
    return(
        <li className="list">
            <a href="#" className="nav-link">
                <h5 className="link">{title}</h5>
                <h4> ▼ </h4>
            </a>
            <ul className="menu">
                <li>목록 1</li>
                <li>목록 2</li>
                <li>목록 3</li>
            </ul>
        </li>
    )
}