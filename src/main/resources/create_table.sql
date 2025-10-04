-- Create students table
CREATE TABLE students (
                          student_id SERIAL PRIMARY KEY,
                          name VARCHAR(50) NOT NULL,
                          surname VARCHAR(50) NOT NULL,
                          birthday DATE NOT NULL
);

-- Create hobbies table with foreign key relationship
CREATE TABLE hobbies (
                         hobby_id SERIAL PRIMARY KEY,
                         student_id INTEGER NOT NULL,
                         hobby VARCHAR(100) NOT NULL,
                         CONSTRAINT fk_student
                             FOREIGN KEY(student_id)
                                 REFERENCES students(student_id)
                                 ON DELETE CASCADE
);
