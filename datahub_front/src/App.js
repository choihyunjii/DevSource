import './App.css';
import Header from "./components/Layout/Header/Header";
import CreateProjectPage from "./project/components/page/CreateProjectPage";
import DataBaseShowCasePage from "./project/components/page/DataBaseShowCasePage";


function App() {
  return (
      <div>
          <Header/>
          {/*<CreateProjectPage/>*/}
          <DataBaseShowCasePage/>
      </div>

  );
}

export default App;
