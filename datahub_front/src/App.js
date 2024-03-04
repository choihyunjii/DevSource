import './App.css';
import Header from "./Layout/Header/Header";
import TemplateFactory from "bootstrap/js/src/util/template-factory";
import TemplatePage from "./template/components/page/TemplatePage";


function App() {
  return (
      <div>
          <Header/>
          {/*<CreateProjectPage/>*/}
          {/*<DataBaseShowCasePage/>*/}
          <TemplatePage/>
      </div>

  );
}

export default App;
