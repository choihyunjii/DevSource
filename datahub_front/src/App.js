import './App.css';

import TemporaryAppRouter from "./routes/TemporaryAppRouter";
import Header from "./Layout/Header/Header";
import SideBarComponent from "./Layout/SideBar/SideBarComponent";
// import TablePage from "./devSource/Components/page/TablePage";


function App() {
  return (
      <div>
          <Header/>
          {/*<TemporaryAppRouter/>*/}
          <SideBarComponent/>


      </div>

  );
}

export default App;
