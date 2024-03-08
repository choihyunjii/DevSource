import TableLayout from "../layout/TableLayout";
import DataBaseSideBarLayOut from "../../../project/components/layout/DataBaseSideBarLayOut";
import TableTitleUI from "../ui/TableTitleUI";

export default function TablePage() {
    return(
        <div>
            <DataBaseSideBarLayOut/>
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
