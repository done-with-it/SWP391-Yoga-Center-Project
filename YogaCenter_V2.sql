CREATE TABLE Role_table (
  role_id INT PRIMARY KEY,
  role_name VARCHAR(50)
);

CREATE TABLE user_table (
  user_id INT PRIMARY KEY,
  user_name VARCHAR(100),
  email VARCHAR(100),
  address VARCHAR(255),
  gender VARCHAR(10),
  phone VARCHAR(20),
  status VARCHAR(50),
  role_id INT,
  FOREIGN KEY (role_id) REFERENCES Role_table(role_id)
);

CREATE TABLE account_table (
  user_id INT PRIMARY KEY,
  password VARCHAR(100),
  FOREIGN KEY (user_id) REFERENCES user_table(user_id)
);

CREATE TABLE Course_table (
  course_id INT PRIMARY KEY,
  course_name VARCHAR(100),
  startdate DATE,
  enddate DATE,
  class_id INT,
  status VARCHAR(50)
);

CREATE TABLE Class_table (
  class_id INT PRIMARY KEY,
  class_name VARCHAR(100),
  status VARCHAR(50),
  user_id INT,
  course_id INT,
  FOREIGN KEY (course_id) REFERENCES Course_table(course_id),
  FOREIGN KEY (user_id) REFERENCES User_table(user_id)
);

CREATE TABLE Feedback_table (
  feedback_id INT PRIMARY KEY,
  content VARCHAR(255),
  status VARCHAR(50),
  user_id INT,
  class_id INT,
  FOREIGN KEY (user_id) REFERENCES User_table(user_id),
  FOREIGN KEY (class_id) REFERENCES Class_table(class_id)
);

CREATE TABLE ClassList_table (
  classlist_id INT PRIMARY KEY,
  class_id INT,
  user_id INT,
  FOREIGN KEY (class_id) REFERENCES Class_table(class_id),
  FOREIGN KEY (user_id) REFERENCES User_table(user_id),
);

CREATE TABLE Attendance_table (
  attendance_id INT PRIMARY KEY,
  attendance_date DATE,
  status VARCHAR(50),
  classlist_id INT,
  FOREIGN KEY (classlist_id) REFERENCES ClassList_table(classlist_id)
);

CREATE TABLE Booking_table (
  booking_id INT PRIMARY KEY,
  bookingdate DATE,
  user_id INT,
  class_id INT,
  status VARCHAR(50),
  FOREIGN KEY (user_id) REFERENCES User_table(user_id),
  FOREIGN KEY (class_id) REFERENCES Class_table(class_id)
);

Create Table Content_table(
	content_id int primary key,
	topic VARCHAR (100),
	decription varchar (100),
	createdate date,
	status varchar(50),
	 user_id INT,
	FOREIGN KEY (user_id) REFERENCES user_table(user_id)
);

CREATE TABLE Time_table (
  time_id INT PRIMARY KEY,
  start_time TIME,
  end_time TIME
);

CREATE TABLE Room_table (
  room_id INT PRIMARY KEY,
  room_name VARCHAR(100)
);

CREATE TABLE Schedule_table (
  schedule_id INT PRIMARY KEY,
  role_id INT,
  class_id INT,
  time_id INT,
  room_id INT,
  room VARCHAR(100),
  FOREIGN KEY (role_id) REFERENCES Role_table(role_id),
  FOREIGN KEY (class_id) REFERENCES Class_table(class_id),
  FOREIGN KEY (time_id) REFERENCES Time_table(time_id),
  FOREIGN KEY (room_id) REFERENCES Room_table(room_id)
);
-- Insert data into Role_table
INSERT INTO Role_table (role_id, role_name) VALUES
(1, 'admin'),
(2, 'staff'),
(3, 'teachers'),
(4, 'customers');

-- Insert data into user_table
INSERT INTO user_table (user_id, user_name, email, address, gender, phone, status, role_id) VALUES
(1, 'Admin User 1', 'admin1@example.com', '123 Admin St', 'Male', '123456789', 'Active', 1),
(2, 'Admin User 2', 'admin2@example.com', '456 Admin St', 'Female', '987654321', 'Active', 1),
(3, 'Staff User 1', 'staff1@example.com', '123 Staff St', 'Male', '123456789', 'Active', 2),
(4, 'Staff User 2', 'staff2@example.com', '456 Staff St', 'Female', '987654321', 'Active', 2),
(5, 'Teacher User 1', 'teacher1@example.com', '123 Teacher St', 'Male', '123456789', 'Active', 3),
(6, 'Teacher User 2', 'teacher2@example.com', '456 Teacher St', 'Female', '987654321', 'Active', 3),
(7, 'Teacher User 3', 'teacher3@example.com', '789 Teacher St', 'Male', '123456789', 'Active', 3),
(8, 'Teacher User 4', 'teacher4@example.com', '012 Teacher St', 'Female', '987654321', 'Active', 3),
(9, 'Teacher User 5', 'teacher5@example.com', '345 Teacher St', 'Male', '123456789', 'Active', 3),
(10, 'Customer User 1', 'customer1@example.com', '123 Customer St', 'Female', '987654321', 'Active', 4),
(11, 'Customer User 2', 'customer2@example.com', '456 Customer St', 'Male', '123456789', 'Active', 4),
(12, 'Customer User 3', 'customer3@example.com', '789 Customer St', 'Female', '987654321', 'Active', 4),
(13, 'Customer User 4', 'customer4@example.com', '012 Customer St', 'Male', '123456789', 'Active', 4),
(14, 'Customer User 5', 'customer5@example.com', '345 Customer St', 'Female', '987654321', 'Active', 4),
(15, 'Customer User 6', 'customer6@example.com', '678 Customer St', 'Male', '123456789', 'Active', 4),
(16, 'Customer User 7', 'customer7@example.com', '901 Customer St', 'Female', '987654321', 'Active', 4),
(17, 'Customer User 8', 'customer8@example.com', '234 Customer St', 'Male', '123456789', 'Active', 4),
(18, 'Customer User 9', 'customer9@example.com', '567 Customer St', 'Female', '987654321', 'Active', 4),
(19, 'Customer User 10', 'customer10@example.com', '890 Customer St', 'Male', '123456789', 'Active', 4);

