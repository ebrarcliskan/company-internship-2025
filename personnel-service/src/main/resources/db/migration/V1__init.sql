CREATE TABLE  IF NOT EXISTS employees (
    id BIGSREIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    department VARCHAR(255) NOT NULL
);

CREATE INDEX IF NOT EXISTS idx_employees_department ON employees(department);
CREATE INDEX IF NOT EXISTS idx_employess_email_ci ON employees (LOWER(email));
CREATE INDEX IF NOT EXISTS idx_employees_name_ci ON employees (lower(name));