import './App.css';
import Header from "./components/Layout/Header/Header";
import DataComponentCanvas from "./components/dataListView/DataComponentCanvas";
import SideBar from "./components/dataListView/sideBarCompoents/SideBar";
import ListMenuComponent from "./components/dataListView/dataListCompeonts/listMenuComponent/ListMenu";
import ListMenuExampleData from "./components/dataListView/dataListCompeonts/listMenuComponent/ListMenuExampleData";
import exampleData from "./components/dataListView/dataListCompeonts/exampleData";

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
