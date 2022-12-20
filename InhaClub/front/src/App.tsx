import React, { useState, useEffect } from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import 'bootstrap/dist/css/bootstrap.min.css';
import Join from './join';
import './join.scss';
function App() {
  const [message, setMessage] = useState([]);
  useEffect(() => {
    fetch("/join")
      .then((res) => {
        return res.json();
      })
      .then((data) => {
        setMessage(data);
      });
  }, []);
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path="/join" element={<Join/>} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;