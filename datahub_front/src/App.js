import './App.css';

import TemporaryAppRouter from "./routes/TemporaryAppRouter";
import Header from "./Layout/Header/Header";

import SignUpLayout from "./SignUp/components/layout/SignUpLayout";
import ProjectSideBarLayOut from "./project/components/layout/ProjectSideBarLayOut";
import SideBarContentLayOut from "./Layout/SideBar/SideBarContentLayOut";
import SideBarListLayOut from "./Layout/SideBar/SideBarListLayOut";
import LoginLayout from "./Login/components/layout/LoginLayout";

function App() {
  return (
      <div>
         {/* <Header/>*/}
        {/*  <TemporaryAppRouter/>*/}
      {/*    <SignUpLayout/>
          <ProjectSideBarLayOut/>*/}
       {/*   <SideBarContentLayOut/>
          <Normalization/>*/}
        {/* <SideBarContentLayOut/>*/}
          <LoginLayout/>
      </div>

  );
}

export default App;
