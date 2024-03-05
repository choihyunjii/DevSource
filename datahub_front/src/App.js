import './App.css';

import TemporaryAppRouter from "./routes/TemporaryAppRouter";
import Header from "./Layout/Header/Header";
import TablePage from "./devSource/Components/page/TablePage";

function App() {
  return (
      <div>
          <Header/>
          <TemporaryAppRouter/>
          {/*<TablePage/>*/}
      </div>

  );
}

export default App;
