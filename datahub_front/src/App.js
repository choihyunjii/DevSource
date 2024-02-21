import './App.css';
import Header from "./components/Layout/Header/Header";
import CreateProjectPage from "./project/components/page/CreateProjectPage";

function App() {
  return (
      <div>
          {/*<ProjectApiFetch/>*/}
          <Header/>
          {/*<SideBar/>*/}
          {/*<DataComponentCanvas/>*/}
          {/*<ListMenuComponent title="menu" menus={ListMenuExampleData}/>*/}
          {/*<DataComponentCanvas exampleData={exampleData}/>*/}
          <CreateProjectPage/>
      </div>

  );
}

export default App;
