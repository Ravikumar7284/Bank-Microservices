CREATE TABLE IF NOT EXISTS loans (
  loan_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  mobile_number VARCHAR(10) NOT NULL,
  loan_number VARCHAR(16) NOT NULL,
  loan_type VARCHAR(20) NOT NULL,
  total_loan INT NOT NULL,
  amount_paid INT NOT NULL,
  outstanding_amount INT NOT NULL,
  created_at DATE NOT NULL,
  created_by VARCHAR(20) NOT NULL,
  updated_at DATE DEFAULT NULL,
  updated_by VARCHAR(20) DEFAULT NULL
);