-- Insert data into Course_table
INSERT INTO Course_table (course_id, course_name, startdate, enddate, class_id, status) VALUES
(1, 'Course 1', '2023-01-01', '2023-02-01', 1, 'Active'),
(2, 'Course 2', '2023-02-01', '2023-03-01', 2, 'Active'),
(3, 'Course 3', '2023-03-01', '2023-04-01', 3, 'Active'),
(4, 'Course 4', '2023-04-01', '2023-05-01', 4, 'Active');

-- Insert data into Class_table
INSERT INTO Class_table (class_id, class_name, status, user_id, course_id) VALUES
(1, 'Class 1', 'Active', 5, 1),
(2, 'Class 2', 'Active', 6, 1),
(3, 'Class 3', 'Active', 7, 2),
(4, 'Class 4', 'Active', 8, 2),
(5, 'Class 5', 'Active', 9, 3),
(6, 'Class 6', 'Active', 9, 4),
(7, 'Class 7', 'Active', 10, 3),
(8, 'Class 8', 'Active', 11, 4);

-- Insert data into ClassList_table (customer in classList)
INSERT INTO ClassList_table (classlist_id, class_id, user_id) VALUES
(1, 1, 10),
(2, 1, 11),
(3, 2, 12),
(4, 2, 13),
(5, 3, 14),
(6, 3, 15),
(7, 4, 16),
(8, 4, 17),
(9, 5, 18),
(10, 5, 19),
(11, 6, 10),
(12, 6, 11),
(13, 7, 12),
(14, 7, 13),
(15, 8, 14),
(16, 8, 15);

-- Insert data into Time_table
INSERT INTO Time_table (time_id, start_time, end_time) VALUES
(1, '09:00:00', '10:30:00'),
(2, '10:30:00', '12:00:00'),
(3, '13:00:00', '14:30:00'),
(4, '14:30:00', '16:00:00');

-- Insert data into Room_table
INSERT INTO Room_table (room_id, room_name) VALUES
(1, 'Room 1'),
(2, 'Room 2'),
(3, 'Room 3'),
(4, 'Room 4');

-- Insert data into Schedule_table (teacher can teach many classes but not at the same time)
INSERT INTO Schedule_table (schedule_id, role_id, class_id, time_id, room_id, room) VALUES
(1, 3, 1, 1, 1, 1),
(2, 3, 2, 2, 2, 2),
(3, 3, 3, 3, 3, 3),
(4, 3, 4, 4, 4, 4),
(5, 3, 5, 1, 2, 2),
(6, 3, 6, 2, 3, 3),
(7, 3, 7, 3, 4, 4),
(8, 3, 8, 4, 1, 1);

-- Insert data into Booking_table (booking table and attendance enough customers)
INSERT INTO Booking_table (booking_id, bookingdate, user_id, class_id, status) VALUES
(1, '2023-01-01', 10, 1, 'Active'),
(2, '2023-01-01', 11, 1, 'Active'),
(3, '2023-01-01', 12, 2, 'Active'),
(4, '2023-01-01', 13, 2, 'Active'),
(5, '2023-01-01', 14, 3, 'Active'),
(6, '2023-01-01', 15, 3, 'Active'),
(7, '2023-01-01', 16, 4, 'Active'),
(8, '2023-01-01', 17, 4, 'Active'),
(9, '2023-01-01', 18, 5, 'Active'),
(10, '2023-01-01', 19, 5, 'Active'),
(11, '2023-01-01', 10, 6, 'Active'),
(12, '2023-01-01', 11, 6, 'Active'),
(13, '2023-01-01', 12, 7, 'Active'),
(14, '2023-01-01', 13, 7, 'Active'),
(15, '2023-01-01', 14, 8, 'Active'),
(16, '2023-01-01', 15, 8, 'Active');

-- Insert data into Feedback_table
INSERT INTO Feedback_table (feedback_id, content, status, user_id, class_id) VALUES
(1, 'Great class!', 'Active', 10, 1),
(2, 'I learned a lot.', 'Active', 11, 1),
(3, 'The instructor was excellent.', 'Active', 12, 2),
(4, 'I would recommend this class.', 'Active', 13, 2),
(5, 'The course content was very informative.', 'Active', 14, 3),
(6, 'I enjoyed the interactive activities.', 'Active', 15, 3),
(7, 'The class was well-organized.', 'Active', 16, 4),
(8, 'I had a great experience overall.', 'Active', 17, 4);

-- Insert data into Content_table
INSERT INTO Content_table (content_id, topic, decription, createdate, status, user_id) VALUES
(1, 'Topic 1', 'Description 1', '2023-01-01', 'Active', 5),
(2, 'Topic 2', 'Description 2', '2023-01-01', 'Active', 6),
(3, 'Topic 3', 'Description 3', '2023-01-01', 'Active', 7),
(4, 'Topic 4', 'Description 4', '2023-01-01', 'Active', 8);