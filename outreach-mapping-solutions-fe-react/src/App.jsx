import "./index.css";
import { NavBarMain } from "./navBarMain/navBarMain";
import ClientProfile from "./clientProfile";
import ClientPortal from "./clientPortal";
import { ReportPortal } from "./reportPortal";
import { MapPortal } from "./mapPortal";
import { Login } from "./login/login";
import { BrowserRouter, Route, Routes, Navigate } from "react-router-dom";
import ClientSearch from "./clientSearch";

export default function App() {
  return (
    <BrowserRouter>
      <div className="portal-main-container">
        <div className="nav-bar-main">
          <NavBarMain />
        </div>
        <div className="selected-portal">
          <Routes>
            <Route path="client-portal" element={<ClientPortal />}>
              <Route path="search" element={<ClientSearch />} />
              <Route path="profile" element={<ClientProfile />} />
              <Route path="" element={<Navigate to="search" />} />
              <Route path="*" element={<Navigate to="search" />} />
            </Route>
            <Route path="report-portal" element={<ReportPortal />} />
            <Route path="map-portal" element={<MapPortal />} />
            <Route path="login" element={<Login />} />
          </Routes>
        </div>
      </div>
    </BrowserRouter>
  );
}
