import React from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import Join from './join';
import Clubs from './clubs';
import './join.scss';
import './clubs.scss';
function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path="/join" element={<Join/>} />
        </Routes>
        <Routes>
          <Route path="/clubs" element={<Clubs/>} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;