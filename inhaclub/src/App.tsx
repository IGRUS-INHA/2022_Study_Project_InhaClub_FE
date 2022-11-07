import React from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import Join from './join';
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