import './App.css';

import TemporaryAppRouter from "./routes/TemporaryAppRouter";
import Header from "./Layout/Header/Header";
import TablePage from "./devSource/Components/page/TablePage";
import Normalization from "./normalization/page/Normalization";

function App() {
  return (
      <div>
          <Header/>
          {/*<TemporaryAppRouter/>*/}
          <Normalization/>
      </div>

  );
}

export default App;
