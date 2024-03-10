import './App.css';

import TemporaryAppRouter from "./routes/TemporaryAppRouter";
import Header from "./Layout/Header/Header";
// import TablePage from "./devSource/Components/page/TablePage";
import Normalization from "./normalization/page/Normalization";
import MemberBoxLayout from "./project/components/layout/MemberBoxLayout";

function App() {
  return (
      <div>
          <Header/>
          {/*<TemporaryAppRouter/>*/}
          <MemberBoxLayout/>
          {/*<Normalization/>*/}
      </div>

  );
}

export default App;
