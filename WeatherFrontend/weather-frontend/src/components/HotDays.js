import React, { useState, useEffect } from 'react';
import axios from 'axios';

function HotDays() {
  const [hotDays, setHotDays] = useState([]);

  useEffect(() => {
    axios.get("/api/weather/get_hot")
      .then((response) => {
        setHotDays(response.data);
      })
      .catch((error) => {
        console.error("Error fetching hot days:", error);
      });
  }, []);

  return (
    <div>
      <h2>Hot Days (Temperature > 25°C)</h2>
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
          {hotDays.map((forecast, index) => (
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

export default HotDays;
