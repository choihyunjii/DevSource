import sideBarCategories from "./sideBarCategories";
import SideButton from "./SideButton";

export default function SideBar(){
    return(
        <div className="sidebar">
            {sideBarCategories.map(category =>(
                <SideButton
                    key = {category.id}
                    category={category.category}
                />
            ))}
        </div>
    )
}