import { useState } from "react";
import "./index.css";
import { NavBarMain } from "./navBarMain";
import { ClientPortal } from "./clientPortal";
import { ReportPortal } from "./reportPortal";
import { MapPortal } from "./mapPortal";
import { Login } from "./login";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

export default function App() {
  return (
    <div className="portal-main-container">
      <NavBarMain />
      <div className="selected-portal">
        <Routes>
          <Route path="/" exact element={<ClientPortal />} />
          <Route path="/client-portal" element={<ClientPortal />} />
          <Route path="/report-portal" element={<ReportPortal />} />
          <Route path="/map-portal" element={<MapPortal />} />
          <Route path="/login-portal" element={<Login />} />
        </Routes>
      </div>
    </div>
  );
}
