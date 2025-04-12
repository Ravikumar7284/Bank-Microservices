CREATE TABLE IF NOT EXISTS cards (
  card_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  mobile_number VARCHAR(10) NOT NULL,
  card_number VARCHAR(16) NOT NULL,
  card_type VARCHAR(20) NOT NULL,
  total_limit INT NOT NULL,
  amount_used INT NOT NULL,
  available_amount INT NOT NULL,
  created_at DATE NOT NULL,
  created_by VARCHAR(20) NOT NULL,
  updated_at DATE DEFAULT NULL,
  updated_by VARCHAR(20) DEFAULT NULL
);