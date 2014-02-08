//Please calculate the average population of cities in California (abbreviation CA) and New York (NY) (taken together) with populations over 25,000. 
/*
1.Filter state "CA" and "NY"
2.Group by "city" and "state" to calculate total population in both states
3.Filter total population larger than 25000
4.TRICKY PART. As I only want average population for both states, no need to use "_id" and thus put "not_used" here. Can also put other words here. ***should not use "$_id.state" as the result will be divided by states***
*/
db.zips.aggregate([
	{$match:{$or:[{state:"CA"}, {state:"NY"}]}},
	{$group:{_id:{city:"$city", state:"$state"}, total:{$sum:"$pop"}}}, 
	{$match:{total:{$gt:25000}}},
	{$group:{_id:"not_used", avgtotal:{$avg:"$total"}}}
])