import { useState } from "react";
import "./index.css";

const dataQualitySelections = [
  "Complete",
  "Partial",
  "Client Did Not Know",
  "Client Refused",
  "Data Not Collected",
];

const months = [
  "January",
  "February",
  "March",
  "April",
  "May",
  "June",
  "July",
  "August",
  "September",
  "October",
  "November",
  "December",
];

// prettier-ignore
const days29=['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24', '25', '26', '27', '28', '29'];
// prettier-ignore
const days30=['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24', '25', '26', '27', '28', '29', '30'];
// prettier-ignore
const days31=['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24', '25', '26', '27', '28', '29', '30', '31'];

//prettier-ignore
const months31Days=["January", "March", "May", "July", "August", "October", "December"];
const months30Days = ["April", "June", "September", "November"];

export function ClientBaseForm() {
  const [monthsDays, setMonthsDays] = useState([]);

  return (
    <form className="form-client-search">
      <div className="form-group">
        <label htmlFor="firstName">
          First Name
          <input
            type="text"
            id="firstName"
            name="firstName"
            // value={formData.firstName}
            // onChange={handleChange}
          />
        </label>
        <label htmlFor="middleName">
          Middle Name
          <input
            type="text"
            id="middleName"
            name="middleName"
            // value={formData.firstName}
            // onChange={handleChange}
          />
        </label>
        <label htmlFor="lastName">
          Last Name
          <input
            type="text"
            id="lastName"
            name="lastName"
            // value={formData.firstName}
            // onChange={handleChange}
          />
        </label>
      </div>

      <div className="data-quality-selection">
        <label htmlFor="nameDataQuality">
          Name Data Quality
          <select
            id="nameDataQuality"
            name="nameDataQuality"
            // value={selectedOption}
            // onchange={handleChange}
          >
            {dataQualitySelections.map((selection, index) => (
              <option key={index} value={selection}>
                {selection}
              </option>
            ))}
          </select>
        </label>
      </div>

      <div className="form-group-dob">
        <label htmlFor="dobMonth">Date of Birth</label>
        <div className="dob-input-fields">
          <select
            id="dobInput"
            name="dobMonth"
            // onChange={handleMonthSelection}
            // value={selectedOption}
          >
            <option>Month</option>

            {months.map((month, index) => (
              <option key={index} value={month}>
                {month}
              </option>
            ))}
          </select>

          <select
            id="dobInput"
            name="dobDay"
            // value={selectedOption}
            // onChange={handleChange}
          >
            <option>Day</option>
            {monthsDays.map((day, index) => (
              <option key={index} value={day}>
                {day}
              </option>
            ))}
          </select>

          <input
            type="text"
            id="dobInput"
            name="dobYear"
            maxLength="4"
            placeholder="YYYY"
            // value={formData.firstName}
            // onChange={handleChange}/
          />
        </div>
      </div>

      <div className="data-quality-selection">
        <label htmlFor="nameDataQuality">
          Date of Birth Data Quality
          <select
            id="nameDataQuality"
            name="nameDataQuality"
            // value={selectedOption}
            // onchange={handleChange}
          >
            {dataQualitySelections.map((option, index) => (
              <option key={index} value={option}>
                {option}
              </option>
            ))}
          </select>
        </label>
      </div>

      <div className="form-group-ssn">
        <label htmlFor="ssnInput">Social Security Number </label>
        <div className="ssn-input-fields">
          <input
            type="text"
            id="ssnInput"
            name="firstThreeSsn"
            maxLength="3"

            // value={formData.firstName}
            // onChange={handleChange}
          />
          <span>-</span>
          <input
            type="text"
            id="ssnInput"
            name="middleTwoSsn"
            maxLength="2"
            // value={formData.firstName}
            // onChange={handleChange}
          />
          <span>-</span>
          <input
            type="text"
            id="ssnInput"
            name="lastFourSsn"
            maxLength="4"
            // value={formData.firstName}
            // onChange={handleChange}
          />
        </div>
      </div>

      <div className="data-quality-selection">
        <label htmlFor="nameDataQuality">
          Social Security Number Data Quality
          <select
            id="nameDataQuality"
            name="nameDataQuality"
            // value={selectedOption}
            // onchange={handleChange}
          >
            {dataQualitySelections.map((option, index) => (
              <option key={index} value={option}>
                {option}
              </option>
            ))}
          </select>
        </label>
      </div>
    </form>
  );
}
