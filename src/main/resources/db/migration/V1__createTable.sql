CREATE TABLE `planet` (
  `planet_id` VARCHAR(15) NOT NULL,
  `planet_name` VARCHAR(31) NOT NULL,
  PRIMARY KEY (`planet_id`),
  UNIQUE INDEX `planet_name_UNIQUE` (`planet_name` ASC));

CREATE TABLE `edge` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `source` VARCHAR(15) NOT NULL,
  `destination` VARCHAR(15) NOT NULL,
  `distance` DECIMAL(5,2) NOT NULL DEFAULT 0.00,
  `traffic_delay` DECIMAL(5,2) NOT NULL DEFAULT 0.00,
  PRIMARY KEY (`id`));
