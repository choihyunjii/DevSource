import SideBarList from "./SideBarListLayOut";
import SideBarListLayOut from "./SideBarListLayOut";
import BottomContentUI from "./BottomContentUI";

export default function SideBarContentLayOut(){
    return(
        <div className="sidebar-content">
            <ul className="lists">
                <SideBarListLayOut title={"테이블 목록"}/>
                <SideBarListLayOut title={"즐겨찾기 목록"}/>
                <SideBarListLayOut title={"삭제 목록"}/>
            </ul>
            <div className="bottom-cotent">
                <BottomContentUI/>
            </div>
        </div>
    )
}