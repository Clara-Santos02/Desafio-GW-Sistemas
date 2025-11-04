import React, { useState } from "react";
import OrderForm from "./components/OrderForm";
import OcurrenceForm from "./components/OcurrenceForm";
import TrackingView from "./components/TrackingView";
import "./App.css";

function App() {
  const [screen, setScreen] = useState("orders");

  return (
    <div className="container">
      <h1>Sistema de rastreio de encomendas</h1>
      <div className="menu">
        <button onClick={() => setScreen("orders")}>Cadastrar Encomenda</button>
        <button onClick={() => setScreen("ocurrences")}>Registrar Ocorrência</button>
        <button onClick={() => setScreen("tracking")}>Consultar Status</button>
      </div>

      {screen === "orders" && <OrderForm />}
      {screen === "ocurrences" && <OcurrenceForm />}
      {screen === "tracking" && <TrackingView />}
    </div>
  );
}

export default App;

