import './App.css';
import Header from "./components/Layout/Header/Header";
import ProjectShowCasePage from "./project/components/page/ProjectShowCasePage";

function App() {
  return (
      <div>
          <Header/>
          {/*<CreateProjectPage/> 프로젝트 선택 페이지 */}
          <ProjectShowCasePage/>
      </div>

  );
}

export default App;
