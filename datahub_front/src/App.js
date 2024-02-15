import './App.css';
import Header from "./components/Layout/Header/Header";
import DrawApp from "./components/Front/DrawApp";
import DraggableElement from "./components/Front/DraggableElement";

function App() {
  return (
    <div className="App">
        <Header/>
        <DrawApp/>
        <DraggableElement/>
    </div>
  );
}

export default App;
