import './App.css';
import Header from "./components/Layout/Header/Header";
import DataComponentCanvas from "./components/dataListView/DataComponentCanvas";
import SideBar from "./components/dataListView/sideBarCompoent/SideBar";
import exampleData from "./components/dataListView/dataListCompeonts/cardListComponent/exampleData";
import TitleComponent from "./components/project/projectCreate/TitleComponent";
import ProjectCreateUI from "./components/project/projectCreate/ProjectCreateUI";

function App() {
  return (
      <div>
          {/*<ProjectApiFetch/>*/}
          <Header/>
          {/*<SideBar/>*/}
          {/*<DataComponentCanvas/>*/}
          {/*<ListMenuComponent title="menu" menus={ListMenuExampleData}/>*/}
          {/*<DataComponentCanvas exampleData={exampleData}/>*/}
          <ProjectCreateUI/>
      </div>

  );
}

export default App;
