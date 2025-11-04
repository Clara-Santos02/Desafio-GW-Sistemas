import React, { useState } from "react";

function TrackingView() {
  const [trackingCode, setTrackingCode] = useState("");
  const [data, setData] = useState(null);
  const [error, setError] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();
    const res = await fetch(`http://localhost:8080/ocurrences/tracking?trackingCode=${trackingCode}`);
    if (res.ok) {
      const json = await res.json();
      setData(json);
      setError("");
    } else {
      setError("Código não encontrado.");
      setData(null);
    }
  };

  return (
    <div>
      <form onSubmit={handleSubmit}>
        <label>Código de Rastreamento:</label>
        <input value={trackingCode} onChange={(e) => setTrackingCode(e.target.value)} required />
        <button type="submit">Consultar</button>
      </form>

      {error && <p>{error}</p>}
      {data && (
        <div>
          <h3>Cliente: {data.clientName}</h3>
          <p>Endereço: {data.address}</p>
          <h4>Status Atual: {data.currentStatus}</h4>

          <h4>Timeline:</h4>
          {data.timeline.map((t, i) => (
            <div key={i} className="status-line">
              <strong>{t.status}</strong> - {new Date(t.ocurrenceTime).toLocaleString()}
            </div>
          ))}
        </div>
      )}
    </div>
  );
}

export default TrackingView;
