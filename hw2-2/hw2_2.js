//must declare a variable 
var tmps = db.grades.find({type: "homework"}).sort({student_id:1, score: 1}) 

student_id = -1
while(tmps.hasNext()){
	tmp = tmps.next()
	if(student_id != tmp["student_id"]){ //can also use 'tmp.student_id' 
		student_id = tmp["student_id"]
		db.grades.remove(tmp)
	}
}