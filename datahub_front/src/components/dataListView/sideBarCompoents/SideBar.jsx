import sideBarCategories from "./sideBarCategories";
import SideButton from "./SideButton";

export default function SideBar(){
    return(
        <div className="sidebar">
            {sideBarCategories.map(category =>(
                <SideButton
                    category={category.category}
                />
            ))}
        </div>
    )
}