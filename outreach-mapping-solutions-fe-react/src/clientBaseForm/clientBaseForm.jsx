import { useEffect, useState } from "react";
import "./clientBaseForm.css";
import { initialClientBaseFormData } from "./clientBaseFormData.jsx";
import axios from "axios";

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

const days29 = Array.from({ length: 29 }, (_, i) => i + 1);
const days30 = Array.from({ length: 30 }, (_, i) => i + 1);
const days31 = Array.from({ length: 31 }, (_, i) => i + 1);

//prettier-ignore
const months31Days=["January", "March", "May", "July", "August", "October", "December"];
const months30Days = ["April", "June", "September", "November"];

export function ClientBaseForm({ onClientSearch }) {
  const [month, setMonth] = useState("");
  const [monthsDays, setMonthsDays] = useState([]);
  const [formData, setFormData] = useState(initialClientBaseFormData);

  useEffect(
    function () {
      if (month === "February") {
        setMonthsDays(days29);
      }

      if (months31Days.includes(month)) {
        setMonthsDays(days31);
      }

      if (months30Days.includes(month)) {
        setMonthsDays(days30);
      }
    },
    [month]
  );

  function handleSelectMonth(e) {
    setMonth(e.target.value);
    handleChange(e);
  }

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  function handleClearForm() {
    setFormData(initialClientBaseFormData);
  }

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post(
        "http://localhost:8080/clientBase/create",
        formData,
        {
          headers: {
            "Content-Type": "application/json",
          },
        }
      );
      console.log("Success:", response.data);
    } catch (error) {
      console.log("Error:", error);
    }
    // const newClientData = { ...formData };
    // console.log("New Client Data:", newClientData);
  };

  return (
    <>
      <form className="form-client-search" onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="firstName">
            First Name
            <input
              type="text"
              id="firstName"
              name="firstName"
              value={formData.firstName}
              onChange={handleChange}
            />
          </label>
          <label htmlFor="middleName">
            Middle Name
            <input
              type="text"
              id="middleName"
              name="middleName"
              value={formData.middleName}
              onChange={handleChange}
            />
          </label>
          <label htmlFor="lastName">
            Last Name
            <input
              type="text"
              id="lastName"
              name="lastName"
              value={formData.lastName}
              onChange={handleChange}
            />
          </label>
        </div>

        <div className="data-quality-selection">
          <label htmlFor="nameDataQuality">
            Name Data Quality
            <select
              id="nameDataQuality"
              name="nameDataQuality"
              value={formData.nameDataQuality}
              onChange={handleChange}
            >
              <option>Please Select...</option>
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
              onChange={handleSelectMonth}
              value={formData.dobMonth}
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
              value={formData.dobDay}
              onChange={handleChange}
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
              value={formData.dobYear === 0 ? "YYYY" : formData.dobYear}
              onChange={handleChange}
            />
          </div>
        </div>

        <div className="data-quality-selection">
          <label htmlFor="dobDataQuality">
            Date of Birth Data Quality
            <select
              id="dobDataQuality"
              name="dobDataQuality"
              value={formData.dobDataQuality}
              onChange={handleChange}
            >
              <option>Please Select...</option>
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
              value={formData.firstThreeSsn === 0 ? "" : formData.firstThreeSsn}
              onChange={handleChange}
            />
            <span>-</span>
            <input
              type="text"
              id="ssnInput"
              name="middleTwoSsn"
              maxLength="2"
              value={formData.middleTwoSsn === 0 ? "" : formData.middleTwoSsn}
              onChange={handleChange}
            />
            <span>-</span>
            <input
              type="text"
              id="ssnInput"
              name="lastFourSsn"
              maxLength="4"
              value={formData.lastFourSsn === 0 ? "" : formData.lastFourSsn}
              onChange={handleChange}
            />
          </div>
        </div>

        <div className="data-quality-selection">
          <label htmlFor="ssnDataQuality">
            Social Security Number Data Quality
            <select
              id="ssnDataQuality"
              name="ssnDataQuality"
              value={formData.ssnDataQuality}
              onChange={handleChange}
            >
              <option>Please Select...</option>
              {dataQualitySelections.map((option, index) => (
                <option key={index} value={option}>
                  {option}
                </option>
              ))}
            </select>
          </label>
        </div>
      </form>
      <div>
        <button onClick={onClientSearch}>Search</button>
        <button onClick={handleClearForm}>Clear</button>
        <button onClick={handleSubmit}>Add New Client</button>
      </div>
    </>
  );
}
