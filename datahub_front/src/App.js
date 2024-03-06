import './App.css';

import Header from "./Layout/Header/Header";
import Normalization from "./normalization/page/Normalization";
import TemporaryAppRouter from "./routes/TemporaryAppRouter";

function App() {
  return (
      <div>
          <Header/>
          <TemporaryAppRouter/>
      </div>

  );
}

export default App;
