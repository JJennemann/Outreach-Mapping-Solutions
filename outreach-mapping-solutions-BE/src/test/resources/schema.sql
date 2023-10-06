CREATE TABLE CLIENT_BASE_DATA (
  id INT PRIMARY KEY,

  first_name VARCHAR(255),
  middle_name VARCHAR(255),
  last_name VARCHAR(255),
  name_data_quality VARCHAR(255),

  dob_month VARCHAR(255),
  dob_day VARCHAR(255),
  dob_year VARCHAR(255),
  dob_data_quality VARCHAR(255),

  first_three_ssn INT,
  middle_two_ssn INT,
  last_four_ssn INT,
  ssn_data_quality VARCHAR(255)
);