import './App.css';

import TemporaryAppRouter from "./routes/TemporaryAppRouter";
import Header from "./Layout/Header/Header";
import SideBarComponent from "./Layout/SideBar/SideBarComponent";
import MemberBoxLayout from "./project/components/layout/MemberBoxLayout";
import TableBoxLayout from "./project/components/layout/TableBoxLayout";
import TablePage from "./devSource/Components/page/TablePage";
import DataTypeUI from "./project/components/uI/DataTypeUI";
import TableSearchLayout from "./project/components/layout/TableSearchLayout";
import SignUpLayout from "./SignUp/components/layout/SignUpLayout";
// import TablePage from "./devSource/Components/page/TablePage";


function App() {
  return (
      <div>
           {/* <Header/>*/}
          {/*<TemporaryAppRouter/>*/}
          {/*<TableBoxLayout/>*/}
          <SignUpLayout/>

        {/*  <MemberBoxLayout/>*/}
        {/*  <Header/>*/}
      {/*    <SideBarComponent/>*/}
         {/* <TablePage/>*/}
      </div>

  );
}

export default App;
