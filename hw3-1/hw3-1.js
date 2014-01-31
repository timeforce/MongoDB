var students = db.students.find({"scores.type": "homework"})

for (student in students){
	
	for (score in student["scores"]){
		if (score == "homework"){
			homeworkscore.push(score.score);
		}
	}
	
}

db.students.update({"_id": student["_id"]}, {"$pull": {"scores": min(homeworkscore)}})