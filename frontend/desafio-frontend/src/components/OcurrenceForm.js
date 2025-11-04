import React, { useState } from "react";

function OcurrenceForm() {
  const [form, setForm] = useState({ trackingCode: "", status: "OUT_FOR_DELIVERY" });
  const [message, setMessage] = useState("");

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const res = await fetch("http://localhost:8080/ocurrences", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(form),
    });

    if (res.ok) setMessage("Ocorrência registrada com sucesso!");
    else setMessage("Erro ao registrar ocorrência.");
  };

  return (
    <form onSubmit={handleSubmit}>
      <label>Código de Rastreamento:</label>
      <input name="trackingCode" value={form.trackingCode} onChange={handleChange} required />

      <label>Status:</label>
      <select name="status" value={form.status} onChange={handleChange}>
        <option value="Saiu para entrega">Out for delivery</option>
        <option value="Em Trânsito">In transit</option>
        <option value="Não Entregue">Not delivered</option>
        <option value="Entregue">Delivered</option>
      </select>

      <button type="submit">Registrar</button>
      {message && <p>{message}</p>}
    </form>
  );
}

export default OcurrenceForm;
