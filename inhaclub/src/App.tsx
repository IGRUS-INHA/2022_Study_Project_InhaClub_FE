import React from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import 'bootstrap/dist/css/bootstrap.min.css';
import Join from './join';
import './join.scss';
function App() {
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