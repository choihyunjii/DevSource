import './SideBarStyle.css'
export default function SideButton({category}){
    return(
        <div className="button-container" >
            <button style={{display : "flex" , justifyContent : "space-between"}}>
                {category} <span>▼</span> {/* 이 부분을 기깔난 아이콘 으로 바꿔 주세요*/}
            </button>
        </div>
    )
}