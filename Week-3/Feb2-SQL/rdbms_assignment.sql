create database RDBMS_Assignment
use RDBMS_Assignment
create table User(
	user_id int primary key auto_increment,
    user_name varchar(255) not null,
    user_added_date datetime default current_timestamp,
    user_password varchar(255) not null,
    user_mobile varchar(20) not null
)
create table Note(
	note_id int primary key auto_increment,
    note_title varchar(255) not null,
    note_content text,
    note_status varchar(20),
    note_creation_date datetime default current_timestamp
)
create table Category(
	category_id int primary key auto_increment,
    category_name varchar(255) not null,
    category_descr text,
    category_creation_date datetime default current_timestamp,
    category_creator int
)
create table Reminder(
	reminder_id int primary key auto_increment,
    reminder_name varchar(255),
    reminder_descr text,
    reminder_type varchar(20),
    reminder_creation_date datetime default current_timestamp,
    reminder_creator int
)
create table NoteCategory(
	notecategory_id int primary key auto_increment,
    note_id int,
    category_id int,
    foreign key (note_id) references Note(note_id),
    foreign key (category_id) references Category(category_id)
)
create table Notereminder(
	notereminder_id int primary key auto_increment,
    note_id int,
    reminder_id int,
    foreign key (note_id) references Note(note_id),
    foreign key (reminder_id) references Reminder(reminder_id)
)
create table Usernote(
	usernote_id int primary key auto_increment,
    user_id int,
    note_id int,
    foreign key (user_id) references User(user_id),
    foreign key (note_id) references Note(note_id)
)

INSERT INTO User (user_name, user_password, user_mobile)
VALUES
  ("John Doe", "password123", "555-555-5555"),
  ("Jane Doe", "passwd456", "555-555-5556"),
  ("Bob Smith", "securepass", "555-555-5557");

INSERT INTO Note (note_title, note_content, note_status)
VALUES
  ("Important Meeting", "Meeting with the boss at 10am tomorrow.", "active"),
  ("Grocery List", "1. Milk\n2. Bread\n3. Eggs", "active"),
  ("To Do List", "1. Buy gifts for birthday party\n2. Plan weekend trip\n3. Call mom", "active");

INSERT INTO Category (category_name, category_descr, category_creator)
VALUES
  ("Personal", "Personal notes and reminders.", 1),
  ("Work", "Work related notes and reminders.", 2),
  ("Shopping", "Shopping lists and purchases.", 3);

INSERT INTO Reminder (reminder_name, reminder_descr, reminder_type, reminder_creator)
VALUES
  ("Pay Rent", "Pay rent for the current month.", "monthly", 1),
  ("Water Plants", "Water the plants once a week.", "weekly", 2),
  ("Take Medication", "Take medication every morning.", "daily", 3);

INSERT INTO NoteCategory (note_id, category_id)
VALUES
  (1, 2),
  (2, 3),
  (3, 1);

INSERT INTO Notereminder (note_id, reminder_id)
VALUES
  (1, 1),
  (2, 3),
  (3, 2);

INSERT INTO Usernote (user_id, note_id)
VALUES
  (1, 1),
  (2, 2),
  (3, 3);

# Fetch the row from User table based on Id and Password.
select * from User where user_id = 1 and user_password = 'password123';

# Fetch all the rows from Note table based on the field note_creation_date.
select * from Note where date(note_creation_date) = '2023-02-03';

# Fetch all the Categories created after the particular Date.
select * from Category where date(category_creation_date) > '2023-02-02';

# Fetch all the Note ID from UserNote table for a given User.
select note_id from UserNote where user_id = 1;

# Write Update query to modify particular Note for the given note Id.
update Note set note_title='Dentist Appointment', note_content='Dentist appointment scheduled for next Thursday' where note_id=1;

# Fetch all the Notes from the Note table by a particular User.
select * from Note n join UserNote un on n.note_id = un.note_id where un.user_id = 1;

# Fetch all the Notes from the Note table for a particular Category.
select * from Note n join NoteCategory nc on n.note_id = nc.note_id where nc.category_id = 3;

# Fetch all the reminder details for a given note id.
select * from Reminder r join NoteReminder nr on r.reminder_id = nr.reminder_id where nr.note_id = 1 ;

# Fetch the reminder details for a given reminder id.
select * from Reminder where reminder_id = 1;

# Write a query to create a new Note from particular User (Use Note and UserNote tables - insert statement).
insert into Note(note_title, note_content, note_status, note_creation_date) Values("Dentist feedback", "Floss more regularly", "active", NOW());
insert into UserNote(user_id, note_id) Values(1, last_insert_id());

# Write a query to create a new Note from particular User to particular Category(Use Note and NoteCategory tables - insert statement)
insert into Note(note_title, note_content, note_status, note_creation_date) Values("Personal Trainer", "Sam", "active", NOW());
insert into NoteCategory(note_id, category_id) Values(last_insert_id(), 1);

# Write a query to set a reminder for a particular note (Use Reminder and NoteReminder tables - insert statement)
insert into Reminder (reminder_name, reminder_descr, reminder_type, reminder_creator) Values("PT Appt", "Saturday 9am", "weekly", 1);
insert into NoteReminder (note_id, reminder_id) Values (1, last_insert_id());

# Write a query to delete particular Note added by a User(Note and UserNote tables - delete statement)
delete n, un
from Note n
join UserNote un on un.note_id = n.note_id
where n.note_id = 1 and un.user_id = 1;

# Write a query to delete particular Note from particular Category(Note and NoteCategory tables - delete statement)
delete n, nc
from Note n
join NoteCategory nc on nc.note_id = n.note_id
where n.note_id = 1 and nc.category_id = 1;

# Create a trigger to delete all matching records from UserNote, NoteReminder and NoteCategory tables
DELIMITER $$
create trigger tr_delete_note_references 
after delete on Note 
for each row 
begin
  delete from UserNote where UserNote.note_id = old.note_id; 
  delete from NoteReminder where NoteReminder.note_id = old.note_id; 
  delete from NoteCategory where NoteCategory.note_id = old.note_id; 
end$$
DELIMITER ;