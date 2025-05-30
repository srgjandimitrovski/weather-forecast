import React, { useState, useEffect } from 'react';
import axios from 'axios';

function RainyDays() {
  const [rainyDays, setRainyDays] = useState([]);

  useEffect(() => {
    axios.get("/api/weather/get_rainy")
      .then((response) => {
        setRainyDays(response.data);
      })
      .catch((error) => {
        console.error("Error fetching rainy days:", error);
      });
  }, []);

  return (
    <div>
      <h2>Rainy Days</h2>
      <table>
        <thead>
          <tr>
            <th>City</th>
            <th>Date</th>
            <th>Max Temperature (°C)</th>
            <th>Weather</th>
          </tr>
        </thead>
        <tbody>
          {rainyDays.map((forecast, index) => (
            <tr key={index}>
              <td>{forecast.city}</td>
              <td>{forecast.date}</td>
              <td>{forecast.maxTemp}°C</td>
              <td>{forecast.main}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default RainyDays;
