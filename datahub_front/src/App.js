import './App.css';
import Header from "./Layout/Header/Header";

import TemplateCodeEditor from "./template/components/page/TemplateCodeEditor";


function App() {
  return (
      <div>
          <Header/>
          {/*<CreateProjectPage/>*/}
          {/*<DataBaseShowCasePage/>*/}
          {/*<TemplatePage/>*/}
          <TemplateCodeEditor/>
      </div>

  );
}

export default App;
