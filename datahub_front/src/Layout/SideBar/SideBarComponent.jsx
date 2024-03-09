import './style.css'
import LogoUI from "./LogoUI";
import SideBarContentLayOut from "./SideBarContentLayOut";

export default function SideBarComponent(){
    return(
        <div>
            <nav>
                <div className="sidebar">
                    <LogoUI/>
                    <SideBarContentLayOut/>
                </div>
            </nav>
        </div>
    )
}