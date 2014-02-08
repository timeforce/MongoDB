//calculate the class with the best average student performance. 

/*
Hint/Strategy: You need to group twice to solve this problem. You must figure out the GPA that each student has achieved in a class and then average those numbers to get a class average. After that, you just need to sort. The hardest class is class_id=2. Those students achieved a class average of 37.6 
*/
db.grades.aggregate([
	{$unwind:"$scores"},
	{$match:{"scores.type":{$ne:"quiz"}}},
	{$group:{_id:{student_id:"$student_id", class_id:"$class_id"},avgstudent:{$avg:"$scores.score"}}},
	{$group:{_id:"$_id.class_id", avgclass:{$avg:"$avgstudent"}}},
	{$sort:{avgclass : -1}},
	{$limit: 1}
])




