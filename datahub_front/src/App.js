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
import DuplicateIDLayout from "./Modal/DuplicateIDLayout";
import AvailableIDLayout from "./Modal/AvailableIDLayout";
import ModalLayout from "./SignUp/components/layout/ModalLayout";
import LoginLayout from "./Login/components/layout/LoginLayout";
import SendButton from "./Button/SendButton";
import TextButton from "./Button/TextButton";
import SearchIDLayout from "./Login/SearchID/SearchIDLayout";
import ResetPWLayout from "./Login/ResetPW/ResetPWLayout";
// import TablePage from "./devSource/Components/page/TablePage";


function App() {
  return (
      <div>
           {/* <Header/>*/}
          {/*<TemporaryAppRouter/>*/}
          {/*<TableBoxLayout/>*/}
          <SignUpLayout/>
        {/*  <DuplicateIDLayout/>*/}
          {/*<AvailableIDLayout/>*/}
         {/* <ModalLayout/>*/}
          {/*<LoginLayout/>*/}
         {/* <SearchIDLayout/>*/}
          {/*<ResetPWLayout/>*/}
      {/*    <AvailableIDLayout/>*/}
        {/*  <SendButton/>*/}
         {/*   <TextButton text="아이디 찾기"/>*/}
        {/*  <MemberBoxLayout/>*/}
        {/*  <Header/>*/}
      {/*    <SideBarComponent/>*/}
         {/* <TablePage/>*/}
      </div>

  );
}

export default App;
