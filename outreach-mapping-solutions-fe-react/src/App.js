import { useState } from "react";
import "./index.css";
import { ClientBaseForm } from "./clientBaseForm";

export default function App() {
  return (
    <>
      <NavBar />
      <ClientBaseForm />
      <ClientSearchResults />4
    </>
  );
}

function NavBar() {
  return <nav>NavBar</nav>;
}

function ClientSearchResults() {
  return <p>Search Results</p>;
}
