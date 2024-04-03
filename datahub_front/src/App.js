import './App.css';

import TemporaryAppRouter from "./routes/TemporaryAppRouter";
import Header from "./Layout/Header/Header";

import SignUpLayout from "./SignUp/components/layout/SignUpLayout";

function App() {
  return (
      <div>
         {/* <Header/>*/}
         {/* <TemporaryAppRouter/>*/}
          <SignUpLayout/>
          {/*<Normalization/>*/}
      </div>

  );
}

export default App;
