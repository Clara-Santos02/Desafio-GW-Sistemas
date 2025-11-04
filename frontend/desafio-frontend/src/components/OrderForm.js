import React, { useState } from "react";

function OrderForm() {
  const [form, setForm] = useState({ trackingCode: "", clientName: "", address: "" });
  const [message, setMessage] = useState("");

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const res = await fetch("http://localhost:8080/orders", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(form),
    });

    if (res.ok) setMessage("Encomenda cadastrada com sucesso!");
    else setMessage("Erro ao cadastrar encomenda.");
  };

  return (
    <form onSubmit={handleSubmit}>
      <label>Código de Rastreamento:</label>
      <input name="trackingCode" value={form.trackingCode} onChange={handleChange} required />

      <label>Nome do Cliente:</label>
      <input name="clientName" value={form.clientName} onChange={handleChange} required />

      <label>Endereço:</label>
      <input name="address" value={form.address} onChange={handleChange} required />

      <button type="submit">Cadastrar</button>
      {message && <p>{message}</p>}
    </form>
  );
}

export default OrderForm;
