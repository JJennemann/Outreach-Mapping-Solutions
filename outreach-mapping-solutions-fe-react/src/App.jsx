import { BrowserRouter, Route, Routes, Navigate } from "react-router-dom";

import "./index.css";
import NavBarMain from "./components/navBarMain";
import ReportPortal from "./portals/reportPortal";
import MapPortal from "./portals/mapPortal";
import Login from "./portals/login";
import ClientProfile from "./pages/clientProfile";
import ClientPortal from "./portals/clientPortal";
import ClientSearch from "./pages/clientSearch";

export default function App() {
  return (
    <BrowserRouter>
      <div className="portal-main-container">
        <div className="nav-bar-main">
          <NavBarMain />
        </div>
        <div className="selected-portal">
          <Routes>
            <Route
              index
              element={<Navigate replace to="client-portal/search" />}
            />
            <Route path="client-portal" element={<ClientPortal />}>
              <Route index element={<Navigate replace to="search" />} />
              <Route path="search" element={<ClientSearch />} />
              <Route path="client-profile" element={<ClientProfile />} />

              <Route path="*" element={<Navigate replace to="search" />} />
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
