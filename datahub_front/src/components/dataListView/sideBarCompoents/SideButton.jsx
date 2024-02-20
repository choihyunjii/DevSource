import './SideBarStyle.css'
export default function SideButton({category}){
    return(
        <div className="button-container" >
            <button style={{display : "flex" , justifyContent : "space-between"}}>
                {category} <span>▼</span>
            </button>
        </div>
    )
}