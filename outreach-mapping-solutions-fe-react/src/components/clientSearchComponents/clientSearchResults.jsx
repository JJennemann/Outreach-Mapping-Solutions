import { Link } from "react-router-dom";

export default function ClientSearchResults({ searchResults }) {
  const searchResultsRows = Object.keys(searchResults).map((resultId) => {
    const returnedClient = searchResults[resultId];
    const { id, displayName, displayDob, displaySsn } = returnedClient;

    return (
      <tr key={resultId}>
        <td>{id}</td>
        <td>
          <Link
            to={`/client-portal/client-profile/?clientId=${returnedClient.id}`}
          >
            {displayName}
          </Link>
        </td>
        <td>{displayDob}</td>
        <td>{displaySsn}</td>
      </tr>
    );
  });

  return (
    <table>
      <thead>
        <tr>
          <th>ID</th>
          <th>Name</th>
          <th>Date of Birth</th>
          <th>Social Security Number</th>
        </tr>
      </thead>
      <tbody>{searchResultsRows}</tbody>
    </table>
  );
}
