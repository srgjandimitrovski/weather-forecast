import React from 'react';
import HotDays from './components/HotDays';
import RainyDays from './components/RainyDays';
import './App.css';

function App() {
  return (
    <div className="App">
      <h1>Weather Forecast</h1>

      <HotDays />
      <RainyDays />
    </div>
  );
}

export default App;
