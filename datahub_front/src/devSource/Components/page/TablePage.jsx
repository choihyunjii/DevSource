import TableLayout from "../layout/TableLayout";
import TableTitleUI from "../ui/TableTitleUI";
import SideBarComponent from "../../../Layout/SideBar/SideBarComponent";

export default function TablePage() {
    return(
        <div>
            <SideBarComponent/>
            <div style={
                {
                    width : '60%' ,
                    margin : "3% auto",
                }
            }>
                <TableTitleUI title={"[ 프로젝트명 ]"} subTitle={"-[테이블명] Table"}/>
            </div>
            <TableLayout/>
        </div>
    )
}
