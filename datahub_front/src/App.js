import './App.css';

import TemporaryAppRouter from "./routes/TemporaryAppRouter";
import Header from "./Layout/Header/Header";
// import TablePage from "./devSource/Components/page/TablePage";
import Normalization from "./normalization/page/Normalization";
import MemberBoxLayout from "./project/components/layout/MemberBoxLayout";
import SideBarLayOut from "./template/components/layout/SideBarLayOut";
import SideBarContentLayOut from "./Layout/SideBar/SideBarContentLayOut";
import SideBarComponent from "./Layout/SideBar/SideBarComponent";

function App() {
  return (
      <div>
          <Header/>
          {/*<TemporaryAppRouter/>*/}
          {/*<MemberBoxLayout/>*/}
        {/*  <SideBarContentLayOut/>*/}
          <SideBarComponent/>
          {/*<Normalization/>*/}
      </div>

  );
}

export default App;
