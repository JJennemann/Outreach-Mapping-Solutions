import { Outlet } from "react-router-dom";
import { useState } from "react";
import { ClientBaseForm } from "../components/clientSearchComponents/clientBaseForm";
import ClientSearchResults from "../components/clientSearchComponents/clientSearchResults";
import axios from "axios";

export default function ClientSearch() {
  const [showSearchResults, setShowSearchResults] = useState(false);
  const [searchResults, setSearchResults] = useState({});

  const handleClientSearchResults = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.get(
        "http://localhost:8080/clientBase/returnAll",
        {
          headers: {
            "Content-Type": "application/json",
          },
        }
      );
      console.log("Success:", response.data);
      setSearchResults(response.data);
      setShowSearchResults(true);
    } catch (error) {
      console.log("Error:", error);
    }
  };

  return (
    <div className="client-portal-container">
      <ClientBaseForm onClientSearch={handleClientSearchResults} />
      {showSearchResults ? (
        <ClientSearchResults searchResults={searchResults} />
      ) : (
        ""
      )}

      <Outlet />
    </div>
  );
}
