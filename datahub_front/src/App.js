import './App.css';
import Header from "./components/Layout/Header/Header";
import DataComponentCanvas from "./components/dataListView/DataComponentCanvas";
import SideBar from "./components/dataListView/sideBarCompoent/SideBar";
import exampleData from "./components/dataListView/dataListCompeonts/cardListComponent/exampleData";

function App() {
  return (
      <div>
          {/*<ProjectApiFetch/>*/}
          <Header/>
          <SideBar/>
          {/*<DataComponentCanvas/>*/}
          {/*<ListMenuComponent title="menu" menus={ListMenuExampleData}/>*/}
          <DataComponentCanvas exampleData={exampleData}/>
      </div>

  );
}

export default App;
