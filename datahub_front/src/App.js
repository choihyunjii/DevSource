import './App.css';
import Header from "./components/Layout/Header/Header";
import DataComponentCanvas from "./components/dataListView/DataComponentCanvas";
import SideButton from "./components/dataListView/sideBarCompoents/SideButton";
import SideBar from "./components/dataListView/sideBarCompoents/SideBar";
import ProjectApiFetch from "./components/project/ProjectApiFetch";

function App() {
  return (
      <div>
          {/*<ProjectApiFetch/>*/}
          <Header/>
          <SideBar/>
          <DataComponentCanvas/>
      </div>

  );
}

export default App;
