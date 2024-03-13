import './App.css';

import TemporaryAppRouter from "./routes/TemporaryAppRouter";
import Header from "./Layout/Header/Header";

function App() {
  return (
      <div>
          <Header/>
          <TemporaryAppRouter/>
          {/*<Normalization/>*/}
      </div>

  );
}

export default App;
