import { useState } from "react";
import { ClientBaseForm } from "./clientBaseForm/clientBaseForm";
import { ClientSearchResults } from "./clientSearchResults/clientSearchResults";
import axios from "axios";

export function ClientPortal() {
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
    </div>
  );
}
