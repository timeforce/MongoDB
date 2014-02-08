//calculate the sum total of people who are living in a zip code where the city starts with a digit.
db.zips.aggregate([
	{$project:{first_char:{$substr:["$city",0,1]}, pop:1}},
	{$match:{first_char:/[0-9]/}}, //use regular expression to filter out unwanted document
	{$group:{_id:"total", total:{$sum:"$pop"}}} //can change"total" to any word, since I don't need "_id" field when calculating total sum.
])