import Header from "./layout/Header";
import Main from "./layout/Main";
import { BrowserRouter } from "react-router-dom";
import "./App.scss";

export default function App() {
  return (
    <BrowserRouter>
      <div className="app">
        <Header />
        <Main />
      </div>
    </BrowserRouter>
  );
}
