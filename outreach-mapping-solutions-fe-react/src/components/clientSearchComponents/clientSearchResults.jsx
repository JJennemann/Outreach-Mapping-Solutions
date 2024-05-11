import { Link } from "react-router-dom";

export default function ClientSearchResults({ searchResults }) {
  const searchResultsRows = Object.keys(searchResults).map((resultId) => {
    const returnedClient = searchResults[resultId];
    return (
      <tr key={resultId}>
        <td>{returnedClient.id}</td>
        <td>
          <Link
            to={`/client-portal/client-profile/?clientId=${returnedClient.id}`}
          >
            {returnedClient.lastName +
              ", " +
              returnedClient.firstName +
              " " +
              returnedClient.middleName}
          </Link>
        </td>
        <td>
          {returnedClient.dobMonth +
            " " +
            returnedClient.dobDay +
            ", " +
            returnedClient.dobYear}
        </td>
        <td>
          {returnedClient.firstThreeSsn +
            "-" +
            returnedClient.middleTwoSsn +
            "-" +
            returnedClient.lastFourSsn}
        </td>
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
