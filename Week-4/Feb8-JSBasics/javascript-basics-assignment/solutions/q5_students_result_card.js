// Write a program to display one result card of 10 students
// with their name and percentage
// Out of 10 students, 5 has subjects - Grammer and Accounts
// and other 5 has subjects -  Grammer and Physics

// Hint : You need to calculate percentage of 10 students having different set of subjects.
//        You can assume their scores on their respective subjects.


// Write your code here

var students = [
    {
      name: 'John Doe',
      subjects: [
        { name: 'Grammar', gradePercentage: 85 },
        { name: 'Accounting', gradePercentage: 90 }
      ]
    },
    {
      name: 'Jane Doe',
      subjects: [
        { name: 'Grammar', gradePercentage: 80 },
        { name: 'Accounting', gradePercentage: 92 }
      ]
    },
    {
      name: 'Jim Smith',
      subjects: [
        { name: 'Grammar', gradePercentage: 88 },
        { name: 'Accounting', gradePercentage: 87 }
      ]
    },
    {
      name: 'Jill Smith',
      subjects: [
        { name: 'Grammar', gradePercentage: 95 },
        { name: 'Accounting', gradePercentage: 89 }
      ]
    },
    {
      name: 'Jack Johnson',
      subjects: [
        { name: 'Grammar', gradePercentage: 90 },
        { name: 'Accounting', gradePercentage: 80 }
      ]
    },
    {
      name: 'Jessica Johnson',
      subjects: [
        { name: 'Grammar', gradePercentage: 82 },
        { name: 'Physics', gradePercentage: 95 }
      ]
    },
    {
      name: 'Jonathan Wilson',
      subjects: [
        { name: 'Grammar', gradePercentage: 75 },
        { name: 'Physics', gradePercentage: 88 }
      ]
    },
    {
      name: 'Janet Wilson',
      subjects: [
        { name: 'Grammar', gradePercentage: 92 },
        { name: 'Physics', gradePercentage: 87 }
      ]
    },
    {
      name: 'Jordan Brown',
      subjects: [
        { name: 'Grammar', gradePercentage: 83 },
        { name: 'Physics', gradePercentage: 90 }
      ]
    },
    {
      name: 'Jacob Brown',
      subjects: [
        { name: 'Grammar', gradePercentage: 78 },
        { name: 'Physics', gradePercentage: 95 }
      ]
    }
  ];

  function averageGrade(subject) {
    let totalGrade = 0;
    let numStudents = 0;
    for (let i = 0; i < students.length; i++) {
        for (let j = 0; j < students[i].subjects.length; j++) {
            if (students[i].subjects[j].name === subject) {
                totalGrade += students[i].subjects[j].gradePercentage;
                numStudents++;
            }
        }
    }

    return numStudents > 0 ? totalGrade / numStudents : 0;
  }

  console.log(averageGrade('Grammar'));
  console.log(averageGrade('Accounting'));
  console.log(averageGrade('Physics'));
